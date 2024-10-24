package com.example.healthcare_back.provider;

import java.util.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.security.Key;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

// class: JWT 생성 및 검증 기능 제공자
// - JWT 암호화 알고리즘 HS256
// - 비밀키 환경변수에 있는 jwt.secret
// - JWT 만료기간 10시간
@Component // Spring의 컴포넌트로 등록
public class JwtProvider {
    
    @Value("${jwt.secret}") // application.properties에서 jwt.secret 값을 주입
    private String secretKey;

    // JWT 생성 메서드
    public String create(String userId) {
        // 만료시간 = 현재시간 + 10시간
        Date expiredDate = Date.from(Instant.now().plus(10, ChronoUnit.HOURS));

        String jwt = null;

        try {
            // JWT 암호화에 사용할 Key 생성
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            // JWT 생성
            jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256) // HS256 알고리즘으로 서명
                .setSubject(userId) // JWT의 주체(사용자 ID) 설정
                .setIssuedAt(new Date()) // JWT 발급 시간 설정
                .setExpiration(expiredDate) // JWT 만료 시간 설정
                .compact(); // JWT 문자열 생성
            
        } catch (Exception exception) {
            exception.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
            return null; // 예외가 발생하면 null 반환
        }

        return jwt; // 생성된 JWT 반환
    }

    // JWT 검증 메서드
    public String validate(String jwt) {
        String userId = null;

        try {
            // JWT 암호화에 사용할 Key 생성
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            // jwt 검증 및 payload의 subject 값 추출
            userId = Jwts.parserBuilder()
                .setSigningKey(key) // 서명 검증을 위한 키 설정
                .build()
                .parseClaimsJws(jwt) // JWT 파싱 및 검증
                .getBody() // JWT의 본문 추출
                .getSubject(); // 주체(사용자 ID) 추출

        } catch (Exception exception) {
            exception.printStackTrace(); // 예외 발생 시 스택 트레이스 출력
            return null; // 예외가 발생하면 null 반환
        }

        return userId; // 검증된 사용자 ID 반환
    }
}