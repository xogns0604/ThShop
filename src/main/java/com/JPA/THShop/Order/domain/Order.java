package com.JPA.THShop.Order.domain;

import com.JPA.THShop.Order.domain.enums.OrderStatus;
import com.JPA.THShop.common.entity.BaseTime;
import com.JPA.THShop.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "orders")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int usePoint;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", member=" + member +
                ", status=" + status +
                ", usePoint=" + usePoint +
                '}';
    }

    //연관관계 편의 매서드
    private Order(Member member, int usePoint) {
        this.member = member;
        this.status = OrderStatus.ORDER;
        member.getOrders().add(this);

        this.usePoint = usePoint;
        member.minusPoint(usePoint);
    }

    //==생성==//
    public static Order createOrder(Member member, int usePoint) {
        Order order = new Order(member, usePoint);
        return order;
    }

    //==비즈니스로직==//
}
