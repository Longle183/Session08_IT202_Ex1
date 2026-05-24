package com.restaurant.ex1.service;

import com.restaurant.ex1.dto.StockRequest;
import com.restaurant.ex1.entity.Product;
import com.restaurant.ex1.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void stockIn(
            StockRequest request,
            String username,
            String role
    ) {

        repository.stockIn(
                request.getSku(),
                request.getQuantity()
        );
    }

    @Transactional
    public void stockOut(
            StockRequest request,
            String username,
            String role
    ) {

        Product product = repository
                .findBySku(request.getSku())
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy sản phẩm"));

        if (product.getQuantity() < request.getQuantity()) {
            throw new RuntimeException(
                    "Không đủ số lượng tồn kho"
            );
        }

        repository.stockOut(
                request.getSku(),
                request.getQuantity()
        );
    }

    public String inspectInventory(
            String username,
            String role
    ) {

        List<Product> products = repository.findAll();

        int totalQuantity = products.stream()
                .mapToInt(Product::getQuantity)
                .sum();

        double totalValue = products.stream()
                .mapToDouble(
                        p -> p.getPrice() * p.getQuantity()
                )
                .sum();

        return "Total Quantity = "
                + totalQuantity
                + " | Total Value = "
                + totalValue;
    }

    public void deleteProduct(
            Long id,
            String username,
            String role
    ) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy sản phẩm"));

        repository.delete(product);
    }
}