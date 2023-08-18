package com.productcustomerdemo.ProductCustomerDemo.repository;

import com.productcustomerdemo.ProductCustomerDemo.model.Categories;
import com.productcustomerdemo.ProductCustomerDemo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {}
