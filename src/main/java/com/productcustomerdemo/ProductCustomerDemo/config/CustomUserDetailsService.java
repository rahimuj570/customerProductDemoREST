package com.productcustomerdemo.ProductCustomerDemo.config;

import com.productcustomerdemo.ProductCustomerDemo.repository.CustomerRepo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private @Resource CustomerRepo customerRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Requested UserDetails for username: {}", username);
        return customerRepo.findByEmail(username).orElseThrow(()->new RuntimeException("USER NOT FOUND"));
    }
}
