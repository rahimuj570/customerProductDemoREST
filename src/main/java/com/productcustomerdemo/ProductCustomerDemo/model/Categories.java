package com.productcustomerdemo.ProductCustomerDemo.model;

import com.productcustomerdemo.ProductCustomerDemo.dto.CategoriesDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    public Categories() {
    }

    public Categories(Long id) {
        this.id = id;
    }
    public CategoriesDto toDto(){
        return CategoriesDto.from(this);
    }
}
