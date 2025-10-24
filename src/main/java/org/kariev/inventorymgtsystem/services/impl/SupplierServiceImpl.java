package org.kariev.inventorymgtsystem.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.dtos.SupplierDTO;
import org.kariev.inventorymgtsystem.exceptions.NotFoundException;
import org.kariev.inventorymgtsystem.mapper.SupplierMapper;
import org.kariev.inventorymgtsystem.models.Supplier;
import org.kariev.inventorymgtsystem.repositories.SupplierRepository;
import org.kariev.inventorymgtsystem.services.SupplierService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    @Override
    public ResponseDTO createSupplier(SupplierDTO dto) {

        Supplier supplier = mapper.dtoToEntity(dto);
        repository.save(mapper.entityToDto(supplier));
        return ResponseDTO.builder()
                .status(200)
                .message("Supplier created")
                .build();
    }

    @Override
    public ResponseDTO updateSupplierById(UUID id, SupplierDTO dto) {

        Supplier supplier = repository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
        if (dto.getName() != null) supplier.setName(dto.getName());
        if (dto.getContactInfo() != null) supplier.setContactInfo(dto.getContactInfo());
        if (dto.getAddress() != null) supplier.setAddress(dto.getAddress());
        repository.save(mapper.entityToDto(supplier));

        return ResponseDTO.builder()
                .status(200)
                .message("Supplier updated")
                .build();
    }

    @Override
    public ResponseDTO getSupplierById(UUID id) {
        Supplier supplier = repository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
        repository.save(mapper.entityToDto(supplier));

        return ResponseDTO.builder()
                .status(200)
                .message("Supplier found")
                .build();
    }

    @Override
    public ResponseDTO getAllSupplier() {
        List<Supplier> suppliers = repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        List<SupplierDTO> dtos = mapper.entityListToDto(suppliers);

        return ResponseDTO.builder()
                .status(200)
                .message("Supplier found")
                .suppliers(dtos)
                .build();
    }

    @Override
    public ResponseDTO deleteSupplierById(UUID id) {

        Supplier supplier = repository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
        repository.delete(supplier);

        return ResponseDTO.builder()
                .status(200)
                .message("Supplier deleted")
                .build();
    }
}
