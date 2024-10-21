package com.example.healthcare_back.dto.response.customer;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.CustomerEntity;

public class GetSignInResponseDto extends ResponseDto {
    
    public GetSignInResponseDto(CustomerEntity customerEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        customerEntity.getUserId();
        customerEntity.getName();
        customerEntity.getNickname();
    }

}
