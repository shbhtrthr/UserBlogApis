package com.codewithshobhit.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.codewithshobhit.blog.payloads.ApiResponse;
import com.codewithshobhit.blog.payloads.CategoryDto;
import com.codewithshobhit.blog.services.CategoryService;



@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
    //create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cateogDto)
	{
		CategoryDto createCategory = this.categoryService.createCategory(cateogDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto cateogDto,@PathVariable Integer catId)
	{
		CategoryDto updatedCategory = this.categoryService.updateCategory(cateogDto,catId);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@RequestBody CategoryDto cateogDto,@PathVariable Integer catId)
	{
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully!!",false),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@RequestBody CategoryDto cateogDto,@PathVariable Integer catId)
	{
		CategoryDto category = this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories()
	{
		List<CategoryDto> categories = this.categoryService.getCategories();
		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
	}
}
