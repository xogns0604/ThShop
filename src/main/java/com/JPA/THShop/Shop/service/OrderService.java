package com.JPA.THShop.Shop.service;

import com.JPA.THShop.Shop.domain.Dto.OrderDto;
import com.JPA.THShop.Shop.domain.Dto.ProductDto;
import com.JPA.THShop.Shop.domain.Order;
import com.JPA.THShop.Shop.domain.OrderProduct;
import com.JPA.THShop.Shop.domain.Product;
import com.JPA.THShop.Shop.repository.OrderRepository;
import com.JPA.THShop.Shop.repository.ProductRepository;
import com.JPA.THShop.member.domain.Member;
import com.JPA.THShop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    //주문하기
    public OrderDto order(Long memberId, List<Long> productIds, List<Integer> counts) {

        Member findMember = memberRepository.findById(memberId).get();
        List<Product> products = productRepository.findAllById(productIds);

        Order order = Order.createOrder(findMember);

        Order result = orderRepository.save(order);
        for (int i = 0; i < counts.size(); i++) {
            OrderProduct op = OrderProduct.createOrderProduct(order, products.get(i), counts.get(i));
        }

        //Dto 변환
        return OrderDto.createOrderDto(result);
    }

    //주문조회(+상품)
    public OrderDto findOrder(Long orderId) {
        Order foundOrder = orderRepository.findFetchById(orderId);

        return new OrderDto();//Dto만들어서 수정
    }




}
