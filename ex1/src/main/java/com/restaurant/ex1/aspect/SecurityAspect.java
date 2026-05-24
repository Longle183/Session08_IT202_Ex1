package com.restaurant.ex1.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Before("""
        execution(* com.restaurant.inventory.service.ProductService.deleteProduct(..))
        && args(id, username, role)
    """)
    public void checkAdmin(
            Long id,
            String username,
            String role
    ) {

        if (!"ADMIN".equals(role)) {

            throw new SecurityException(
                    "Chỉ ADMIN được phép xóa sản phẩm"
            );
        }
    }
}