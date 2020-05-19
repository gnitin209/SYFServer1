package com.mindtree.sellyourfurniture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.FurnitureImage;
@Repository
public interface FurnitureImageRepository extends JpaRepository<FurnitureImage, Integer> {


	public List<FurnitureImage> findByFurnitureImage(Furniture furnitureImage);
	
}
