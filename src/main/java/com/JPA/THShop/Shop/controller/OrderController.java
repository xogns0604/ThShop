package com.JPA.THShop.Shop.controller;

import com.JPA.THShop.Shop.domain.Dto.OrderDto;
import com.JPA.THShop.Shop.service.OrderService;
import com.google.gson.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 고객이 주문내역 전체를 조회
     * @param
     * @return OrdersMenuDto
     */
    @GetMapping("api/order")
    public void getOrder() {



    }

    /**
     * 주문하기위해 고객이 선택한 정보를 받음
     * @param getJson innerClass 고객id와 선택한제품의 아이디,갯수가 리스트
     * @return Result(OrderDto)
     */
    @PostMapping("api/order")
    public Result OrderProduct(@RequestBody GetOrderInfo getOrderInfo)  {
        return new Result(orderService.order(
                getOrderInfo.getId(),
                getOrderInfo.getProductIds(),
                getOrderInfo.getCounts()));
    }






    @Data @NoArgsConstructor @AllArgsConstructor
    static class GetOrderInfo {
        private Long id;
        private List<Long> productIds = new ArrayList<>();
        private List<Integer> counts = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }


}

//PostMapping 전송데이터 예시
//{
//        "id": 5,
//        "productIds": [
//        1,
//        2,
//        3
//        ],
//        "counts": [
//        1,
//        2,
//        3
//        ]
//}