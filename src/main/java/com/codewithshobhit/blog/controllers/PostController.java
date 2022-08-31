package com.codewithshobhit.blog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithshobhit.blog.config.AppConstants;
import com.codewithshobhit.blog.payloads.ApiResponse;
import com.codewithshobhit.blog.payloads.PostDto;
import com.codewithshobhit.blog.payloads.PostResponse;
import com.codewithshobhit.blog.repositories.PostRepo;
import com.codewithshobhit.blog.services.FileService;
import com.codewithshobhit.blog.services.PostService;

import lombok.Value;

@RestController
@RequestMapping("/api/")
public class PostController {

	
	@Autowired
	private PostService postService;
	
//	@Autowired
//	private FileService fileService;
	
//	@Value("${project.image}")
//	private String path;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createpost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	//update
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId )
	{
		PostDto updatePost=this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//deletePost
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ApiResponse("Post Deleted !!",true);
	}
	
	//get By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> postsByUser= this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsByUser,HttpStatus.OK);
	}
	
	//get By Category
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
			List<PostDto> postsByCategory= this.postService.getPostsByCategory(categoryId);
			return new ResponseEntity<List<PostDto>>(postsByCategory,HttpStatus.OK);
		}
		
	//get All posts
		@GetMapping("/posts")
		public ResponseEntity<PostResponse> getPosts(@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false) Integer pageNumber,
				@RequestParam(value="pageSize", defaultValue =AppConstants.PAGE_SIZE,required= false) Integer pageSize,
				@RequestParam(value="sortBy", defaultValue="postId", required=false) String sortBy){
			PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy);
					
			return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		}
		
	//get post	
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
			PostDto post= this.postService.getPostbyId(postId);
			return new ResponseEntity<PostDto>(post,HttpStatus.OK);
		}
		
		//search
		@GetMapping("/posts/search/{keyword}")
		public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword){
			List<PostDto> searchPosts = this.postService.searchPosts(keyword);
			return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
		}
		
		//post Image upload
//		@PostMapping("/post/image/upload/{postId}")
//		public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException
//		{
//			String fileName = this.fileService.uploadImage(path, image);
//			PostDto postDto = this.postService.getPostbyId(postId);
//			postDto.setImageName(fileName);
//			PostDto updatePost = this.postService.updatePost(postDto,postId);
//			return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
//		}
}
