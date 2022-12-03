package telran.java45.forum.ecouting.repository;

import org.springframework.data.repository.CrudRepository;

import telran.java45.forum.ecouting.model.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, String> {
	
	
}
