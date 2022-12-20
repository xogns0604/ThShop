package com.JPA.THShop.Order.service;

import com.JPA.THShop.Order.repository.CartRepository;
import com.JPA.THShop.member.domain.Member;
import com.JPA.THShop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;

    public void getCartByMember(Long memberId) {

        Member findMember = memberRepository.findById(memberId).get();



    }

}
