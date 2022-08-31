package com.codewithshobhit.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.codewithshobhit.blog.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min=4, message="Username must be min of 4 characters!!")
	private String name;
	@Email(message="Email address is not valid!!")
	private String email;
	@NotEmpty
	private String about;
	@NotEmpty
	@Size(min=3, max=10, message="PWD must be in size!!")
	
	private String password;

	private Set<RoleDto> roles=new HashSet<>();
}
