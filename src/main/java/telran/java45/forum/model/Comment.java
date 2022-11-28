package telran.java45.forum.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
public class Comment {
	String user;
   String message;
    LocalDate dateCreated;
    Integer likes;
	public Comment(String user, String message, LocalDate dateCreated) {
		this.user = user;
		this.message = message;
		this.dateCreated = dateCreated;
	}
    
}
