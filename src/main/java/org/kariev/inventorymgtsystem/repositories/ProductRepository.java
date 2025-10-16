package org.kariev.inventorymgtsystem.repositories;

import org.kariev.inventorymgtsystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
}
