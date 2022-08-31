package com.codewithshobhit.blog.services.impl;

import java.util.HashSet;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithshobhit.blog.entities.Comment;
import com.codewithshobhit.blog.entities.Post;
import com.codewithshobhit.blog.exceptions.ResourceNotFoundException;
import com.codewithshobhit.blog.payloads.CommentDto;
import com.codewithshobhit.blog.payloads.PostDto;
import com.codewithshobhit.blog.repositories.CommentRepo;
import com.codewithshobhit.blog.repositories.PostRepo;
import com.codewithshobhit.blog.services.CommentService;

@Service
public class CommentSericeImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id",postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		//post.setComments(new HashSet<>(comment));
		Comment savedComment= this.commentRepo.save(comment);
		
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Comment Id",commentId));
		
		this.commentRepo.delete(comment);

	}

}
