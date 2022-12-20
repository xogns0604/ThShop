package com.JPA.THShop.Order.repository;

import com.JPA.THShop.Order.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
