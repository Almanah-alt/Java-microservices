package com.example.authservice.security;

import com.example.authservice.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		// hard coding the users. All passwords must be encoded.
//		final List<AppUser> users = Arrays.asList(
//				new AppUser(1L, "ali", encoder.encode("ali"), UserRole.USER),
//				new AppUser(2L, "admin", encoder.encode("admin"), UserRole.ADMIN),
//				new AppUser(3L, "rep", encoder.encode("rep"), UserRole.REPAIRER)
//		);

		com.example.authservice.User user = userRepository.findByUsername(username);
//		for(AppUser appUser: users) {
			if(user != null) {

				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
						.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());

				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
				// And used by auth manager to verify and check user authentication.
				return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
//			}
		}

		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Username: " + username + " not found");
	}

}