package com.tobeto.stockcontrol2.repository;

import com.tobeto.stockcontrol2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
