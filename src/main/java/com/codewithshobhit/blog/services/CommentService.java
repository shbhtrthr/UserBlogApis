package com.codewithshobhit.blog.services;

import com.codewithshobhit.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);
	
}
