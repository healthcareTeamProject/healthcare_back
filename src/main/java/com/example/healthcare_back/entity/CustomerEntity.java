package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="customer")
@Table(name="customer")
public class CustomerEntity {
    
    @Id
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
    private Integer userMuscleFatNumber;
    private String userMuscleFatDate;
    private Integer threeMajorLiftsNumber;
    private String threeMajorLiftsDate;


    public CustomerEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.name = dto.getName();
        this.nickname = dto.getNickname();
        this.password = dto.getPassword();
        this.telNumber = dto.getTelNumber();
        this.joinPath = dto.getJoinPath();
        this.snsId = dto.getSnsId();
        this.profileImage = dto.getProfileImage();
        this.personalGoal = dto.getPersonalGoal();
        this.height = dto.getHeight();
        this.weight = dto.getWeight();
        this.skeletalMuscleMass = dto.getSkeletalMuscleMass();
        this.bodyFatMass = dto.getBodyFatMass();
        this.deadlift = dto.getDeadlift();
        this.benchPress = dto.getBenchPress();
        this.squat = dto.getSquat();
        this.userMuscleFatNumber = 0; // 기본값 설정
        this.userMuscleFatDate = null; // 초기값을 null로 설정
        this.threeMajorLiftsNumber = 0; 
        this.threeMajorLiftsDate = null; 
    }
}
