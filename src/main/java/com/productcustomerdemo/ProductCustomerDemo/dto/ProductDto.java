package com.productcustomerdemo.ProductCustomerDemo.dto;

import com.productcustomerdemo.ProductCustomerDemo.model.Categories;
import com.productcustomerdemo.ProductCustomerDemo.model.Products;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private int price;
    private String description;
    private Long categoryId;

    public Products toEntity() {
        Products products = new Products();
        products.setId(id);
        products.setCategories(new Categories(categoryId));
        products.setTitle(title);
        products.setPrice(price);
        products.setDescription(description);
        return products;
    }

    public static ProductDto from(Products entity){
    ProductDto dto=new ProductDto();
    dto.setId(entity.getId());
    dto.setCategoryId(entity.getCategories().getId());
    dto.setDescription(entity.getDescription());
    dto.setTitle(entity.getTitle());
    dto.setPrice(entity.getPrice());
    return dto;
    }

}
