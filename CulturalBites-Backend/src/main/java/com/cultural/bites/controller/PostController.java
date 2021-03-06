/**
 * 
 */
package com.cultural.bites.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultural.bites.exception.ResourceNotFFoundException;
import com.cultural.bites.model.Post;
import com.cultural.bites.repository.PostRepository;



/**
 * @author siddi
 *
 */
@CrossOrigin(origins="http://localhost:3001")
@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostRepository postRepo;
	
	@PostMapping("/add")
	public String add(@RequestBody Post post) {
		postRepo.save(post);
		return "Post successyfully added!";
	}
	
	@GetMapping("/getAll")
	public List<Post> getAllPosts(){
		return postRepo.findAll();
	}
	
	@GetMapping("/posts/{name}")
	public List<Post> getPostById(@PathVariable String name){
		List <Post> posts = postRepo.findByName(name);
		if(posts.isEmpty()) {
			System.out.println(new ResourceNotFFoundException("Post(s) with the name "+ name +" not found"));
		}
		
		return postRepo.findByName(name);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<Post> updatePost(@PathVariable int postId, @RequestBody Post post){
		Post toBeUpdated = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFFoundException("Post not found"));
		toBeUpdated.setPost(post.getName());
		toBeUpdated.setPost(post.getSubject());
		toBeUpdated.setPost(post.getPost());
		Post updatedPost = postRepo.save(toBeUpdated);
		return ResponseEntity.ok(updatedPost);
	}
	
	@DeleteMapping("/delete/{postId}")
	public String deletePost(@PathVariable int postId) {
		postRepo.findById(postId).orElseThrow(() -> new ResourceNotFFoundException("Post not found"));
		postRepo.deleteById(postId);
		return "The post with id: "+ postId +" is removed from the database";
	}
		
}
