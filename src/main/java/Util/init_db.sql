-- Tạo database mới với tên cafedb (đảm bảo file DBConnection.java sử dụng cùng tên)
CREATE DATABASE IF NOT EXISTS cafedb;
USE cafedb;

---------------------------------------
-- Tạo bảng Account (cho thông tin tài khoản)
---------------------------------------
CREATE TABLE IF NOT EXISTS Account (
    username VARCHAR(100) NOT NULL PRIMARY KEY,
    displayName VARCHAR(100),
    password VARCHAR(100),
    type INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Chèn dữ liệu mẫu vào bảng Account (password đổi về "1")
INSERT INTO Account (username, displayName, password, type) VALUES
('admin1', 'Admin One', '1', 1),
('staff1', 'Staff One', '1', 0),
('staff2', 'Staff Two', '1', 0);

---------------------------------------
-- Tạo bảng TableDrink (cho thông tin bàn cafe)
---------------------------------------
CREATE TABLE IF NOT EXISTS TableDrink (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    status VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Chèn dữ liệu mẫu cho bàn: 10 bàn, bàn 1-3 có người ("Có người"), 4-10 trống ("Trống")
INSERT INTO TableDrink (name, status) VALUES
('Bàn 1', 'Có người'),
('Bàn 2', 'Có người'),
('Bàn 3', 'Có người'),
('Bàn 4', 'Trống'),
('Bàn 5', 'Trống'),
('Bàn 6', 'Trống'),
('Bàn 7', 'Trống'),
('Bàn 8', 'Trống'),
('Bàn 9', 'Trống'),
('Bàn 10', 'Trống');

---------------------------------------
-- Tạo bảng DrinkCategory (cho danh mục món)
---------------------------------------
CREATE TABLE IF NOT EXISTS DrinkCategory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Chèn danh mục theo thứ tự: Trà Sữa, Cafe, Nước Ép, Sinh Tố, Đồ Ăn Vặt
INSERT INTO DrinkCategory (name) VALUES
('Trà Sữa'),
('Cafe'),
('Nước Ép'),
('Sinh Tố'),
('Đồ Ăn Vặt');

---------------------------------------
-- Tạo bảng Drink (cho sản phẩm - món)
---------------------------------------
CREATE TABLE IF NOT EXISTS Drink (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    idCategory INT,
    price FLOAT,
    FOREIGN KEY (idCategory) REFERENCES DrinkCategory(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Chèn dữ liệu mẫu cho các món

-- Món thuộc danh mục "Trà Sữa" (idCategory = 1)
INSERT INTO Drink (name, idCategory, price) VALUES
('Trà sữa socola', 1, 25000),
('Trà sữa dâu', 1, 25000),
('Trà sữa dừa', 1, 25000),
('Trà sữa matcha', 1, 30000),
('Trà sữa trân châu', 1, 30000),
('Trà sữa trái cây', 1, 30000),
('Trà sữa caramel', 1, 30000);

-- Món thuộc danh mục "Cafe" (idCategory = 2)
INSERT INTO Drink (name, idCategory, price) VALUES
('Cafe đen', 2, 25000),
('Cafe sữa đá', 2, 25000),
('Cafe sữa nóng', 2, 25000),
('Cafe dừa', 2, 25000),
('Cafe trứng', 2, 30000),
('Latte', 2, 30000),
('Cappuccino', 2, 30000);

-- Món thuộc danh mục "Nước Ép" (idCategory = 3)
INSERT INTO Drink (name, idCategory, price) VALUES
('Nước ép cam', 3, 30000),
('Nước ép táo', 3, 30000),
('Nước ép dứa', 3, 30000),
('Nước ép dưa hấu', 3, 30000),
('Nước ép bưởi', 3, 30000),
('Nước ép nho', 3, 35000),
('Nước ép dưa gang', 3, 35000),
('Nước ép kiwi', 3, 35000);

-- Món thuộc danh mục "Sinh Tố" (idCategory = 4)
INSERT INTO Drink (name, idCategory, price) VALUES
('Sinh tố xoài', 4, 30000),
('Sinh tố dứa', 4, 30000),
('Sinh tố bơ', 4, 30000),
('Sinh tố cam', 4, 30000),
('Sinh tố dưa hấu', 4, 30000),
('Sinh tố bưởi', 4, 30000),
('Sinh tố chuối', 4, 30000),
('Sinh tố dâu', 4, 30000),
('Sinh tố kiwi', 4, 35000),
('Sinh tố nho', 4, 35000);

-- Món thuộc danh mục "Đồ Ăn Vặt" (idCategory = 5)
INSERT INTO Drink (name, idCategory, price) VALUES
('Hướng dương', 5, 10000),
('Hướng dương vị', 5, 15000),
('Khô bò', 5, 20000),
('Khô gà', 5, 20000),
('Bánh bông lan', 5, 30000),
('Bánh mousse', 5, 30000),
('Bánh tiramisu', 5, 40000);

---------------------------------------
-- Tạo bảng Bill (cho hóa đơn)
---------------------------------------
CREATE TABLE IF NOT EXISTS Bill (
    id INT AUTO_INCREMENT PRIMARY KEY,
    DateCheckIn DATETIME,
    DateCheckOut DATETIME,
    idTable INT,
    status INT,
    discount INT,
    totalPrice FLOAT,
    FOREIGN KEY (idTable) REFERENCES TableDrink(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Chèn một hóa đơn mẫu
INSERT INTO Bill (DateCheckIn, DateCheckOut, idTable, status, discount, totalPrice) VALUES
('2024-04-01 10:00:00', '2024-04-01 10:30:00', 1, 1, 0, 150000);

---------------------------------------
-- Tạo bảng BillInfo (cho chi tiết hóa đơn)
---------------------------------------
CREATE TABLE IF NOT EXISTS BillInfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idBill INT,
    idFood INT,
    count INT,
    FOREIGN KEY (idBill) REFERENCES Bill(id),
    FOREIGN KEY (idFood) REFERENCES Drink(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Chèn chi tiết hóa đơn mẫu (ví dụ: hóa đơn 1, món 1, số lượng 2)
INSERT INTO BillInfo (idBill, idFood, count) VALUES
(1, 1, 2);
