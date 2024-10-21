package com.example.healthcare_back.dto.response;

public interface ResponseMessage {

    String SUCCESS = "Success.";

    String VALIDATION_FAIL = "Validation failed."; 
    String DUPLICATED_USER_ID = "Duplicated user id."; 
    String DUPLICATED_USER_NICKNAME = "Duplicated user nickname.";  
    String DUPLICATED_USER_TEL_NUMBER = "Duplicated user tel number.";

    String TEL_AUTH_FAIL = "Tel number authentication failed.";
    String SIGN_IN_FAIL = "Sign in failed.";
    String AUTHENTICATION_FAIL = "Authentication fail.";

    String TOKEN_CREATE_FAIL = "Token creation failed.";
    String DATABASE_ERROR = "Database error.";
}
