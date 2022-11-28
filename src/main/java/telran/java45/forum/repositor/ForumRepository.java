package telran.java45.forum.repositor;



import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java45.forum.model.Post;


public interface ForumRepository extends CrudRepository<Post, String> {
	Optional<Post> findPostByAuthor(String author);	
	Post findPostByTagsInIgnoreCase(String tags);
	
	@Query("{'dateCreated': {$lt: ?0, $gt: ?1}}")
	Stream<Post> findPostsInPeriod(LocalDate min, LocalDate max);
}
