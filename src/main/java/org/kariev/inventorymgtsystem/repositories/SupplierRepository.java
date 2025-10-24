package org.kariev.inventorymgtsystem.repositories;

import org.kariev.inventorymgtsystem.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

}
