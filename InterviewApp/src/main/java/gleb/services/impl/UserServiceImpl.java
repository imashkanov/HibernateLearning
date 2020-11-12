package gleb.services.impl;

import gleb.entities.EntityStatus;
import gleb.entities.Role;
import gleb.entities.User;
import gleb.repositories.RoleRepository;
import gleb.repositories.UserRepository;
import gleb.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final BCryptPasswordEncoder passwordEncoder;

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User register(User user) {
		Role roleUser = roleRepository.findByName("ROLE_USER");
		List<Role> userRoles = Collections.singletonList(roleUser);

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(userRoles);
		user.setStatus(EntityStatus.ACTIVE);

		User registeredUser = userRepository.save(user);
		log.info("IN register - user: {} succesfully registered", registeredUser);
		return registeredUser;
	}

	@Override
	public List<User> getAll() {
		List<User> users = userRepository.findAll();
		log.info("IN getAll - {} users found", users.size());
		return users;
	}

	@Override
	public User findByUserName(String userName) {
		User foundUser = userRepository.findByUserName(userName);
		log.info("IN findByUserName - user: {} found by username {}", foundUser, userName);
		return foundUser;
	}

	@Override
	public User findById(Long id) {
		User foundUser = userRepository.findById(id).orElse(null);
		if (foundUser == null) {
			log.warn("IN findById - no user found by id: {}", id);
			return null;
		}
		log.info("IN findById - user: {} found by id {}", foundUser, id);
		return foundUser;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
		log.info("IN delete: succesfully deleted user with id: {}", id);
	}
}
