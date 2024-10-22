package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.customer.PatchCustomerRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetCustomerResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;

public interface CustomerService {

    ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId);
    ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, String userId);
    ResponseEntity<? super GetCustomerResponseDto> getCustomer(String userId);

}
