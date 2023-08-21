package com.productcustomerdemo.ProductCustomerDemo.controller;

import com.productcustomerdemo.ProductCustomerDemo.dto.CategoriesDto;
import com.productcustomerdemo.ProductCustomerDemo.dto.ProductDto;
import com.productcustomerdemo.ProductCustomerDemo.model.Products;
import com.productcustomerdemo.ProductCustomerDemo.repository.ProductsRepo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product")
public class ProductsController {


    private @Resource ProductsRepo productsRepo;

    public boolean isOwner(Long id, Principal principal) {
        Products products = new Products();
        products = productsRepo.findById(id).orElseThrow(() -> new NoSuchElementException());
        return principal.getName().equals(products.getCustomer().getEmail());
    }

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

    @GetMapping("/{id}")
    public List<ProductDto> getOwnerProduct(@PathVariable Long id, Principal principal, HttpServletResponse response) {
        if(isOwner(id,principal)){
            List<Products> entity = new ArrayList<>();
            entity = productsRepo.findProductsByCustomerId(id);
            List<ProductDto> dto = new ArrayList<>();
            for (Products product : entity) {
                dto.add(product.toDto());
            }
            return dto;
        }else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new ArrayList<>();
        }
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
    public void deleteProd(HttpServletResponse response, @PathVariable Long id, Principal principal) {
//        Products prod = new Products();
//        prod = productsRepo.findById(id).orElse(null);
//        logger.info(productsRepo.findCustomerByCustomerId(id));
        Logger logger = LoggerFactory.getLogger(ProductsController.class);
        //logger.info(principal.getName());
        //logger.info(prod.getCustomer().getEmail());
        if (isOwner(id, principal)) {
            productsRepo.deleteById(id);
            logger.info("YWAAAAAAHHHHHHHHHHHHHHHHHH");
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.info("Nooooooooooooooooooooooo");
        }

    }

    @PutMapping("/{id}")
    public ProductDto updateProd(
            @RequestBody ProductDto data, @PathVariable Long id,
            Principal principal,
            HttpServletResponse response
    ) {
        if (isOwner(id, principal)) {

            ProductDto dto = data;
            dto.setId(id);
            return productsRepo.save(dto.toEntity()).toDto();
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return new ProductDto();
        }

    }
}


