package com.JPA.THShop.Order.domain;

import com.JPA.THShop.common.entity.BaseTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;
    private int price;
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", price=" + price +
                ", count=" + count +
                ", product=" + product +
                '}';
    }

    //==연관관계==//
    private OrderProduct(Order order, Product product, int price, int count) {
        this.price = price;
        this.count = count;
        this.product = product;
        this.order = order;
    }


    //==생성==//
    public static OrderProduct createOrderProduct(Order order, Product product, int count) {
        OrderProduct orderProduct = new OrderProduct(order, product,product.getPrice()*count, count);
        order.getOrderProducts().add(orderProduct);
        product.removeStock(count);

        return orderProduct;
    }

    //==비즈니스로직==//

}
