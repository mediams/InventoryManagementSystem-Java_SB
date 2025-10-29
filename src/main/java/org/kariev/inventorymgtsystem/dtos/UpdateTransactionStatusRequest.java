package org.kariev.inventorymgtsystem.dtos;

import org.kariev.inventorymgtsystem.enums.TransactionStatus;

public record UpdateTransactionStatusRequest(TransactionStatus transactionStatus) {
}