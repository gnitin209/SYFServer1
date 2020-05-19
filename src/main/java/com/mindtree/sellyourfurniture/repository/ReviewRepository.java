package com.mindtree.sellyourfurniture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sellyourfurniture.entity.Furniture;
import com.mindtree.sellyourfurniture.entity.Review;
import com.mindtree.sellyourfurniture.entity.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	List<Review> findByFurnitureRating(Furniture furnitureRating);
	//Review findByReviewId(int reviewId);
	Review findByFurnitureRatingAndUserRating(Furniture furnitureRating ,User userRating);

}
