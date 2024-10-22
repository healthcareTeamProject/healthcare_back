package com.example.healthcare_back.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.healthcare_back.common.object.CommentsList;
import com.example.healthcare_back.dto.response.ResponseCode;
import com.example.healthcare_back.dto.response.ResponseDto;
import com.example.healthcare_back.dto.response.ResponseMessage;
import com.example.healthcare_back.entity.BoardEntity;

import lombok.Getter;

@Getter
public class GetBoardResponseDto extends ResponseDto {
    
    private Integer boardNumber;
    private String boardTitle;
    private String nickname;
    private String boardUploadDate;
    private String boardContents;
    private String youtubeVideoLink;
    private String boardFileContents;
    private Integer boardViewCount;
    private Integer boardLikeCount;
    private CommentsList[] comments;
    private Integer commentsCount;

    public GetBoardResponseDto(BoardEntity boardEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.nickname = boardEntity.getNickname();
        this.boardUploadDate = boardEntity.getBoardUploadDate();
        this.boardContents = boardEntity.getBoardContents();
        this.youtubeVideoLink = boardEntity.getYoutubeVideoLink();
        this.boardFileContents = boardEntity.getBoardFileContents();
        this.boardViewCount = boardEntity.getBoardViewCount();
        this.boardLikeCount = boardEntity.getBoardLikeCount();
        this.comments = boardEntity.getComments();
        this.commentsCount = boardEntity.getCommentsCount();
    }

    public static ResponseEntity<GetBoardResponseDto> success(BoardEntity boardEntities) {
        GetBoardResponseDto responseBody = new GetBoardResponseDto(boardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
