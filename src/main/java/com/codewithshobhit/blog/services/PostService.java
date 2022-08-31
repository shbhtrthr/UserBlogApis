package com.codewithshobhit.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codewithshobhit.blog.entities.Post;
import com.codewithshobhit.blog.payloads.PostDto;
import com.codewithshobhit.blog.payloads.PostResponse;

@Service
public interface PostService {

	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//getAllposts
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy);
	
	//get single post
	PostDto getPostbyId(Integer postId);
	
	//get All post by Category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//getAll posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
}
