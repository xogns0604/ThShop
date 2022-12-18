package com.JPA.THShop.Shop.domain;

import com.JPA.THShop.Shop.service.OrderService;
import com.JPA.THShop.common.entity.BaseTime;
import com.JPA.THShop.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "orders")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id; // PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //연관관계 편의 매서드
    private Order(Member member) {
        this.member = member;
        this.status = OrderStatus.ORDER;
        member.getOrders().add(this);
    }

    //==생성==//
    public static Order createOrder(Member member) {
        Order order = new Order(member);
        return order;
    }

    //==비즈니스로직==//


}
