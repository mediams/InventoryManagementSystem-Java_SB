package org.kariev.inventorymgtsystem.services;


import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.dtos.SupplierDTO;

import java.util.UUID;

public interface SupplierService {

    ResponseDTO createSupplier(SupplierDTO dto);

    ResponseDTO updateSupplierById(UUID id, SupplierDTO dto);

    ResponseDTO getSupplierById(UUID id);

    ResponseDTO getAllSupplier();

    ResponseDTO deleteSupplierById(UUID id);
}
