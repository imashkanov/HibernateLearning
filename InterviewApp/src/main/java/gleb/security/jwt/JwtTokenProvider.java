package gleb.security.jwt;

import gleb.entities.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

	private String secret;

	private long validityInMilliseconds;

	private UserDetailsService userDetailsService;

	@Value("${jwt.token.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${jwt.token.expired}")
	public void setValidityInMilliseconds(long validityInMilliseconds) {
		this.validityInMilliseconds = validityInMilliseconds;
	}

	@Autowired
	public void setUserDetailsService(@Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct
	protected void init() {
		secret = Base64.getEncoder().encodeToString(secret.getBytes());
	}

	public String createToken(String userName, List<Role> roles) {
		Claims claims = Jwts.claims().setSubject(userName);
		claims.put("roles", getRoleNames(roles));
		Date now = new Date();
		Date validityExpirationDate = new Date(now.getTime() + validityInMilliseconds);
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(validityExpirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public String getUserName(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new JwtAuthenticationException("JWT token is expired or invalid");
		}
	}

	private List<String> getRoleNames(List<Role> userRoles) {
		return userRoles.stream().map(Role::getName).collect(Collectors.toList());
	}
}
