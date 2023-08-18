package com.productcustomerdemo.ProductCustomerDemo.controller;

import com.productcustomerdemo.ProductCustomerDemo.model.Customer;
import com.productcustomerdemo.ProductCustomerDemo.repository.CustomerRepo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomersController {
    private @Resource CustomerRepo customerRepo;

    @GetMapping
    List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @PostMapping
    Customer addCustomer(@RequestBody Customer data) {
        return customerRepo.save(data);
    }
}
