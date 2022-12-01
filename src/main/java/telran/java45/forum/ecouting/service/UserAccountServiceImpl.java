package telran.java45.forum.ecouting.service;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java45.forum.ecouting.dto.RolsResponsDto;
import telran.java45.forum.ecouting.dto.UserAccountResponseDto;
import telran.java45.forum.ecouting.dto.UserRegistrationDto;
import telran.java45.forum.ecouting.dto.UserUppdateDto;
import telran.java45.forum.ecouting.model.UserAccount;
import telran.java45.forum.ecouting.repository.UserEcountRepository;
import telran.java45.forum.exception.UserNotFoundExeption;
@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
	
	final UserEcountRepository repository;
	final ModelMapper modelMapper;
	
	@Override
	public UserAccountResponseDto addUser(UserRegistrationDto userReg) {
		if (repository.existsById(userReg.getLogin())) {
			throw new UserNotFoundExeption();
		}
		UserAccount userEcount = modelMapper.map(userReg, UserAccount.class);
		repository.save(userEcount);
		return modelMapper.map(userEcount, UserAccountResponseDto.class);
	}

	@Override
	public UserAccountResponseDto getUser(String login) {
		UserAccount userEcount = repository.findById(login).orElseThrow(UserNotFoundExeption::new);
		return modelMapper.map(userEcount, UserAccountResponseDto.class);
	}

	@Override
	public UserAccountResponseDto removeUser(String login) {
		UserAccount userEcount = repository.findById(login).orElseThrow(UserNotFoundExeption::new);
		repository.delete(userEcount);
		return modelMapper.map(userEcount, UserAccountResponseDto.class);
	}

	@Override
	public UserAccountResponseDto editUser(String login, UserUppdateDto userUppdateDto) {
		UserAccount userEcount = repository.findById(login).orElseThrow(UserNotFoundExeption::new);
		userEcount.setFirstName(userUppdateDto.getFirstName());
		userEcount.setLastName(userUppdateDto.getLastName());
		repository.save(userEcount);
		return  modelMapper.map(userEcount, UserAccountResponseDto.class);
	}

	@Override
	public RolsResponsDto changeRolesList(String login, String role, boolean isAddRole) {
		UserAccount userEcount = repository.findById(login).orElseThrow(UserNotFoundExeption::new);
		if (isAddRole) {
			userEcount.addRole(role);

		} else {
			userEcount.removeRole(role);
		}
		repository.save(userEcount);

		return RolsResponsDto.builder().login(login).roles(userEcount.getRoles()).build();
	}

	@Override
	public void changePassword(String login, String password) {
		UserAccount userEcount = repository.findById(login).orElseThrow(UserNotFoundExeption::new);
		userEcount.setPassword(password);
		repository.save(userEcount);
	}

}
