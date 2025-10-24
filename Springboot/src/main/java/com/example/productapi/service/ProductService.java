package com.example.productapi.service;

import com.example.productapi.dto.ProductRequest;
import com.example.productapi.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(ProductRequest req);
    List<Product> getProductsByPriceRange(double min, double max);
    Product updatePrice(Long productId, double newPrice);
}
