package telran.java45.forum.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.mongodb.repository.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java45.forum.dto.CommentDto;
import telran.java45.forum.dto.MessageDto;
import telran.java45.forum.dto.MetaDataDto;
import telran.java45.forum.dto.PeriodDto;
import telran.java45.forum.dto.PostDto;
import telran.java45.forum.service.ForumService;

@RestController
@RequiredArgsConstructor
public class ForumController {
	
	final ForumService forumService;
	
	@PostMapping("/forum/post/{author}")
	public PostDto addPost(@PathVariable String author, @RequestBody MetaDataDto metaDataDto) {		
		return forumService.addPost(author, metaDataDto);		
	}
	
	@GetMapping("/forum/post/{id}")
	public PostDto findById(@PathVariable String id) {
		return forumService.findPostById(id);
		
	}
	
	@PutMapping("/forum/post/{id}/like")
	public void addLike(@PathVariable String id, @RequestParam Integer like) {		
		forumService.addLike(id, like);
	}
	
	@GetMapping("/forum/posts/author/{author}")
	public PostDto findByAutor(@PathVariable String author) {
		return forumService.findPostByAutor(author);
		
	}
	
	@PostMapping("/forum/post/{id}/comment/{user}")
	public CommentDto addMessage(@PathVariable String id, @PathVariable String user, @RequestBody MessageDto messageDto) {
		return forumService.addComment(id, user, messageDto);		
	}
	
	@DeleteMapping("/forum/post/{id}")
	public PostDto deletPost(@PathVariable String id) {
		return forumService.deletPost(id);		
	}
	
	@PostMapping("/forum/posts/tags")
	public List<PostDto> findPostByTegs(@RequestBody List<String> tags) {
		return forumService.findPostByTegs(tags);		
	}
	
	@PostMapping("/forum/posts/period")
	public List<PostDto> findPostByPeriod(@RequestBody PeriodDto periodDto) {
		return forumService.findPostByPeriod(periodDto);		
	}
	
	@PutMapping("/forum/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody MetaDataDto metaDataDto) {
		return forumService.updatePost(id, metaDataDto);		
	}
}
