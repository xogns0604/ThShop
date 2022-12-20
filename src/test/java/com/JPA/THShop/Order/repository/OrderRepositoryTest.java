package com.JPA.THShop.Order.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback(false)
class OrderRepositoryTest {

    @Autowired OrderRepository orderRepository;

    @Test
    public void test() throws Exception{
        //given
        //List<Order> findMembers = orderRepository.findFetchByMember(5L);

//        System.out.println("findMembers = " + findMembers);
        //when

        //then

    }
}