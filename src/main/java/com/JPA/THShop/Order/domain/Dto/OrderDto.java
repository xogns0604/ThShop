package com.JPA.THShop.Order.domain.Dto;

import com.JPA.THShop.Order.domain.Order;
import com.JPA.THShop.Order.domain.OrderProduct;
import com.JPA.THShop.Order.domain.enums.OrderStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문에 대한 반환 Dto클래스
 * 멤버,주문 정보와 제품정보를 가짐
 */
@Getter @Setter @NoArgsConstructor
public class OrderDto {

    private Long memberId;
    private String memberName;
    private int holdings;
    private int point;

    private Long orderId;
    private OrderStatus status;

    private List<ProductDto> productDtos = new ArrayList<>();


    public static OrderDto createOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setMemberId(order.getMember().getId());
        orderDto.setMemberName(order.getMember().getName());
        orderDto.setHoldings(order.getMember().getHoldings());
        orderDto.setPoint(order.getMember().getPoint());

        orderDto.setOrderId(order.getId());
        orderDto.setStatus(order.getStatus());

        for (OrderProduct orderProduct : order.getOrderProducts()) {
            ProductDto productDto = new ProductDto(
                    orderProduct.getProduct().getId(),
                    orderProduct.getProduct().getName(),
                    orderProduct.getProduct().getStock(),
                    orderProduct.getProduct().getOwnerName(),
                    orderProduct.getCount()
            );
            orderDto.getProductDtos().add(productDto);
        }
        return orderDto;

    }

    @Data @AllArgsConstructor
    static class ProductDto {
        private Long productId;
        private String productName;
        private int stock;
        private String ownerName;
        private int count;
    }





}


//    public static OrderDto createOrderDto(Order order) {
//        OrderDto orderDto = new OrderDto();
//        orderDto.setId(order.getId());
//        orderDto.setMember(order.getMember());
//        orderDto.setStatus(order.getStatus());
//        for (OrderProduct orderProduct : order.getOrderProducts()) {
//            orderDto.orderProductsDto.add(OrderProductDto.createOrderProductDto(orderProduct));
//        }
//        return orderDto;
//    }