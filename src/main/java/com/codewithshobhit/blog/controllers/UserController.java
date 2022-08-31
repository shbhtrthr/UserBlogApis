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
import com.codewithshobhit.blog.payloads.UserDto;
import com.codewithshobhit.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
	private UserService userService;
    
    
    //POST-create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
    	UserDto createUserDto=this.userService.createUser(userDto);
    	return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    
    //PUT-update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")Integer uId)
    {
    	UserDto updatedUser=this.userService.updateUser(userDto, uId);
    	return  ResponseEntity.ok(updatedUser);
    	
    }
    
    //Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId")Integer uid)
    {
    	this.userService.deleteUser(uid);
    	return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }
    
    //GET-user get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
    	return ResponseEntity.ok(this.userService.getAllUsers());
    }
    
  //GET-user get
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId")Integer userId)
    {
    	return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
