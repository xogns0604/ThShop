package com.JPA.THShop.Shop.repository;

import com.JPA.THShop.Shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(@Param("name") String name);
}
