package com.iuh.sientifirticle.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.iuh.sientifirticle.entity.User;
import com.iuh.sientifirticle.repository.UserRepository;

@SpringBootApplication
public class App implements CommandLineRunner{

	@Autowired
    UserRepository repo;
    @Autowired
    PasswordEncoder passwordEncoder;
    
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// Khi chương trình chạy
        // Insert vào csdl một user.
        User user = new User();
        user.setName("sytran");
        user.setEmail("sytranvan220501@gmail.com");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRole("ADMIN");
        repo.save(user);
        System.out.println(user);
	}

}
