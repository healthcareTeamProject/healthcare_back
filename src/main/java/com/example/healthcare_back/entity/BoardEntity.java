package com.example.healthcare_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {
    
    private Integer boardNumber;
    private String boardTitle;
    private String nickname;
    private String boardUploadDate;
    private Integer boardViewCount;


}
