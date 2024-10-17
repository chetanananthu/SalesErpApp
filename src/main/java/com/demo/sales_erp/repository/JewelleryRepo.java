package com.demo.sales_erp.repository;

import com.demo.sales_erp.entity.Jewellery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
//import org.springframework.data.mongodb.repository.MongoRepository;

public interface JewelleryRepo extends JpaRepository<Jewellery, Long>{
    List<Jewellery> findBySalesPerson(String salesPerson);
}
