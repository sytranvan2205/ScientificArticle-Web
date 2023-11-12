package com.iuh.sientifirticle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iuh.sientifirticle.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	@Query(value = "select u.* from user u where u.user_email where 1=1 and u.user_email = ? and u.status =1 and u.is_verified = 1",nativeQuery = true)
	User findUserActiveByEmail(String email);

}
