package com.JPA.THShop.Order.service;

import com.JPA.THShop.Order.domain.Cart;
import com.JPA.THShop.member.domain.Member;
import com.JPA.THShop.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class CartServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void test() throws Exception{
        //given
        Long memberId = 5L;
        Long productId = 1L;

        Member member = memberRepository.findById(memberId).get();

        Cart cart = member.getCart();


        //when

        //then

    }
}