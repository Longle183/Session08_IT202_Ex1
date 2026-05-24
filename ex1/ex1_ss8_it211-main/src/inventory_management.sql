CREATE DATABASE inventory_management;

USE inventory_management;

CREATE TABLE products (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          sku VARCHAR(100) NOT NULL UNIQUE,
                          name VARCHAR(255) NOT NULL,
                          quantity INT NOT NULL,
                          price DOUBLE NOT NULL
);

CREATE TABLE inventory_logs (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                timestamp DATETIME NOT NULL,
                                username VARCHAR(100) NOT NULL,
                                action VARCHAR(100) NOT NULL,
                                detail TEXT NOT NULL
);

INSERT INTO products (sku, name, quantity, price)
VALUES
    ('IPHONE15', 'iPhone 15 Pro Max', 100, 35000000),
    ('SAMSUNG-S24', 'Samsung Galaxy S24', 80, 27000000),
    ('MACBOOK-M3', 'Macbook Pro M3', 40, 52000000),
    ('AIRPODS-PRO', 'Airpods Pro', 150, 6500000);


-- Xem toàn bộ sản phẩm
SELECT * FROM products;

-- Xem toàn bộ log
SELECT * FROM inventory_logs;

-- Test nhập kho
UPDATE products
SET quantity = quantity + 10
WHERE sku = 'IPHONE15';

-- Test xuất kho
UPDATE products
SET quantity = quantity - 5
WHERE sku = 'IPHONE15'
  AND quantity >= 5;