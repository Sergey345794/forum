package telran.java45.forum.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.transform.stream.StreamSource;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mongodb.Tag;

import lombok.RequiredArgsConstructor;
import telran.java45.forum.dto.CommentDto;
import telran.java45.forum.dto.MessageDto;
import telran.java45.forum.dto.MetaDataDto;
import telran.java45.forum.dto.PeriodDto;
import telran.java45.forum.dto.PostDto;
import telran.java45.forum.exception.NotExistException;
import telran.java45.forum.model.Comment;
import telran.java45.forum.model.Post;
import telran.java45.forum.repositor.ForumRepository;

@Service
@RequiredArgsConstructor
public class ForumServicImpl implements ForumService {
	
	final ForumRepository forumRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addPost(String author, MetaDataDto metaDataDto) {		
		PostDto post = new PostDto(metaDataDto.getTitle(), metaDataDto.getContent(), author, LocalDate.now(), metaDataDto.getTags());		
		forumRepository.save(modelMapper.map(post, Post.class));
		return post;
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new NotExistException());
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String id, Integer like) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new NotExistException());
		post.setLikes(like);
	}

	@Override
	public PostDto findPostByAutor(String author) {
		Post post = forumRepository.findPostByAuthor(author).orElseThrow(() -> new NotExistException());
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public CommentDto addComment(String id, String user, MessageDto messageDto) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new NotExistException());
		post.getComments().add(new Comment(user, messageDto.getMessage(), LocalDate.now()));
		forumRepository.save(post);
		return modelMapper.map(post.getComments(), CommentDto.class);
	}

	@Override
	public PostDto deletPost(String id) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new NotExistException());
		forumRepository.deleteById(id);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> findPostByTegs(List<String> tags) {
		List<Post> post = tags.stream().map(t -> forumRepository.findPostByTagsInIgnoreCase(t))
			.collect(Collectors.toList());
		List<PostDto> postsDto = post.stream().map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
		return postsDto;
	}

	@Override
	public List<PostDto> findPostByPeriod(PeriodDto periodDto) {
		LocalDate min = periodDto.getDateFrom();
		LocalDate max = periodDto.getDateTo();
		
		return forumRepository.findPostsInPeriod(min, max)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PostDto updatePost(String id, MetaDataDto metaDataDto) {
		Post post = forumRepository.findById(id).orElseThrow(() -> new NotExistException());
		post.setTitle(metaDataDto.getTitle());
		post.setContent(metaDataDto.getContent());
		post.getTags().add(metaDataDto.getTags().stream().flatMap(tag -> Stream.of(tag)).toString());
		forumRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

}
