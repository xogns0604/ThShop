package com.JPA.THShop.common;

import com.JPA.THShop.Shop.domain.Product;
import com.JPA.THShop.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@Transactional
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInitProduct();
        initService.dbInitMember();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;

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
    }

}
