package com.example.paginationspringproject.service;

import com.example.paginationspringproject.service.impl.PostDto;
import com.example.paginationspringproject.payload.PostResponse;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
