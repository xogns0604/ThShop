package com.JPA.THShop.Order.service;

import com.JPA.THShop.Order.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional @Rollback(value = false)
class ProductServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void addProduct상품추가() throws Exception{
        //given

        //when

        //then

//        public Long addProduct(ProductDto productDto) {
//            Product product = Product.createProduct(productDto);
//            Product result = productRepository.save(product);
//
//            return result.getId();
//        }
    }














    /*@Test @Transactional
    public void 상품추가테스트() throws Exception{  //name, int price, int stock, float pointRate, String ownerName
        //given
        ProductDto productDto1 = new ProductDto("상품1", 10000, 100, 20, "주인1");

        //when
        productService.addProduct(productDto1);

        //then
        Product findproduct = productRepository.findByName("상품1");
        assertThat(productDto1.getPrice()).isEqualTo(findproduct.getPrice());
    }

    @Test
    public void 모든상품조회() throws Exception{
        //given
        Product product1 = new Product("상품1", 10000, 100, 20, "주인1");
        Product product2 = new Product("상품2", 10000, 100, 20, "주인1");
        Product product3 = new Product("상품3", 10000, 100, 20, "주인1");
        Product product4 = new Product("상품4", 10000, 100, 20, "주인1");
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        //when
        List<ProductDto> products = productService.getAllProducts(1);

        //then
        assertThat(products.size()).isEqualTo(4);


    }

    @Test
    public void 실험() throws Exception{
        Product product1 = new Product("상품1", 10000, 100, 20, "주인1");
        System.out.println("result = " + productRepository.save(product1));

    }*/

}