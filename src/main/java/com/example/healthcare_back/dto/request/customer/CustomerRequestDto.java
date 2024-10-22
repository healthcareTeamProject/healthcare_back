package com.example.healthcare_back.dto.request.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerRequestDto {
    
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
    

}
