package telran.java45.forum.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import telran.java45.forum.dto.CommentDto;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Post {
	String id;
	@Setter
	String title;
	@Setter
	String content;
	@Setter
	String author;
	@Setter
	LocalDate dateCreated;
	List<String> tags = new ArrayList<>();
	Integer likes;
	List<Comment> comments = new ArrayList<>();

	public Post(String title, String content, String author, LocalDate dateCreated, List<String> tags) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.dateCreated = dateCreated;
		this.tags = tags;
	}

	public boolean addTags(String tag) {
		return tags.add(tag);
	}

	public boolean addComments(Comment comment) {
		return comments.add(comment);
	}
	
	public void setLikes(Integer likes) {
		this.likes += likes;
	}
}
