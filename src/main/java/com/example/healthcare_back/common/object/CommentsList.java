package com.example.healthcare_back.common.object;

import java.util.ArrayList;
import java.util.List;

import com.example.healthcare_back.entity.CommentsEntity;

import lombok.Getter;

@Getter
public class CommentsList {

    private Integer commentsNumber;
    private String commentsContents;
    private String nickname;
    private String commentsDate;
    private Integer commentsLikeCount;

     private CommentsList (CommentsEntity commentsEntity) {

        this.commentsNumber = commentsEntity.getCommentsNumber();
        this.commentsContents = commentsEntity.getCommentsContents();
        this.nickname = commentsEntity.getNickname();
        this.commentsDate = commentsEntity.getCommentsDate();
        this.commentsLikeCount = commentsEntity.getCommentsLikeCount();

    }

    public static List<CommentsList> getList(List<CommentsEntity> commentsEntities) {

        List<CommentsList> comment = new ArrayList<>();
        for (CommentsEntity commentsEntity: commentsEntities) {
            CommentsList commentsList = new CommentsList(commentsEntity);
            comment.add(commentsList);
        }
        return comment;

    }
}
