package com.example.paginationspringproject.repository;

import com.example.paginationspringproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
