package com.example.healthcare_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    
    private final CustomerService customerService;

    @GetMapping("/sign-in")
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super GetSignInResponseDto> response = customerService.getSignIn(userId);
        return response;
    }

}
