package com.mindtree.sellyourfurniture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sellyourfurniture.entity.Cart;
import com.mindtree.sellyourfurniture.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	Optional<Cart> findByUserCart(User user);

	void deleteByuserCart(User userr);

	Cart findByuserCart(User userr);

}
