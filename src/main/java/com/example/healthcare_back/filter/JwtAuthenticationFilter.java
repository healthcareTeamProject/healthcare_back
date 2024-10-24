package com.example.healthcare_back.filter;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.healthcare_back.provider.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


// JWT 검증 및 Security Context에 접근 제어자 추가 필터
// - request의 header에서 토큰 추출 검증
// - security context에 접근 제어자 정보 등록
@Component // Spring의 컴포넌트로 등록
@RequiredArgsConstructor // 생성자 주입을 위한 어노테이션
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider; // JWT 검증을 위한 JwtProvider

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // Request 객체에서 Bearer 토큰 추출
            String token = parseBearerToken(request);
            // 토큰이 없으면 다음 필터로 넘어감
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 토큰 검증
            String userId = jwtProvider.validate(token);
            // 토큰 검증에 실패하면 다음 필터로 넘어감
            if (userId == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // security context에 등록
            setContext(request, userId);

        } catch (Exception exception) {
            exception.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
        }
        
        // 다음 필터로 요청을 전달
        filterChain.doFilter(request, response);
    }

    // request로부터 토큰 추출
    private String parseBearerToken(HttpServletRequest request) {
        // Request 객체의 Header에서 Authorization 필드 값을 추출
        String authorization = request.getHeader("Authorization");

        // 추출한 authorization 값이 실제로 존재하는 문자열인지 확인
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if (!hasAuthorization) return null; // 없으면 null 반환

        // Bearer 인증 방식인지 확인
        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) return null; // Bearer 방식이 아니면 null 반환

        // Authorization 필드 값에서 토큰 추출
        String token = authorization.substring(7); // "Bearer " 이후의 부분 추출
        return token;
    }

    // security context 생성 및 등록
    private void setContext(HttpServletRequest request, String userId) {
        // 접근 주체에 대한 인증 토큰 생성
        AbstractAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES); // 사용자 ID와 권한 정보를 포함한 인증 토큰 생성

        // 생성한 인증 토큰이 어떤 요청에 대한 내용인지 상세 정보 추가
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 빈 security context 생성
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        // 생성한 빈 security context에 authenticationToken 주입
        securityContext.setAuthentication(authenticationToken);

        // 생성한 security context 등록
        SecurityContextHolder.setContext(securityContext);
    }
}