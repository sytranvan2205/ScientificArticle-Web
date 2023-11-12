package com.iuh.sientifirticle.service;

import com.iuh.sientifirticle.dto.DataResponse;
import com.iuh.sientifirticle.dto.UserCreateRequest;
import com.iuh.sientifirticle.entity.User;

public interface UserService {
	DataResponse createAccount(UserCreateRequest userDTO) throws Exception;
	User updateUser(User user);
	DataResponse verifyAccount(String token);
}
