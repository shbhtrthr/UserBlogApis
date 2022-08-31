package com.codewithshobhit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithshobhit.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
