package com.iuh.sientifirticle.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iuh.sientifirticle.entity.MailToken;
import com.iuh.sientifirticle.entity.User;
import com.iuh.sientifirticle.repository.MailTokenRepository;
import com.iuh.sientifirticle.service.MailTokenService;
import com.iuh.sientifirticle.util.RandomUtil;

@Service
public class MailTokenServiceImpl implements MailTokenService{
	@Value("${jdj.secure.token.validity}")
	private int tokenValidityInSeconds;
	
	@Autowired
	private MailTokenRepository mailTokenRepository;

	@Override
	public MailToken createMailToken(User user) {
		String tokenValue = RandomUtil.generateRandomStringNumber(6).toUpperCase();
		MailToken secureToken = new MailToken();
		secureToken.setToken(tokenValue);
		secureToken.setExpireAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));
		secureToken.setUser(user);
		this.saveMailToken(secureToken);
		return secureToken;

	}

	@Override
	public void saveMailToken(MailToken token) {
		mailTokenRepository.save(token);	
	}

	@Override
	public MailToken findByToken(String token) {
		return mailTokenRepository.findByToken(token);
	}

	@Override
	public void removeToken(String token) {
		MailToken mailToken = mailTokenRepository.findByToken(token);
		mailTokenRepository.delete(mailToken);
		
	}
	
	public int getTokenValidityInSeconds() {
		return tokenValidityInSeconds;
	}

}
