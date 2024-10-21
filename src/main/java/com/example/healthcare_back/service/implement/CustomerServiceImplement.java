package com.example.healthcare_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.customer.GetSignInResponseDto;
import com.example.healthcare_back.entity.CustomerEntity;
import com.example.healthcare_back.repository.CustomerRepository;
import com.example.healthcare_back.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<? super GetSignInResponseDto> getSignIn(String userId) {
        
        CustomerEntity customerEntity = null;

        try {

            customerEntity = customerRepository.findByUserId(userId);
            if (customerEntity == null) return ResponseDto.AuthenticationFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetSignInResponseDto.success(customerEntity);
        
    }
    
    

}
