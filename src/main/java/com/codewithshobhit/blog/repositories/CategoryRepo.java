package com.codewithshobhit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithshobhit.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	

}
