package com.productcustomerdemo.ProductCustomerDemo.repository;

import com.productcustomerdemo.ProductCustomerDemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
        public Optional<Customer> findByEmail(String email);
    //public Customer findByEmail(String email);

}
