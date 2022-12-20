package com.JPA.THShop.Order.domain;

import com.JPA.THShop.Order.domain.Dto.ProductDto;
import com.JPA.THShop.common.entity.BaseTime;
import com.JPA.THShop.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTime {

    @Id @GeneratedValue
    @JoinColumn(name = "product_id")
    private Long id;

    private String name;
    private int price;
    private int stock;
    private int pointRate;
    private String ownerName;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", pointRate=" + pointRate +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    //==연관관계 편의==//


    //==생성==//
    public static Product createProduct(ProductDto productDto) {
        Product product = new Product(
                productDto.getName(),
                productDto.getPrice(),
                productDto.getStock(),
                productDto.getPointRate(),
                productDto.getOwnerName()
        );

        return product;
    }

    public Product(String name, int price, int stock, int pointRate, String ownerName) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.pointRate = pointRate;
        this.ownerName = ownerName;
    }

    //==비즈니스로직==//
    //정보변경(차후수정)
    public Product changeOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    //상품구매시 재고 차감
    public void removeStock(int stock) {
        int restStock = this.stock - stock;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stock = restStock;
    }




}
