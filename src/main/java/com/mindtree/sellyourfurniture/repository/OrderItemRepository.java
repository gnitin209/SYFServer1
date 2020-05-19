package com.mindtree.sellyourfurniture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.OrderDetails;
import com.mindtree.sellyourfurniture.entity.OrderItem;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
	
	//boolean existsByFurnitureAndOrder(Furniture furniture,OrderDetails order);
	
	List<OrderItem> findByFurniture(Furniture furniture);

}
