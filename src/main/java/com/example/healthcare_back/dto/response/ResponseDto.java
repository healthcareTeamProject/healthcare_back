package com.example.healthcare_back.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {
    
    private String code;
    private String message;

    public static ResponseEntity<ResponseDto> success() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> validationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedUserId() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_USER_ID, ResponseMessage.DUPLICATED_USER_ID);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedUserNickname() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_USER_NICKNAME, ResponseMessage.DUPLICATED_USER_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicatedUserTelNumber() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_USER_TEL_NUMBER, ResponseMessage.DUPLICATED_USER_TEL_NUMBER );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> TelAuthFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.TEL_AUTH_FAIL, ResponseMessage.TEL_AUTH_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> SignInFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> AuthenticationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.AUTHENTICATION_FAIL, ResponseMessage.AUTHENTICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
    
    public static ResponseEntity<ResponseDto> TokenCreationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.TOKEN_CREATE_FAIL, ResponseMessage.TOKEN_CREATE_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

}