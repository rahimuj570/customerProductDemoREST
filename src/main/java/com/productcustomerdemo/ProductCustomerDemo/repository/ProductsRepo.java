package com.productcustomerdemo.ProductCustomerDemo.repository;

import com.productcustomerdemo.ProductCustomerDemo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
    List<Products> findProductsByCategoriesId(Long id);
    List<Products> findProductsByCustomerId(Long id);
}
