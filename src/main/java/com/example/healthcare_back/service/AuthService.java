package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.auth.IdCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.NicknameCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.SignInRequestDto;
import com.example.healthcare_back.dto.request.auth.SignUpRequestDto;
import com.example.healthcare_back.dto.request.auth.TelAuthCheckRequestDto;
import com.example.healthcare_back.dto.request.auth.TelAuthRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.auth.SignInResponseDto;

public interface AuthService {
   ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto requestBody);
   ResponseEntity<ResponseDto> nicknameCheck(NicknameCheckRequestDto requestBody);
   ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto requestBody);
   ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto requestBody);
   ResponseEntity<ResponseDto> signUp(SignUpRequestDto requestBody);
   ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto requestBody);
}
