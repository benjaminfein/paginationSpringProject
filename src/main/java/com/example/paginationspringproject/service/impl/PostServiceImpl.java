package com.example.paginationspringproject.service.impl;

import com.example.paginationspringproject.entity.Comments;
import com.example.paginationspringproject.entity.Post;
import com.example.paginationspringproject.payload.PostResponse;
import com.example.paginationspringproject.repository.PostRepository;
import com.example.paginationspringproject.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        // convert entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content= listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    // convert Entity into DTO
    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setComments(commentsMapToDto(post.getComments()));
        return postDto;
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setComments(commentsMapToEntity(postDto.getComments()));
        return post;
    }

    private Set<Comments> commentsMapToEntity(Set<CommentDto> commentsDto){
        Set<Comments> comments = new HashSet<>();
        for (CommentDto commentDto:commentsDto) {
            Comments comment = new Comments();
            comment.setTextOfComment(commentDto.getTextOfComment());
            comment.setAuthorName(commentDto.getAuthorName());
            comment.setTimeOfComment(commentDto.getTimeOfComment());
            comments.add(comment);
        }
        return comments;
    }

    private Set<CommentDto> commentsMapToDto(Set<Comments> comments){
        Set<CommentDto> commentsDto = new HashSet<>();
        for (Comments comment:comments) {
            CommentDto newDto = new CommentDto();
            newDto.setTextOfComment(comment.getTextOfComment());
            newDto.setAuthorName(comment.getAuthorName());
            newDto.setTimeOfComment(comment.getTimeOfComment());
            commentsDto.add(newDto);
        }
        return commentsDto;
    }
}