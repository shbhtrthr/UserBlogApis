package com.codewithshobhit.blog.services;

import java.util.List;

import com.codewithshobhit.blog.payloads.CategoryDto;

public interface CategoryService {
   
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);
	
	//get
	public CategoryDto getCategory(Integer categoryId);
	
	//getAll
	List<CategoryDto> getCategories();
}
