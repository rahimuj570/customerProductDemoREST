package com.productcustomerdemo.ProductCustomerDemo.dto;

import com.productcustomerdemo.ProductCustomerDemo.model.Categories;
import com.productcustomerdemo.ProductCustomerDemo.model.Products;
import lombok.Data;

import java.util.List;

@Data
public class CategoriesDto {
    private Long id;
    private String name;
    List<ProductDto>products;

    public Categories toEntity() {
        Categories categories = new Categories();
        categories.setId(id);
        categories.setName(name);
        return categories;
    }

    public static CategoriesDto from(Categories entity){
        CategoriesDto dto=new CategoriesDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }


}
