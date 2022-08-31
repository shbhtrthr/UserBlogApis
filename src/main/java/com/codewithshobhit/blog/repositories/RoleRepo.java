package com.codewithshobhit.blog.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithshobhit.blog.entities.Role;



public interface RoleRepo  extends JpaRepository<Role, Integer>{

}