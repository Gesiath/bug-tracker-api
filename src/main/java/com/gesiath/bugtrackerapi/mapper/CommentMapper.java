package com.gesiath.bugtrackerapi.mapper;

import com.gesiath.bugtrackerapi.dto.comment.CommentCreateRequest;
import com.gesiath.bugtrackerapi.dto.comment.CommentResponse;
import com.gesiath.bugtrackerapi.entity.Comment;

public class CommentMapper {

    private CommentMapper(){


    }

    public static CommentResponse toResponse(Comment comment){

        if (comment == null){

            return null;

        }

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .issueId(comment.getIssue().getId())
                .issueTitle(comment.getIssue().getTitle())
                .userName(comment.getAuthor().getName())
                .build();

    }

    public static Comment toEntity(CommentCreateRequest dto){

        if (dto == null){

            return null;

        }

        return Comment.builder()
                .content(dto.getContent())
                .build();

    }

}
