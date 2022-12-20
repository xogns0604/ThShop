package com.JPA.THShop.Order.repository;

import com.JPA.THShop.Order.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(@Param("name") String name);
}
