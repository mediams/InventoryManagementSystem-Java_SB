package org.kariev.inventorymgtsystem.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.kariev.inventorymgtsystem.models.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class TransactionFilter {

    private TransactionFilter() {
    }

    public static Specification<Transaction> byFilter(String rawSearch) {
        return (root, query, cb) -> {
            if (rawSearch == null || rawSearch.isBlank()) {
                return cb.conjunction();
            }

            if (query != null) {
                query.distinct(true);
            }

            String pattern = "%" + escapeLike(rawSearch.trim().toLowerCase(Locale.ROOT)) + "%";

            Join<Transaction, User> user = root.join("user", JoinType.LEFT);
            Join<Transaction, Supplier> supplier = root.join("supplier", JoinType.LEFT);
            Join<Transaction, Product> product = root.join("product", JoinType.LEFT);
            Join<Product, Category> category = product.join("category", JoinType.LEFT);

            List<Predicate> or = new ArrayList<>();

            or.add(cb.like(cb.lower(cb.coalesce(root.get("description"), "")), pattern, '\\'));
            or.add(cb.like(cb.lower(cb.coalesce(root.get("note"), "")), pattern, '\\'));

            or.add(cb.like(cb.lower(cb.coalesce(root.get("status"), "")), pattern, '\\'));
            or.add(cb.like(cb.lower(cb.coalesce(root.get("transactionType"), "")), pattern, '\\'));

            or.add(cb.like(cb.lower(cb.coalesce(user.get("name"), "")), pattern, '\\'));
            or.add(cb.like(cb.lower(cb.coalesce(user.get("email"), "")), pattern, '\\'));
            or.add(cb.like(cb.lower(cb.coalesce(user.get("phoneNumber"), "")), pattern, '\\'));

            or.add(cb.like(cb.lower(cb.coalesce(supplier.get("name"), "")), pattern, '\\'));
            or.add(cb.like(cb.lower(cb.coalesce(supplier.get("contactInfo"), "")), pattern, '\\'));

            or.add(cb.like(cb.lower(cb.coalesce(product.get("name"), "")), pattern, '\\'));
            or.add(cb.like(cb.lower(cb.coalesce(product.get("sku"), "")), pattern, '\\'));
            or.add(cb.like(cb.lower(cb.coalesce(product.get("description"), "")), pattern, '\\'));

            or.add(cb.like(cb.lower(cb.coalesce(category.get("name"), "")), pattern, '\\'));

            return cb.or(or.toArray(new Predicate[0]));
        };
    }

    public static Specification<Transaction> byMonthAndYear(int month, int year) {
        return (root, query, cb) -> {
            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.plusMonths(1);

            Path<LocalDateTime> createdAt = root.get("createdAt");
            Predicate gte = cb.greaterThanOrEqualTo(createdAt, start.atStartOfDay());
            Predicate lt = cb.lessThan(createdAt, end.atStartOfDay());
            return cb.and(gte, lt);
        };
    }

    private static String escapeLike(String s) {
        return s.replace("\\", "\\\\")
                .replace("%", "\\%")
                .replace("_", "\\_");
    }
}
