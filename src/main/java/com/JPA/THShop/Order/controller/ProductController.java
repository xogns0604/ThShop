package com.JPA.THShop.Order.controller;

import com.JPA.THShop.Order.domain.Dto.ProductDto;
import com.JPA.THShop.Order.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 상품등록
     * @param productDto
     * @return 등록한 상품 ID
     */
    @PostMapping("api/product")
    public Long addProduct(@RequestBody @Valid ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    /**
     * 상품전체조회
     * @return 상품객체 리스트
     */
    @GetMapping("api/product")
    public Result getProducts() {
        return new Result(productService.getAllProducts(1));
    }

    /**
     * 상품삭제
     * @param 상품ID
     * @return boolean
     */
    @DeleteMapping("api/product")
    public boolean deleteProduct(@RequestParam Long id) {
        return productService.deleteProduct(id);
    }

    /**
     * 상품정보 변경
     * @param id
     * @param ownerName
     * @return ProductDto
     */
    @PutMapping("api/product/{id}")
    public ProductDto chanOwner(@PathVariable("id") Long id, @RequestParam String ownerName) {
        ProductDto updateMember = productService.changeProduct(id, ownerName);
        return updateMember;
    }

    /**
     * 반환을 배열로 하기위해 감싸주는 class
     * @param <T>
     */
    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}


