package com.example.healthcare_back.common.object;

import com.example.healthcare_back.entity.CustomerEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String userId;
    private String name;
    private String nickname;
    private String password;
    private String telNumber;
    private String joinPath;
    private String snsId;
    private String profileImage;
    private String personalGoal;
    private Float weight;
    private Float height;
    private Float skeletalMuscleMass;
    private Float bodyFatMass;
    private Float deadlift;
    private Float benchPress;
    private Float squat;

    private Customer (CustomerEntity customerEntity) {
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
        this.benchPress = customerEntity.getBenchPress();
        this.squat = customerEntity.getSquat();
    }
}
