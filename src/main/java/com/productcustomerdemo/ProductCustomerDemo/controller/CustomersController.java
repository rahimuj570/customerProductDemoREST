package com.productcustomerdemo.ProductCustomerDemo.controller;

import com.productcustomerdemo.ProductCustomerDemo.model.Customer;
import com.productcustomerdemo.ProductCustomerDemo.repository.CustomerRepo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomersController {
    private @Resource CustomerRepo customerRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @PostMapping
    Customer addCustomer(@RequestBody Customer data) {
        //data.setId(Long.valueOf(UUID.randomUUID().toString()));
        data.setPassword(passwordEncoder.encode(data.getPassword()));
        return customerRepo.save(data);
    }

    @GetMapping("/current")
    public String getCurrent(Principal principal) {
        return principal.getName();
    }
}
