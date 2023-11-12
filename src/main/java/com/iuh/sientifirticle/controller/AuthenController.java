package com.iuh.sientifirticle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iuh.sientifirticle.dto.DataResponse;
import com.iuh.sientifirticle.dto.LoginRequest;
import com.iuh.sientifirticle.dto.LoginResponse;
import com.iuh.sientifirticle.dto.RandomStuff;
import com.iuh.sientifirticle.dto.UserCreateRequest;
import com.iuh.sientifirticle.dto.VerifyRequest;
import com.iuh.sientifirticle.jwt.JwtTokenProvider;
import com.iuh.sientifirticle.security.CustomUserDetail;
import com.iuh.sientifirticle.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthenController {
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
    
    // Api /api/random yêu cầu phải xác thực mới có thể request
    @GetMapping("/member/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
    }
    
    @PostMapping("/sign-up")
    @ResponseBody
    public DataResponse signUp(@RequestBody UserCreateRequest userDTO) {
    	DataResponse dataResponse;
    	try {
			dataResponse = userService.createAccount(userDTO);
			return dataResponse;
		} catch (Exception e) {
			dataResponse = new DataResponse();
			dataResponse.setResponseMsg("System is error");
			return dataResponse;
		}
    }
    
    @PostMapping("/account-verify")
    @ResponseBody
    public DataResponse verifyAccount(@RequestBody VerifyRequest verifyRequest) {
    	DataResponse dataResponse;
    	dataResponse = userService.verifyAccount(verifyRequest.getCode());
    	return dataResponse;
    }

}
