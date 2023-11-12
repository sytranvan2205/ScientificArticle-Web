package com.iuh.sientifirticle.service;

import com.iuh.sientifirticle.entity.MailToken;
import com.iuh.sientifirticle.entity.User;

public interface MailTokenService {
	MailToken createMailToken(User user);

	void saveMailToken(MailToken token);

	MailToken findByToken(String token);

	void removeToken(String token);
}
