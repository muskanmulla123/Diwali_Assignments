package com.example.productapi.controller;

import com.example.productapi.dto.ProductRequest;
import com.example.productapi.model.Product;
import com.example.productapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) { this.productService = productService; }
    @GetMapping
    public List<Product> getAllProducts() { return productService.getAllProducts(); }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest req) {
        try { return ResponseEntity.ok(productService.createProduct(req)); }
        catch (IllegalArgumentException ex) { return ResponseEntity.badRequest().body(ex.getMessage()); }
    }
    @GetMapping("/range")
    public List<Product> getByRange(@RequestParam double min, @RequestParam double max) {
        return productService.getProductsByPriceRange(min, max);
    }
    @PatchMapping("/{id}/price")
    public ResponseEntity<?> updatePrice(@PathVariable Long id, @RequestBody PriceUpdateRequest body) {
        try { return ResponseEntity.ok(productService.updatePrice(id, body.getPrice())); }
        catch (IllegalArgumentException ex) { return ResponseEntity.status(404).body(ex.getMessage()); }
    }
    public static class PriceUpdateRequest {
        private double price;
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }
}
