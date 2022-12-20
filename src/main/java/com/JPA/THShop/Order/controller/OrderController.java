package com.JPA.THShop.Order.controller;

import com.JPA.THShop.Order.domain.Dto.OrderSearchDto;
import com.JPA.THShop.Order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문내역 페이징처리하여 반환
     * @param memberId @param page @param pageSize
     * @return Page<OrderSearchDto>
     */
    @GetMapping("api/order")
    public Page<OrderSearchDto> OrdersGetPage(
            @RequestParam(value = "memberId", defaultValue = "0") Long memberId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
            ) {
        return orderService.ordersGetPage(
                memberId,
                page,
                pageSize
        );
    }

    /**
     * 주문하기위해 고객이 선택한 정보를 받음
     * @param getOrderInfo innerClass 고객id와 선택한제품의 아이디,갯수가 리스트
     * @return Result(OrderDto)
     */
    @PostMapping("api/order")
    public Result OrderProduct(@RequestBody GetOrderInfo getOrderInfo)  {
        return new Result(orderService.order(
                getOrderInfo.getId(),
                getOrderInfo.getProductIds(),
                getOrderInfo.getCounts(),
                getOrderInfo.getPoint()
        ));
    }



    @Data @NoArgsConstructor @AllArgsConstructor
    static class GetOrderSearch {
        private Long memberId;
        private int page;
        private int PageSize;

        @Override
        public String toString() {
            return "GetOrderSearch{" +
                    "memberId=" + memberId +
                    ", page=" + page +
                    ", PageSize=" + PageSize +
                    '}';
        }
    }


    @Data @NoArgsConstructor @AllArgsConstructor
    static class GetOrderInfo {
        private Long id;
        private List<Long> productIds = new ArrayList<>();
        private List<Integer> counts = new ArrayList<>();
        private int point;

    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }


}

//PostMapping 전송데이터 예시
//{
//    "id": 5,
//    "productIds": [
//        1,
//        2,
//        3
//    ],
//    "counts": [
//        10,
//        20,
//        30
//    ],
//    "point": 900
//}

//Get
//http://localhost:8087/api/order?memberId=5&page=1&pageSize=5