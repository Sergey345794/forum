package telran.java45.forum.ecouting.controller;

import java.util.Base64;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java45.forum.ecouting.dto.UserAccountResponseDto;
import telran.java45.forum.ecouting.dto.UserRegistrationDto;
import telran.java45.forum.ecouting.dto.UserUppdateDto;
import telran.java45.forum.ecouting.service.UserAccountService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserEcountComtroller {
	
	final UserAccountService accService;
	
	@PostMapping("/register")
	public UserAccountResponseDto register(@RequestBody UserRegistrationDto userRegistrationDto) {
		return accService.addUser(userRegistrationDto);
		
	}
	
	@PostMapping("/login")
	public UserAccountResponseDto login(@RequestHeader("Authorization") String token) {
		String[] credantialStrings = getCredentials(token);
		return accService.getUser(credantialStrings[0]);
		
	}
	
	@DeleteMapping("/user/{user}")
	public UserAccountResponseDto deletUser(String user) {
		accService.removeUser(user);
		return accService.getUser(user);
		
	}
	
	@PutMapping("/user/{user}")
	public UserAccountResponseDto uppdateUser(String user, UserUppdateDto userUppdateDto) {
		accService.editUser(user, userUppdateDto);
		return accService.getUser(user);
	}
	
	@PutMapping("/user/{user}/role/{role}")
	public UserAccountResponseDto addRole(String user, String role) {
		accService.changeRolesList(user, role, true);
		return accService.getUser(user);
	}
	
	@DeleteMapping("/user/{user}/role/{role}")
	public UserAccountResponseDto delRole(String user, String role) {
		accService.changeRolesList(user, role, false);
		return accService.getUser(user);
	}
	
	@PutMapping("/user/password")
	public UserAccountResponseDto changePassword(@RequestParam String user, @RequestParam String password) {
		accService.changePassword(user, password);
		return accService.getUser(user);
		
	}
	
	
	
	
	
	
	private String[] getCredentials(String token) {
		String[] basicAuth = token.split(" ");
		String decode = new String(Base64.getDecoder().decode(basicAuth[1]));
		return decode.split(":");
	}
}
