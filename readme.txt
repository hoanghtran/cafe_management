Quan Ly Cafe - Hệ thống Quản Lý Cafe
=====================================

Mô tả dự án:
-------------
Đây là dự án Quản Lý Cafe được xây dựng theo mô hình Model – View – DAO – Util với giao diện Swing. 
Hệ thống hỗ trợ các chức năng chính:
  - Đăng nhập (LoginView)
  - Dashboard với các chức năng:
      + Quản lý bán hàng (BanHangPanel)
      + Quản lý tài khoản (TaiKhoanPanel)
      + Quản lý danh mục thực đơn (DanhMucThucDonPanel)
      + Quản lý thực đơn (ThucDonPanel)
      + Quản lý bàn (BanPanel)
      + Xem doanh thu (DoanhThuPanel)
      + Thông tin tài khoản cá nhân (AccountInfoPanel)

Yêu cầu hệ thống:
------------------
- JDK 8 trở lên.
- MySQL Server (đã tạo database theo file init_db.sql bên dưới).
- MySQL Connector/J (thêm vào thư mục lib và cấu hình CLASSPATH cho dự án).
- IDE hỗ trợ Java (Eclipse, IntelliJ IDEA, NetBeans, v.v).

Cấu trúc thư mục dự án:
------------------------
QuanLyCafe/
├── src/
│   ├── model/
│   │    ... (các file model: Account.java, Admin.java, Employee.java, Drink.java, BillInfo.java, Bill.java, Category.java, TableDrink.java, Revenue.java)
│   ├── dao/
│   │    ... (các file DAO: AccountDAO.java, DrinkDAO.java, BillDAO.java, BillInfoDAO.java, CategoryDAO.java, TableDAO.java, RevenueDAO.java)
│   ├── util/
│   │    DBConnection.java
│   └── view/
│        LoginView.java
│        DashboardView.java
│        BanHangPanel.java
│        TaiKhoanPanel.java
│        DanhMucThucDonPanel.java
│        ThucDonPanel.java
│        BanPanel.java
│        DoanhThuPanel.java
│        AccountInfoPanel.java
├── lib/ (MySQL Connector/J và các dependency khác)
├── README.txt
└── init_db.sql

Cách cài đặt và chạy:
----------------------
1. Tạo database:
   - Chạy file init_db.sql trong MySQL Workbench hoặc công cụ quản lý MySQL khác để tạo database 'cafedb' và các bảng cần thiết.
   
2. Cập nhật kết nối cơ sở dữ liệu:
   - Mở file `util/DBConnection.java` và kiểm tra/cập nhật URL, USER, PASSWORD phù hợp với cấu hình MySQL của bạn.
   
3. Build và chạy dự án:
   - Biên dịch toàn bộ mã nguồn.
   - Chạy class chính `yathh.me.quanlycafe.QuanLyCafe` (hàm main đã được viết như bạn đưa ra).
   
4. Đăng nhập:
   - Sử dụng tài khoản mẫu được khởi tạo (ví dụ: username: admin, password: 123) để đăng nhập và truy cập hệ thống.

Chú ý:
--------
- Hệ thống được xây dựng hoàn chỉnh với giao diện GUI đồng bộ với các chức năng CRUD. Bạn có thể mở rộng thêm theo yêu cầu trong tương lai.
- Nếu có lỗi hoặc cần điều chỉnh, kiểm tra lại các tham số kết nối DB và đảm bảo các dependency đã được thêm đúng.

Cảm ơn bạn đã sử dụng dự án này!

