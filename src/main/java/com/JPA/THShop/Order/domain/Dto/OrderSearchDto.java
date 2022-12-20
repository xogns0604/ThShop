package com.JPA.THShop.Order.domain.Dto;

import com.JPA.THShop.Order.domain.Order;
import com.JPA.THShop.Order.domain.OrderProduct;
import com.JPA.THShop.Order.domain.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class OrderSearchDto {


    private Long orderId;
    private OrderStatus status;
    private int usePoint;

    private List<SearchProductDto> productDtos = new ArrayList<>();

    public static OrderSearchDto createOrderSearchDto(Order order) {
        OrderSearchDto orderSearchDto = new OrderSearchDto(
                order.getId(),
                order.getStatus(),
                order.getUsePoint()
        );

        for (OrderProduct orderProduct : order.getOrderProducts()) {
            orderSearchDto.getProductDtos()
                    .add(new SearchProductDto().createProductDto(orderProduct));
        }

        return orderSearchDto;
    }

    private OrderSearchDto(Long orderId, OrderStatus status, int usePoint) {
        this.orderId = orderId;
        this.status = status;
        this.usePoint = usePoint;
    }

    @Getter @Setter @NoArgsConstructor
    static class  SearchProductDto {
        private Long productId;
        private String productName;
        private String ownerName;
        private int count;

        protected static SearchProductDto createProductDto(OrderProduct orderProduct) {
            SearchProductDto spd = new SearchProductDto();
            spd.setProductId(orderProduct.getProduct().getId());
            spd.setProductName(orderProduct.getProduct().getName());
            spd.setOwnerName(orderProduct.getProduct().getOwnerName());
            spd.setCount(orderProduct.getCount());

            return spd;
        }
    }
}

