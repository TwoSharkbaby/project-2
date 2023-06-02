package com.twosharkbaby.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twosharkbaby.www.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
	
	User findByNickname(String nickname);
	
	User findById(Long id);  
	
	@Modifying
	@Query(value = "UPDATE User u SET u.password = :password, u.nickname = :nickname, u.email = :email WHERE u.id = :id", nativeQuery = true)
	Integer updateUser(@Param("id")Long id,@Param("password") String password,@Param("nickname") String nickname,@Param("email") String email);

}
