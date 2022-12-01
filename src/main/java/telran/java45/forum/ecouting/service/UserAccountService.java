package telran.java45.forum.ecouting.service;

import telran.java45.forum.ecouting.dto.RolsResponsDto;
import telran.java45.forum.ecouting.dto.UserAccountResponseDto;
import telran.java45.forum.ecouting.dto.UserRegistrationDto;
import telran.java45.forum.ecouting.dto.UserUppdateDto;

public interface UserAccountService {
	UserAccountResponseDto addUser(UserRegistrationDto userRegistrationDto);

	UserAccountResponseDto getUser(String login);
	
	UserAccountResponseDto removeUser(String login);
	
	UserAccountResponseDto editUser(String login, UserUppdateDto userUppdateDto);
	
	RolsResponsDto changeRolesList(String login, String rol, boolean isAddRole);
	
	void changePassword(String login, String password);
	
	
}
