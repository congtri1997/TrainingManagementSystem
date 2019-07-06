package edu.hcmuaf.tms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hcmuaf.tms.entity.AbstractUser;
import edu.hcmuaf.tms.entity.Role;
import edu.hcmuaf.tms.repository.AbstractUserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AbstractUserRepository abstractUserRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AbstractUser user = abstractUserRepository.getAbstractUser(userName);
		if (user == null) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		System.out.println("Found User: " + user);
		// [ROLE_USER, ROLE_ADMIN,..]
		Set<Role> roleNames = user.getRoles();
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (Role role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new User(user.getUserName(), //
				user.getEncryptedPassword(), grantList);

		return userDetails;
	}

}
