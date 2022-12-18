package com.JPA.THShop.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data @AllArgsConstructor
public class JsonData {
    private Long id;
    private List<Long> productIds;
    private List<Integer> counts;


}
