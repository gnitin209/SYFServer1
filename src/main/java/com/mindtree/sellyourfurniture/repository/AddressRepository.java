package com.mindtree.sellyourfurniture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.sellyourfurniture.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
