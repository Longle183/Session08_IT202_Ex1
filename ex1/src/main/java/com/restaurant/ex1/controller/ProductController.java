package com.restaurant.ex1.controller;
import com.restaurant.ex1.dto.StockRequest;
import com.restaurant.ex1.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/stock-in")
    public ResponseEntity<?> stockIn(
            @Valid @RequestBody StockRequest request,
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {

        service.stockIn(request, username, role);

        return ResponseEntity.ok(
                "Nhập kho thành công"
        );
    }

    @PostMapping("/stock-out")
    public ResponseEntity<?> stockOut(
            @Valid @RequestBody StockRequest request,
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {

        service.stockOut(request, username, role);

        return ResponseEntity.ok(
                "Xuất kho thành công"
        );
    }

    @GetMapping("/inspect")
    public ResponseEntity<?> inspect(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {

        return ResponseEntity.ok(
                service.inspectInventory(username, role)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role
    ) {

        service.deleteProduct(id, username, role);

        return ResponseEntity.ok(
                "Xóa sản phẩm thành công"
        );
    }
}