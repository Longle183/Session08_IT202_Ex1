package com.restaurant.ex1.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("""
        execution(* com.restaurant.ex1.service.ProductService.inspectInventory(..))
    """)
    public Object measureTime(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println(
                "Execution time: "
                        + (end - start)
                        + " ms"
        );

        return result;
    }
}