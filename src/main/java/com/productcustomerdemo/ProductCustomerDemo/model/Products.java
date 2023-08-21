package com.productcustomerdemo.ProductCustomerDemo.model;

import com.productcustomerdemo.ProductCustomerDemo.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private int price;
    private String description;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Categories categories;

    @Transient
    public ProductDto toDto(){
        return ProductDto.from(this);
    }

}
