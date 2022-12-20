package com.JPA.THShop.Order.service;

import com.JPA.THShop.Order.domain.Dto.OrderDto;
import com.JPA.THShop.Order.domain.Dto.OrderSearchDto;
import com.JPA.THShop.Order.domain.Order;
import com.JPA.THShop.Order.domain.OrderProduct;
import com.JPA.THShop.Order.domain.Product;
import com.JPA.THShop.Order.repository.OrderRepository;
import com.JPA.THShop.Order.repository.ProductRepository;
import com.JPA.THShop.member.domain.Member;
import com.JPA.THShop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    //주문하기
    public OrderDto order(Long memberId, List<Long> productIds, List<Integer> counts,int point) {

        Member findMember = memberRepository.findById(memberId).get();
        List<Product> products = productRepository.findAllById(productIds);

        Order order = Order.createOrder(findMember,point);

        //Order result = orderRepository.save(order);

        //멤버에 추가되는포인트!(포인트미사용시)
        int plusPoint = 0;

        for (int i = 0; i < counts.size(); i++) {
            Product product = products.get(i);
            OrderProduct op = OrderProduct.createOrderProduct(order, product, counts.get(i));
            if (point == 0) {
                plusPoint += product.getPrice() * product.getPointRate() / 100;
            }
        }

        findMember.plusPoint(plusPoint);

        orderRepository.save(order);
        //Dto 변환
        return OrderDto.createOrderDto(order);
    }

    public Page<OrderSearchDto> ordersGetPage(Long memberId, int page, int pageSize) {

        PageRequest pageRequest = PageRequest.of(page-1, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        Page<Order> findOrders = orderRepository.findPagingById(memberId, pageRequest);

        return findOrders.map(e -> OrderSearchDto.createOrderSearchDto(e));
    }

    //주문조회(+상품)


//    public OrderDto findOrder(Long orderId) {
//        return OrderDto.createOrderDto(orderRepository.findFetchById(orderId));
//    }



}
