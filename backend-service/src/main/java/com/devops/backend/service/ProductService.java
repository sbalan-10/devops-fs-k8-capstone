package com.devops.backend.service;

import com.devops.backend.dto.ProductRequest;
import com.devops.backend.dto.ProductResponse;
import com.devops.backend.entity.Product;
import com.devops.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductResponse> getAllProducts() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    public ProductResponse getProduct(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        return mapToResponse(product);

    }

    public ProductResponse createProduct(ProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        product.setQuantity(request.getQuantity());
        product.setAvailable(request.getAvailable());

        repository.save(product);

        return mapToResponse(product);

    }

    public ProductResponse updateProduct(Long id,
                                         ProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        product.setQuantity(request.getQuantity());
        product.setAvailable(request.getAvailable());

        repository.save(product);

        return mapToResponse(product);

    }

    public void deleteProduct(Long id) {

        repository.deleteById(id);

    }

    public List<ProductResponse> search(String keyword) {

        return repository.findByNameContainingIgnoreCase(keyword)

                .stream()

                .map(this::mapToResponse)

                .collect(Collectors.toList());

    }

    public List<ProductResponse> category(String category) {

        return repository.findByCategory(category)

                .stream()

                .map(this::mapToResponse)

                .collect(Collectors.toList());

    }

    private ProductResponse mapToResponse(Product product) {

        return new ProductResponse(

                product.getId(),

                product.getName(),

                product.getDescription(),

                product.getCategory(),

                product.getPrice(),

                product.getImageUrl(),

                product.getQuantity(),

                product.getAvailable()

        );

    }

}
