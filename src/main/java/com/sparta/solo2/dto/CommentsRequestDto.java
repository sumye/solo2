package com.sparta.solo2.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentsRequestDto {
    private String username;
    private String comments;
    private Long contentsId;
}