package com.example.healthcare_back.dto.response.customer;

import lombok.Getter;

@Getter
public class GetCustomerResponseDto {
    
    private String userId;
    private String name;
    private String nickname;
    private String password;
    private String telNumber;
    private String profileImage;
    private String personalGoal;
    private String height;


}
