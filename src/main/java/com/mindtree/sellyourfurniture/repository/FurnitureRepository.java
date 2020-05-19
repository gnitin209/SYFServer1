package com.mindtree.sellyourfurniture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.sellyourfurniture.entity.Furniture;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {

	
	Furniture findTopByOrderByFurnitureIdDesc();

	List<Furniture> findTop8ByOrderByFurnitureIdDesc();

	Furniture findByFurnitureId(int furnitureId);
	
	List<Furniture> findByFurnitureName(String name);
}
