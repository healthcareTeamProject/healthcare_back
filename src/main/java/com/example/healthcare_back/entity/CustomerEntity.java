package com.example.healthcare_back.entity;

import com.example.healthcare_back.dto.request.auth.SignInRequestDto;
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
    private String height;
    private String registerId;


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
    }

    public CustomerEntity(SignInRequestDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.registerId = dto.getRegisterId();
    }


}
