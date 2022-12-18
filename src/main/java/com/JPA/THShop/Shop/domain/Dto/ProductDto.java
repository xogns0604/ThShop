package com.JPA.THShop.Shop.domain.Dto;

import com.JPA.THShop.Shop.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private int price;
    private int stock;
    private int pointRate;
    private String ownerName;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.pointRate = product.getPointRate();
        this.ownerName = product.getOwnerName();
    }

    public static ProductDto createProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setPointRate(product.getPointRate());
        productDto.setOwnerName(product.getOwnerName());
        return productDto;
    }
    public ProductDto(String name, int price, int stock, int pointRate, String ownerName) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.pointRate = pointRate;
        this.ownerName = ownerName;
    }


    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", pointRate=" + pointRate +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}
