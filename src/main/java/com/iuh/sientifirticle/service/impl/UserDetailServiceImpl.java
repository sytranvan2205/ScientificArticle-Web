package com.iuh.sientifirticle.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iuh.sientifirticle.entity.User;
import com.iuh.sientifirticle.repository.UserRepository;
import com.iuh.sientifirticle.security.CustomUserDetail;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepositoy;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Kiểm tra xem user có tồn tại hay không
		User user = userRepositoy.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("username");
		}
		return new CustomUserDetail(user);
	}

	public UserDetails loadUserById(Long userId) {
		Optional<User> user = userRepositoy.findById(userId);
		if(user == null) {
			throw new UsernameNotFoundException("username");
		}
		return new CustomUserDetail(user.get());
	}


}
