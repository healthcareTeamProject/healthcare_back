package com.example.healthcare_back.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.CommentsList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.CommentsEntity;

import lombok.Getter;

@Getter
public class GetCommentsListResponseDto extends ResponseDto {
    
    private List<CommentsList> commentsLists;

    private GetCommentsListResponseDto(List<CommentsEntity> commentsEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.commentsLists = CommentsList.getList(commentsEntities);
    }

    public static ResponseEntity<GetCommentsListResponseDto> success(List<CommentsEntity> commentsEntities) {
        GetCommentsListResponseDto responseBody = new GetCommentsListResponseDto(commentsEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
