package gleb.security;

import gleb.entities.User;
import gleb.security.jwt.JwtUser;
import gleb.security.jwt.JwtUserFactory;
import gleb.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);

	private final UserService userService;

	@Autowired
	public JwtUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User foundUser = userService.findByUserName(userName);
		if (foundUser == null) {
			throw new UsernameNotFoundException("Not found user with name:" + userName);
		}
		JwtUser jwtUser = JwtUserFactory.create(foundUser);
		log.info("IN loadUserByUsername - user with username: {} succesfully loaded", userName);
		return jwtUser;
	}
}
