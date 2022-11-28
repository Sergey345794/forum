package telran.java45.forum.service;

import java.util.List;

import telran.java45.forum.dto.CommentDto;
import telran.java45.forum.dto.MessageDto;
import telran.java45.forum.dto.MetaDataDto;
import telran.java45.forum.dto.PeriodDto;
import telran.java45.forum.dto.PostDto;

public interface ForumService {
	 PostDto addPost(String author, MetaDataDto metaDataDto);
	
	 PostDto findPostById(String id);
	
	 void addLike(String id, Integer like);
	
	 PostDto findPostByAutor(String author);
	
	 CommentDto addComment(String id, String user, MessageDto messageDto);
	
	 PostDto deletPost(String id);
	
	 List<PostDto> findPostByTegs(List<String> tags);
	
	 List<PostDto> findPostByPeriod(PeriodDto periodDto);
	
	 PostDto updatePost(String id, MetaDataDto metaDataDto);

}
