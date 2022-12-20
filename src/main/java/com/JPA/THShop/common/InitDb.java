package com.JPA.THShop.common;

import com.JPA.THShop.Order.domain.Order;
import com.JPA.THShop.Order.domain.OrderProduct;
import com.JPA.THShop.Order.domain.Product;
import com.JPA.THShop.Order.repository.OrderRepository;
import com.JPA.THShop.Order.repository.ProductRepository;
import com.JPA.THShop.Order.service.OrderService;
import com.JPA.THShop.member.domain.Member;
import com.JPA.THShop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInitProduct();
        initService.dbInitMember();
        initService.dbInitOrder();
        initService.dbInitOrder();
        initService.dbInitOrder();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        private final OrderService orderService;
        private final MemberRepository memberRepository;
        private final OrderRepository orderRepository;
        private final ProductRepository productRepository;

        public void dbInitProduct() {
            Product product1 = new Product("상품1", 10000, 100, 20, "주인1");
            Product product2 = new Product("상품2", 8000, 55, 30, "주인2");
            Product product3 = new Product("상품3", 2000, 60, 40, "주인3");
            Product product4 = new Product("상품4", 1000, 12, 50, "주인4");

            em.persist(product1);
            em.persist(product2);
            em.persist(product3);
            em.persist(product4);
        }

        public void dbInitMember() {
            Member member1 = new Member("member1Id", "member1Pass", "member1이름", "email1.net@gmail.com");
            Member member2 = new Member("member2Id", "member2Pass", "member2이름", "email2.net@gmail.com");
            Member member3 = new Member("member3Id", "member3Pass", "member3이름", "email3.net@gmail.com");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
        }

        public void dbInitOrder() {
            List<Long> productIds = new ArrayList<>(); //주문할 상품들의 id
            productIds.add(1L);productIds.add(2L);productIds.add(3L);

            List<Integer> counts = new ArrayList<>(); //상품들의 갯수
            counts.add(5); counts.add(5); counts.add(5);

            Long memberId = 6L; //주문한 고객 ID

            int point = 0;

            Member findMember = memberRepository.findById(memberId).get();
            List<Product> products = productRepository.findAllById(productIds);

            Order order = Order.createOrder(findMember,point);

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
        }
    }

}
