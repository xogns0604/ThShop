package com.JPA.THShop.Order.repository;


import com.JPA.THShop.Order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"orderProducts"})
    Order findFetchById(Long orderId);

    @EntityGraph(attributePaths = {"member"})
    @Query("select o from Order o where o.member.id = :memberId")
    Page<Order> findPagingById(@Param("memberId") Long memberId, Pageable pageable);

}
