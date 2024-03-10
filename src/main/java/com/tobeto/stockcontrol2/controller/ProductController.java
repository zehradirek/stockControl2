package com.tobeto.stockcontrol2.controller;

import com.tobeto.stockcontrol2.dto.ProductRequestDTO;
import com.tobeto.stockcontrol2.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tobeto.stockcontrol2.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID productId) {
        ProductResponseDTO product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(
            @PathVariable UUID productId,
            @RequestBody ProductRequestDTO productRequestDTO) {
         productService.updateProduct(productId, productRequestDTO);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/checkStockLevels")
    public String checkStockLevels() {
        return productService.checkStockLevels();
    }
}


