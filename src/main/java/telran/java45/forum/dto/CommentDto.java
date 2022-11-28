package telran.java45.forum.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class CommentDto {
	String use;
    String message;
    LocalDate dateCreated;
    Integer likes;
	public CommentDto(String use, String message, LocalDate dateCreated) {
		this.use = use;
		this.message = message;
		this.dateCreated = dateCreated;
	}
    
}
