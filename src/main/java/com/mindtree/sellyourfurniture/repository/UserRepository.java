package com.mindtree.sellyourfurniture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sellyourfurniture.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByuserEmail(String email);

	public User findByuserFirstName(String userName);

	public Optional<User> findByUserEmailAndUserPassword(String userEmail, String userPassword);

	public User findByUserEmail(String userEmail);

}
