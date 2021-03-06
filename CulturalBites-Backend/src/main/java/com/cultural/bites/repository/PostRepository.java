package com.cultural.bites.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultural.bites.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByName(String name);


}
