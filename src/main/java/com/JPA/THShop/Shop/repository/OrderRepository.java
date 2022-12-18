package com.JPA.THShop.Shop.repository;


import com.JPA.THShop.Shop.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"orders"})
    Order findFetchById(Long orderId);


//    @EntityGraph(attributePaths = {"team"})
//    List<Member> findByUsername(String username)
}
