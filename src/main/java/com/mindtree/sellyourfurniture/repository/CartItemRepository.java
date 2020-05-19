package com.mindtree.sellyourfurniture.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sellyourfurniture.entity.Cart;
import com.mindtree.sellyourfurniture.entity.CartItem;
import com.mindtree.sellyourfurniture.entity.Furniture;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem	, Integer>{
	
	Optional<CartItem> findByFurnitureAndCart(Furniture furniture, Cart cart);
	Optional<List<CartItem>> findByCart(Cart cart);
	void deleteByCart(Cart cart);

}
