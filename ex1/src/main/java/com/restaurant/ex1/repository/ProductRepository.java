package com.restaurant.ex1.repository;

import com.restaurant.ex1.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    @Modifying
    @Query("""
        update Product p
        set p.quantity = p.quantity + :quantity
        where p.sku = :sku
    """)
    void stockIn(
            @Param("sku") String sku,
            @Param("quantity") Integer quantity
    );

    @Modifying
    @Query("""
        update Product p
        set p.quantity = p.quantity - :quantity
        where p.sku = :sku
    """)
    void stockOut(
            @Param("sku") String sku,
            @Param("quantity") Integer quantity
    );
}