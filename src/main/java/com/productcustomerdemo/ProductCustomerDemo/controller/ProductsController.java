package com.productcustomerdemo.ProductCustomerDemo.controller;

import com.productcustomerdemo.ProductCustomerDemo.dto.CategoriesDto;
import com.productcustomerdemo.ProductCustomerDemo.dto.ProductDto;
import com.productcustomerdemo.ProductCustomerDemo.model.Products;
import com.productcustomerdemo.ProductCustomerDemo.repository.ProductsRepo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {
    private @Resource ProductsRepo productsRepo;

    //Get All Product
    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<ProductDto> dto = new ArrayList<>();
        List<Products> entity = new ArrayList<>();
        entity = productsRepo.findAll();
        for (Products e : entity) {
            dto.add(e.toDto());
        }
        return dto;
    }

    //Add Product
    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto dto) {
        return productsRepo.save(dto.toEntity()).toDto();
    }

    //Get All Product By Category
    @GetMapping("/cat/{catId}")
    public List<ProductDto> getProdByCat(@PathVariable Long catId) {
        List<Products> entity = new ArrayList<>();
        entity = productsRepo.findProductsByCategoriesId(catId);
        List<ProductDto> dto = new ArrayList<>();
        for (Products e : entity) {
            dto.add(e.toDto());
        }
        return dto;
    }

    @DeleteMapping("/{id}")
    public void deleteProd(@PathVariable Long id) {
        productsRepo.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductDto updateProd(
            @RequestBody ProductDto data, @PathVariable Long id,
            Principal principal
    ) {

        ProductDto dto = data;
        dto.setId(id);
        return productsRepo.save(dto.toEntity()).toDto();
    }
}


