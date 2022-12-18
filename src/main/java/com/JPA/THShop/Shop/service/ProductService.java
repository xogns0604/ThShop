package com.JPA.THShop.Shop.service;

import com.JPA.THShop.Shop.domain.Product;
import com.JPA.THShop.Shop.domain.Dto.ProductDto;
import com.JPA.THShop.Shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    //상품추가
    public Long addProduct(ProductDto productDto) {
        Product product = Product.createProduct(productDto);
        Product result = productRepository.save(product);

        return result.getId();
    }


    //모든 상품 가져오기
    public List<ProductDto> getAllProducts(int page){

        PageRequest pageRequest = PageRequest.of(page-1, 5, Sort.by("name"));
        Page<Product> products = productRepository.findAll(pageRequest);
        List<ProductDto> result = products.stream().map(o -> new ProductDto(o)).collect(Collectors.toList());

       return result;
    }

    //상품삭제
    public boolean deleteProduct(Long id) {
        if(productRepository.findById(id).isEmpty()) return false;
        else {
            productRepository.deleteById(id);
            return true;
        }
    }

    //상품정보 변경
    public ProductDto changeProduct(Long id, String ownerName) {
        Optional<Product> findProduct = productRepository.findById(id);

        if (findProduct.isEmpty()) {
            return null;
        } else {
            Product product = findProduct.get().changeOwnerName(ownerName);
            return new ProductDto(product);
        }
    }
}
