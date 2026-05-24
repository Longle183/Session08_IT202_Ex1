package com.restaurant.ex1.aspect;

import com.restaurant.ex1.entity.InventoryLog;
import com.restaurant.ex1.repository.InventoryLogRepository;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LoggingAspect {

    private final InventoryLogRepository repository;

    public LoggingAspect(
            InventoryLogRepository repository
    ) {
        this.repository = repository;
    }

    @AfterReturning("""
        execution(* com.restaurant.ex1.service.ProductService.stockIn(..))
        && args(request, username, role)
    """)
    public void logStockIn(
            Object request,
            String username,
            String role
    ) {

        InventoryLog log = new InventoryLog();

        log.setTimestamp(LocalDateTime.now());
        log.setUsername(username);
        log.setAction("STOCK_IN");
        log.setDetail("Nhập kho thành công");

        repository.save(log);
    }

    @AfterReturning("""
        execution(* com.restaurant.inventory.service.ProductService.stockOut(..))
        && args(request, username, role)
    """)
    public void logStockOut(
            Object request,
            String username,
            String role
    ) {

        InventoryLog log = new InventoryLog();

        log.setTimestamp(LocalDateTime.now());
        log.setUsername(username);
        log.setAction("STOCK_OUT");
        log.setDetail("Xuất kho thành công");

        repository.save(log);
    }
}