package com.mindtree.sellyourfurniture.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sellyourfurniture.entity.OrderDetails;
import com.mindtree.sellyourfurniture.entity.User;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

	OrderDetails findByOrderUser(User userr);
	Optional<OrderDetails> findByorderUser(User userr);
	
	//List<OrderDetails> findByOrderUser(User orderUser);

}
