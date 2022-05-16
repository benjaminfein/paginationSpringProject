package com.example.paginationspringproject.service.impl;

import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String textOfComment;
    private String authorName;
    private String timeOfComment;
}
