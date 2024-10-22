package com.example.healthcare_back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.example.healthcare_back.common.object.CommentsList;
import com.example.healthcare_back.dto.request.board.PostBoardRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    public BoardEntity(PostBoardRequestDto dto, String nickname) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String boardUploadDate = simpleDateFormat.format(now);
        
        this.boardTitle = dto.getBoardTitle();
        this.nickname = nickname;
        this.boardUploadDate = boardUploadDate;
        this.boardContents = dto.getBoardContents();
        this.youtubeVideoLink = dto.getYoutubeVideoLink();
        this.boardFileContents = dto.getBoardFileContents();
        this.boardViewCount = 0;
        this.boardLikeCount = 0;
        this.commentsCount = 0;
        
    }
    
    public void increaseViewCount() {
        this.boardViewCount++;
    }

    public void increaseCommentsCount() {
        this.commentsCount++;
    }

    public void increaseFavoriteCount() {
        this.boardLikeCount++;
    }

    public void decreaseFavoriteCount() {
        this.boardLikeCount--;
    }


}
