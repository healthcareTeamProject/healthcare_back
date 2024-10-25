package com.example.healthcare_back.dto.response.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.CustomerEntity;

import lombok.Getter;

@Getter
public class GetCustomerResponseDto extends ResponseDto {

    private String userId;
    private String name;
    private String nickname;
    private String telNumber;
    private String profileImage;
    private String personalGoal;
    private Float weight;
    private Float height;
    private Float skeletalMuscleMass;
    private Float bodyFatMass;
    private Float deadlift;
    private Float benchPress;
    private Float squat;
    private Integer userMuscleFatNumber;
    private String userMuscleFatDate;
    private Integer threeMajorLiftsNumber;
    private String threeMajorLiftsDate;

    private GetCustomerResponseDto(CustomerEntity customerEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userId = customerEntity.getUserId();
        this.name = customerEntity.getName();
        this.nickname = customerEntity.getNickname();
        this.telNumber = customerEntity.getTelNumber();
        this.profileImage = customerEntity.getProfileImage();
        this.personalGoal = customerEntity.getPersonalGoal();
        this.weight = customerEntity.getWeight();
        this.height = customerEntity.getHeight();
        this.skeletalMuscleMass = customerEntity.getSkeletalMuscleMass();
        this.bodyFatMass = customerEntity.getBodyFatMass();
        this.deadlift = customerEntity.getDeadlift();
        this.benchPress = customerEntity.getBenchPress();
        this.squat = customerEntity.getSquat();
        this.userMuscleFatNumber = customerEntity.getUserMuscleFatNumber();
        this.userMuscleFatDate = customerEntity.getUserMuscleFatDate();
        this.threeMajorLiftsNumber = customerEntity.getThreeMajorLiftsNumber();
        this.threeMajorLiftsDate = customerEntity.getThreeMajorLiftsDate();
    }

    public static ResponseEntity<GetCustomerResponseDto> success(CustomerEntity customerEntity) {
        GetCustomerResponseDto responseBody = new GetCustomerResponseDto(customerEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}