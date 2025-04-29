package com.chj.gr.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chj.gr.model.Product;

@RestController
public class ProductController {

    @GetMapping("/products")
    @PreAuthorize("hasAuthority('SCOPE_products.read')")
    public List<Product> getProducts() {
        return Arrays.asList(
                new Product[] {
                		new Product(1, "I Pad", 10),
                        new Product(2, "I Phone", 12),
                        new Product(3, "MacBook", 15)
                });
    }
}
