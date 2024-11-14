package com.example.healthcare_back.common.object;

import com.example.healthcare_back.entity.CustomerEntity;

import lombok.Getter;

/**
 * 고객 정보를 나타내는 클래스입니다.
 */
@Getter
public class Customer {

    private final String userId;
    private final String name;
    private final String nickname;
    private final String telNumber;
    private final String profileImage;
    private final String personalGoals;
    private final Float weight;
    private final Float height;
    private final Float skeletalMuscleMass;
    private final Float bodyFatMass;
    private final Float benchPress;
    private final Float squat;

    private Customer(CustomerEntity customerEntity) {
        this.userId = customerEntity.getUserId();
        this.name = customerEntity.getName();
        this.nickname = customerEntity.getNickname();
        this.telNumber = customerEntity.getTelNumber();
        this.profileImage = customerEntity.getProfileImage();
        this.personalGoals = customerEntity.getPersonalGoals();
        this.weight = customerEntity.getWeight();
        this.height = customerEntity.getHeight();
        this.skeletalMuscleMass = customerEntity.getSkeletalMuscleMass();
        this.bodyFatMass = customerEntity.getBodyFatMass();
        this.benchPress = customerEntity.getBenchPress();
        this.squat = customerEntity.getSquat();
    }

    /**
     * CustomerEntity를 기반으로 Customer 객체를 생성하는 팩토리 메서드입니다.
     *
     * @param customerEntity 생성할 Customer 객체의 정보가 포함된 CustomerEntity
     * @return Customer 객체
     */
    public static Customer fromEntity(CustomerEntity customerEntity) {
        return new Customer(customerEntity);
    }
}