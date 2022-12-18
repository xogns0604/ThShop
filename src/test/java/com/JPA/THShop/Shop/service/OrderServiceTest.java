package com.JPA.THShop.Shop.service;



import com.JPA.THShop.Shop.domain.Dto.OrderDto;
import com.JPA.THShop.Shop.domain.Order;
import com.JPA.THShop.Shop.repository.OrderRepository;
import com.JPA.THShop.Shop.repository.ProductRepository;
import com.JPA.THShop.member.domain.Member;
import com.JPA.THShop.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired ProductRepository productRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager entityManager;

    @Test
    public void 주문하기테스트() throws Exception{
        //given
        List<Long> productIds = new ArrayList<>(); //주문할 상품들의 id
        productIds.add(1L);productIds.add(2L);productIds.add(3L);

        List<Integer> counts = new ArrayList<>(); //상품들의 갯수
        counts.add(10); counts.add(15); counts.add(20);

        Long memberId = 5L; //주문한 고객 ID


        //when
        OrderDto order = orderService.order(memberId, productIds, counts);


        //then
        Order findOrder = orderRepository.findById(order.getOrderId()).get();

        Assertions.assertThat(findOrder.getStatus()).isEqualTo(order.getStatus());

    }

    @Test @org.junit.jupiter.api.Order(2)
    public void 주문1개조회() throws Exception{
        entityManager.clear();
        //given
        Order order = orderRepository.findFetchById(1L);

        //when
        System.out.println("order = " + order);

        //then
        Assertions.assertThat(1).isEqualTo(1);
    }
}
