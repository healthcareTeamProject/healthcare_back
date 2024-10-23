package com.example.healthcare_back.service;

import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.dto.request.board.PatchBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PatchCommentRequestDto;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;
import com.example.healthcare_back.dto.request.board.PostCommentRequestDto;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardListResponseDto;
import com.example.healthcare_back.dto.response.board.GetBoardResponseDto;
import com.example.healthcare_back.dto.response.board.GetCommentListResponseDto;


public interface BoardService {
    
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);
    ResponseEntity<? super GetBoardListResponseDto> getBoardList(Integer boardNumber);

    ResponseEntity<ResponseDto> postBoard(PostBoardRequestDto dto, String userId);
    ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String userId);
    ResponseEntity<ResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String userId);
    ResponseEntity<ResponseDto> patchComment(PatchCommentRequestDto dto, Integer boardNumber, Integer commentNumber, String userId);
    ResponseEntity<ResponseDto> deleteBoard(Integer boardNumber, String userId);
    ResponseEntity<ResponseDto> deleteComment(Integer boardNumber, Integer commentNumber, String userId);

}
