package gleb.security.jwt;

import gleb.entities.EntityStatus;
import gleb.entities.Role;
import gleb.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

	public static JwtUser create(User user) {
		return new JwtUser(
				user.getId(),
				user.getUserName(),
				user.getFirstName(),
				user.getEmail(),
				user.getLastName(),
				user.getPassword(),
				user.getStatus().equals(EntityStatus.ACTIVE),
				user.getUpdated(),
				mapToGrantedAuthorities(user.getRoles())
		);
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
		return userRoles.stream()
				.map(role ->
						new SimpleGrantedAuthority(role.getName())
				).collect(Collectors.toList());
	}
}
