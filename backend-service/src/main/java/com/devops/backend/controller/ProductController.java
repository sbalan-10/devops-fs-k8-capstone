package com.devops.backend.controller;

import com.devops.backend.dto.ProductRequest;
import com.devops.backend.dto.ProductResponse;
import com.devops.backend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        return ResponseEntity.ok(service.getAllProducts());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.getProduct(id));

    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<ProductResponse>> search(
            @PathVariable String keyword) {

        return ResponseEntity.ok(service.search(keyword));

    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> category(
            @PathVariable String category) {

        return ResponseEntity.ok(service.category(category));

    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createProduct(request));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(
                service.updateProduct(id, request));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {

        service.deleteProduct(id);

        return ResponseEntity.ok("Product deleted successfully");

    }

}
