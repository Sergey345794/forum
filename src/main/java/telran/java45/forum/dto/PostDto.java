package telran.java45.forum.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class PostDto {
	
    String title;
    String content;
    String author;
    LocalDate dateCreated;
    List<String> tags;
    Integer likes;
    List<CommentDto>comments;
	public PostDto(String title, String content, String author, LocalDate dateCreated, List<String> tags) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.dateCreated = dateCreated;
		this.tags = tags;
	}
	
    
}
