package com.example.healthcare_back.dto.request.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchCustomerRequestDto {
    
    @NotBlank
    private String name;
    @NotBlank
    private String nickname;
    private String profileImage;
    private String personalGoal;
    @NotBlank
    private Float weight;
    @NotBlank
    private Float height;
    private Float skeletalMuscleMass;
    private Float bodyFatMass;
    private Float deadlift;
    private Float benchPress;
    private Float squat;

}
