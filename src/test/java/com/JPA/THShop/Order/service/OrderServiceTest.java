package com.JPA.THShop.Order.service;



import com.JPA.THShop.Order.domain.Dto.OrderDto;
import com.JPA.THShop.Order.domain.Dto.OrderSearchDto;
import com.JPA.THShop.Order.domain.Order;
import com.JPA.THShop.Order.domain.OrderProduct;
import com.JPA.THShop.Order.repository.OrderRepository;
import com.JPA.THShop.Order.repository.ProductRepository;
import com.JPA.THShop.exception.NotEnoughPointException;
import com.JPA.THShop.exception.NotEnoughStockException;
import com.JPA.THShop.member.repository.MemberRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
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
    @Autowired EntityManager em;

    @Test
    @org.junit.jupiter.api.Order(1)
    public void 주문하기테스트() throws Exception{
        //given
        List<Long> productIds = new ArrayList<>(); //주문할 상품들의 id
        productIds.add(1L);productIds.add(2L);productIds.add(3L);

        List<Integer> counts = new ArrayList<>(); //상품들의 갯수
        counts.add(10); counts.add(15); counts.add(20);

        Long memberId = 5L; //주문한 고객 ID

        //when
        OrderDto order = orderService.order(memberId, productIds, counts, 0);
        Order findOrder = orderRepository.findById(order.getOrderId()).get();
        findOrder.getOrderProducts().get(1);
        //em.clear();

        //then
           //만들어진 주문 확인
        assertThat(findOrder.getId()).isEqualTo(order.getOrderId());
        assertThat(findOrder.getOrderProducts().size()).isEqualTo(order.getProductDtos().size());

          //멤버포인트&상품재고가 0이하로 내려감
        assertThatExceptionOfType(NotEnoughPointException.class).isThrownBy(() -> {
            memberRepository.findById(5L).get().minusPoint(10000);
        });
        assertThatExceptionOfType(NotEnoughStockException.class).isThrownBy(() -> {
            productRepository.findById(1L).get().removeStock(100000);
        });


    }

    /**
     * 1번테스트랑 같이 실행!
     * @throws Exception
     */
    @Test @org.junit.jupiter.api.Order(2)
    public void 주문1개조회() throws Exception{
        //given
        Long memberId = 5L;
        int page = 1;
        int pageSize = 10;

        //when
        Page<OrderSearchDto> osd = orderService.ordersGetPage(memberId, page, pageSize);
        List<OrderSearchDto> osds = new ArrayList<>();

        for (OrderSearchDto orderSearchDto : osd) {
            osds.add(orderSearchDto);
        }

        //then
        assertThat(osds.size()).isEqualTo(3);
    }
}
