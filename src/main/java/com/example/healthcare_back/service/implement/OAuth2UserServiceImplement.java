package com.example.healthcare_back.service.implement;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.common.object.CustomOAuth2User;
import com.example.healthcare_back.entity.CustomerEntity;
import com.example.healthcare_back.provider.JwtProvider;
import com.example.healthcare_back.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService {
    
    private final JwtProvider jwtProvider;

    private final CustomerRepository customerRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);
        String registration = request.getClientRegistration().getClientName().toLowerCase();

        String snsId = getSnsId(oAuth2User, registration);
        
        CustomerEntity customerEntity = customerRepository.findBySnsIdAndJoinPath(snsId, registration);

        CustomOAuth2User customOAuth2User = null;

        if (customerEntity == null) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("snsId", snsId);
            attributes.put("joinPath", registration);

            customOAuth2User = new CustomOAuth2User(snsId, attributes, false);
        } else {
            String userId = customerEntity.getUserId();
            String token = jwtProvider.create(userId);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("accessToken", token);

            customOAuth2User = new CustomOAuth2User(userId, attributes, true);
        }
    
        return customOAuth2User;

    }

    private String getSnsId(OAuth2User oAuth2User, String registration) {
        String snsId = null;

        if (registration.equals("kakao")) {
            snsId = oAuth2User.getName();
        }
        if (registration.equals("naver")) {
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
            snsId = response.get("id");
        }

        return snsId;
    }

}

