package com.productcustomerdemo.ProductCustomerDemo.repository;

import com.productcustomerdemo.ProductCustomerDemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
