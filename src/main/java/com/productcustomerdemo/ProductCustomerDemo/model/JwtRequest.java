package com.productcustomerdemo.ProductCustomerDemo.model;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
