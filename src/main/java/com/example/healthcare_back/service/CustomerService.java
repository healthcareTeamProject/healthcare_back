package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;

public interface CustomerService {

    ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);
    
}
