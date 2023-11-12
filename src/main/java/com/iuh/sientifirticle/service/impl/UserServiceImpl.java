package com.iuh.sientifirticle.service.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iuh.sientifirticle.constant.Constant;
import com.iuh.sientifirticle.dto.DataResponse;
import com.iuh.sientifirticle.dto.UserCreateRequest;
import com.iuh.sientifirticle.entity.MailToken;
import com.iuh.sientifirticle.entity.User;
import com.iuh.sientifirticle.mail.Mail;
import com.iuh.sientifirticle.mail.MailService;
import com.iuh.sientifirticle.repository.UserRepository;
import com.iuh.sientifirticle.service.MailTokenService;
import com.iuh.sientifirticle.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepositoy;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private MailTokenService mailTokenService;

	@Override
	@Transactional(rollbackFor = {SQLException.class})
	public DataResponse createAccount(UserCreateRequest userDTO) throws Exception {
		DataResponse response = new DataResponse();
		try {
			if (userDTO.getName() == null) {
				response.setResponseMsg("User name is not null");
				response.setRespType(Constant.USER_NOT_NULL);
				return response;
			}
			if (userDTO.getEmail() == null) {
				response.setResponseMsg("Email is not null");
				response.setRespType(Constant.EMAIL_NOT_NULL);
				return response;
			}
			if (userDTO.getPassword() == null) {
				response.setResponseMsg("Password is not null");
				response.setRespType(Constant.PASSWORD_NOT_NULL);
				return response;
			}
			User account = new User();
			account.setEmail(userDTO.getEmail());
			account.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			account.setName(userDTO.getName());
			account.setCreateDate(LocalDate.now());
			account.setStatus(1);
			account.setCreateUser(Constant.SYSTEM);
			account.setIsVerified(0);
			userRepositoy.save(account);
			
			MailToken token = mailTokenService.createMailToken(account);
			Map<String, Object> maps = new HashMap<>();
			maps.put("user", account);
			maps.put("token", token.getToken());

			Mail mail = new Mail();
			mail.setFrom("imusicstudio4nvb@gmail.com");
			mail.setSubject("Registration");
			mail.setTo(account.getEmail());
			mail.setModel(maps);
			try {
				mailService.sendEmail(mail);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			response.setResponseMsg("Create successfull !!!");
			response.setRespType(Constant.HTTP_SUCCESS);
			Map<String, Object> responseMap = new HashMap<>();
			userDTO.setPassword(null);
			responseMap.put("user",userDTO);
			response.setValueResponse(responseMap);
			return response;
		} catch (Exception e) {
			response.setResponseMsg("System error");
			response.setRespType(Constant.SYSTEM_ERROR_CODE);
			return response;
		}
	}

	@Override
	public User updateUser(User user) {
		return userRepositoy.save(user);
	}

	@Override
	public DataResponse verifyAccount(String token) {
		DataResponse dataResponse = new DataResponse();
		try {
			MailToken verifyAccount = mailTokenService.findByToken(token);
			if(verifyAccount == null) {
				dataResponse.setRespType(Constant.MAIL_TOKEN_NOT_EXIST);
				dataResponse.setResponseMsg("Mail token not exist");
				return dataResponse;
			}
			if(verifyAccount.getExpireAt().compareTo(LocalDateTime.now()) <0) {
				dataResponse.setRespType(Constant.MAIL_TOKEN_EXPIRE);
				dataResponse.setResponseMsg("Mail token has expire");
				return dataResponse;
			}	
			User account = verifyAccount.getUser();
			account.setIsVerified(1);
			updateUser(account);
			dataResponse.setResponseMsg("Verify successful !!!");
			dataResponse.setRespType(Constant.HTTP_SUCCESS);
			return dataResponse;
		} catch (Exception e) {
			dataResponse.setRespType(Constant.SYSTEM_ERROR_CODE);
			dataResponse.setResponseMsg("System error");
			return dataResponse;
		}
	}

}
