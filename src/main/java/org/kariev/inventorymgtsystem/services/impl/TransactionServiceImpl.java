package org.kariev.inventorymgtsystem.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.dtos.TransactionDTO;
import org.kariev.inventorymgtsystem.dtos.TransactionRequestDTO;
import org.kariev.inventorymgtsystem.enums.TransactionStatus;
import org.kariev.inventorymgtsystem.enums.TransactionType;
import org.kariev.inventorymgtsystem.exceptions.NameValueRequiredException;
import org.kariev.inventorymgtsystem.exceptions.NotFoundException;
import org.kariev.inventorymgtsystem.mapper.TransactionMapper;
import org.kariev.inventorymgtsystem.models.Transaction;
import org.kariev.inventorymgtsystem.repositories.ProductRepository;
import org.kariev.inventorymgtsystem.repositories.SupplierRepository;
import org.kariev.inventorymgtsystem.repositories.TransactionRepository;
import org.kariev.inventorymgtsystem.services.TransactionService;
import org.kariev.inventorymgtsystem.services.UserService;
import org.kariev.inventorymgtsystem.specification.TransactionFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final UserService userService;


    @Override
    public ResponseDTO purchaseTransaction(TransactionRequestDTO dto) {

        UUID productId = dto.getProductId();
        UUID supplierId = dto.getSupplierId();
        Integer quantity = dto.getQuantity();

        if (supplierId == null) throw new NameValueRequiredException("Supplier id is required");

        var product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

        var supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new NotFoundException("Supplier not found"));

        var user = userService.getCurrentUser();

        product.setStockQuantity((product.getStockQuantity() + quantity));
        productRepository.save(product);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.PURCHASE)
                .status(TransactionStatus.COMPLETED)
                .product(product)
                .user(user)
                .supplier(supplier)
                .totalProducts(quantity)
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .note(dto.getNote())
                .build();
        repository.save(transaction);

        return ResponseDTO.builder()
                .status(200)
                .message("Transaction purchased")
                .build();
    }

    @Override
    public ResponseDTO sellTransaction(TransactionRequestDTO dto) {
        UUID productId = dto.getProductId();
        Integer quantity = dto.getQuantity();

        var product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

        var user = userService.getCurrentUser();

        product.setStockQuantity((product.getStockQuantity() - quantity));
        productRepository.save(product);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.SALE)
                .status(TransactionStatus.COMPLETED)
                .product(product)
                .user(user)
                .totalProducts(quantity)
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .description(dto.getDescription())
                .note(dto.getNote())
                .build();
        repository.save(transaction);

        return ResponseDTO.builder()
                .status(200)
                .message("Transaction sell")
                .build();
    }

    @Override
    public ResponseDTO returnToSupplierTransaction(TransactionRequestDTO dto) {
        UUID productId = dto.getProductId();
        UUID supplierId = dto.getSupplierId();
        Integer quantity = dto.getQuantity();

        if (supplierId == null) throw new NameValueRequiredException("Supplier id is required");

        var product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

        var supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new NotFoundException("Supplier not found"));

        var user = userService.getCurrentUser();

        product.setStockQuantity((product.getStockQuantity() - quantity));
        productRepository.save(product);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.RETURN_TO_SUPPLIER)
                .status(TransactionStatus.PROCESSING)
                .product(product)
                .user(user)
                .supplier(supplier)
                .totalProducts(quantity)
                .totalPrice(BigDecimal.ZERO)
                .note(dto.getNote())
                .build();
        repository.save(transaction);

        return ResponseDTO.builder()
                .status(200)
                .message("Transaction returned")
                .build();

    }

    @Override
    public ResponseDTO getAllTransactions(int page, int size, String filter) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Specification<Transaction> spec = TransactionFilter.byFilter(filter);
        Page<Transaction> transactionPage = repository.findAll(spec, pageable);

        List<TransactionDTO> transactionDTOS = mapper.entityToListDto(transactionPage.getContent());

        transactionDTOS.forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setProduct(null);
            transactionDTO.setSupplier(null);
        });

        return ResponseDTO.builder()
                .status(200)
                .message("success")
                .transactions(transactionDTOS)
                .totalElements(transactionPage.getTotalElements())
                .totalPages(transactionPage.getTotalPages())
                .build();
    }

    @Override
    public ResponseDTO getTransactionById(UUID id) {

        Transaction transaction = repository.findById(id).orElseThrow(() -> new NotFoundException("Transaction not found"));

        TransactionDTO transactionDTO = mapper.entityToDto(transaction);

        transactionDTO.setUser(null);
        transactionDTO.setProduct(null);
        transactionDTO.setSupplier(null);

        return ResponseDTO.builder()
                .status(200)
                .message("success")
                .transaction(transactionDTO)
                .build();
    }

    @Override
    public ResponseDTO getAllTransactionsByMonthAndYear(int month, int year) {
        List<Transaction> transactions = repository.findAll(TransactionFilter.byMonthAndYear(month, year));
        List<TransactionDTO> transactionDTOS = mapper.entityToListDto(transactions);
        transactionDTOS.forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setProduct(null);
            transactionDTO.setSupplier(null);
        });

        return ResponseDTO.builder()
                .status(200)
                .message("success")
                .transactions(transactionDTOS)
                .build();
    }

    @Override
    public ResponseDTO updateTransaction(UUID transactionId, TransactionStatus transactionStatus) {

        Transaction transaction = repository.findById(transactionId).orElseThrow(() -> new NotFoundException("Transaction not found"));
        transaction.setStatus(transactionStatus);
        transaction.setUpdatedAt(Instant.now());

        repository.save(transaction);

        return ResponseDTO.builder()
                .status(200)
                .message("Transaction updated")
                .build();
    }
}
