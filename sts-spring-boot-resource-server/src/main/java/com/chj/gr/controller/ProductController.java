package com.chj.gr.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chj.gr.model.Product;

@RestController
public class ProductController {

    @GetMapping("/api/secure/products/read")
    @PreAuthorize("hasAuthority('SCOPE_products.read')")
    public List<Product> readScope() {
        return Arrays.asList(
                new Product[] {
                		new Product(1, "Product READ_1", 10),
                        new Product(2, "Product READ_1", 12),
                        new Product(3, "Product READ_1", 15)
                });
    }
    
    @GetMapping("/api/secure/products/write")
    @PreAuthorize("hasAuthority('SCOPE_products.write')")
    public List<Product> writeScope() {
        return Arrays.asList(
                new Product[] {
                		new Product(1, "Product WRITE_1", 10),
                        new Product(2, "Product WRITE_2", 12),
                        new Product(3, "Product WRITE_3", 15)
                });
    }
}
