package gleb.controllers;

import gleb.dto.authentication.AuthenticationRequestDto;
import gleb.entities.User;
import gleb.security.jwt.JwtTokenProvider;
import gleb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {
	private AuthenticationManager authenticationManager;

	private JwtTokenProvider jwtTokenProvider;

	private UserService userService;

	@Autowired
	public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
		try {
			String userName = requestDto.getUserName();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, requestDto.getPassword()));
			User user = userService.findByUserName(userName);
			if (user == null) {
				throw new UsernameNotFoundException("Not found user with username: " + userName);
			}
			String token = jwtTokenProvider.createToken(userName, user.getRoles());
			Map<Object, Object> response = new HashMap<>();
			response.put("userName", userName);
			response.put("token", token);
			return ResponseEntity.ok(response);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid userName or password");
		}
	}
}
