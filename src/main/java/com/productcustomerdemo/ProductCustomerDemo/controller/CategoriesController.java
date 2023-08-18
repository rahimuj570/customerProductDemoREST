package com.productcustomerdemo.ProductCustomerDemo.controller;

import com.productcustomerdemo.ProductCustomerDemo.dto.CategoriesDto;
import com.productcustomerdemo.ProductCustomerDemo.dto.ProductDto;
import com.productcustomerdemo.ProductCustomerDemo.model.Categories;
import com.productcustomerdemo.ProductCustomerDemo.model.Products;
import com.productcustomerdemo.ProductCustomerDemo.repository.CategoriesRepo;
import com.productcustomerdemo.ProductCustomerDemo.repository.ProductsRepo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoriesController {
    private @Resource CategoriesRepo categoriesRepo;
    private @Resource ProductsRepo productRepo;

    //GelAll Categories
    @GetMapping
    public List<CategoriesDto> getAll() {
        List<CategoriesDto> dto = new ArrayList<>();
        List<Categories> entity = new ArrayList<>(categoriesRepo.findAll());
        for (Categories e : entity) {
            dto.add(e.toDto());
        }
        return dto;
    }

    //Post Category
    @PostMapping
    public CategoriesDto addCat(@RequestBody CategoriesDto data) {
        return categoriesRepo.save(data.toEntity()).toDto();
    }

    //GEt CategoryDetails
    @GetMapping("/{catId}")
    public CategoriesDto getCatDEtails(@PathVariable Long catId) {
        Categories entity = new Categories();
        entity = categoriesRepo.findById(catId).orElseThrow();
        CategoriesDto dto = new CategoriesDto();
        dto = entity.toDto();
        List<ProductDto> prodDto = new ArrayList<>();
        List<Products> prod = new ArrayList<>(productRepo.findProductsByCategoriesId(catId));
        for (Products e : prod) {
            prodDto.add(e.toDto());
        }
        dto.setProducts(prodDto);
        return dto;
    }
}
