package com.codewithshobhit.blog.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codewithshobhit.blog.entities.Category;
import com.codewithshobhit.blog.entities.Post;
import com.codewithshobhit.blog.entities.User;
import com.codewithshobhit.blog.exceptions.ResourceNotFoundException;
import com.codewithshobhit.blog.payloads.PostDto;
import com.codewithshobhit.blog.payloads.PostResponse;
import com.codewithshobhit.blog.repositories.CategoryRepo;
import com.codewithshobhit.blog.repositories.PostRepo;
import com.codewithshobhit.blog.repositories.UserRepo;
import com.codewithshobhit.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("User","category id",categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		
		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		  Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		  post.setImageName(postDto.getImageName());
		  post.setTitle(postDto.getTitle());
		  post.setContent(postDto.getContent());
		  Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
         this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy) {
		
		
		Pageable p=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getNumberOfElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostbyId(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User u=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		List<Post> posts=this.postRepo.findByUser(u);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
