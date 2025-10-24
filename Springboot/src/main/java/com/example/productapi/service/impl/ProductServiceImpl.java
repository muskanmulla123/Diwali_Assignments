package com.example.productapi.service.impl;

import com.example.productapi.dto.ProductRequest;
import com.example.productapi.model.Category;
import com.example.productapi.model.Product;
import com.example.productapi.repository.CategoryRepository;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Product> getAllProducts() { return productRepository.findAll(); }
    @Override
    public Product createProduct(ProductRequest req) {
        Category category = null;
        if (req.getCategoryId() != null) {
            category = categoryRepository.findById(req.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        } else if (req.getCategoryName() != null && !req.getCategoryName().isBlank()) {
            Optional<Category> opt = categoryRepository.findByName(req.getCategoryName());
            category = opt.orElseGet(() -> categoryRepository.save(new Category(req.getCategoryName())));
        } else throw new IllegalArgumentException("CategoryId or CategoryName required");
        Product p = new Product(req.getName(), req.getDescription(), req.getPrice(), category);
        return productRepository.save(p);
    }
    @Override
    public List<Product> getProductsByPriceRange(double min, double max) {
        return productRepository.findByPriceBetween(min, max);
    }
    @Override
    public Product updatePrice(Long productId, double newPrice) {
        Product p = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        p.setPrice(newPrice);
        return productRepository.save(p);
    }
}
