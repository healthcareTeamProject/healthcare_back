package com.example.healthcare_back.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import com.example.healthcare_back.dto.request.board.PostCommentsRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="comments")
@Table(name="comments")
public class CommentsEntity {

    @Id
    private Integer commentsNumber;
    private String commentsContents;
    private String nickname;
    private String commentsDate;
    private Integer commentsLikeCount;

    public CommentsEntity(PostCommentsRequestDto dto, String nickname) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String commentsDate = simpleDateFormat.format(now);

        this.nickname = nickname;
        this.commentsContents = dto.getCommentsContents();
        this.commentsDate = commentsDate;

    }

    public void increaseFavoriteCount() {
        this.commentsLikeCount++;
    }

    public void decreaseFavoriteCount() {
        this.commentsLikeCount--;
    }

}