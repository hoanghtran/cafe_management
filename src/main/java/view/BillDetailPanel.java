package view;

import dao.BillDAO;
import dao.BillInfoDAO;
import dao.DrinkDAO;
import model.Bill;
import model.BillInfo;
import model.Drink;
import util.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class BillDetailPanel extends JFrame {

    private Bill bill;
    private JTable tblOrder;
    private DefaultTableModel orderTableModel;
    private JComboBox<Drink> cbProduct;
    private JTextField txtQuantity;
    private JButton btnAddItem, btnRefresh, btnPay;

    public BillDetailPanel(Bill bill) {
        this.bill = bill;
        setTitle("Chi tiết hóa đơn ID: " + bill.getId());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        loadOrderData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel hiển thị thông tin cơ bản của hóa đơn
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.add(new JLabel("Bàn: " + bill.getIdTable()));
        infoPanel.add(new JLabel("Ngày CheckIn: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(bill.getDateCheckIn())));
        add(infoPanel, BorderLayout.NORTH);

        // Table hiển thị danh sách món order
        orderTableModel = new DefaultTableModel(new Object[]{"ID", "Món", "Số lượng", "Đơn giá", "Thành tiền"}, 0);
        tblOrder = new JTable(orderTableModel);
        add(new JScrollPane(tblOrder), BorderLayout.CENTER);

        // Panel thêm món order và thanh toán
        JPanel addPanel = new JPanel(new FlowLayout());
        cbProduct = new JComboBox<>();
        List<Drink> drinks = DrinkDAO.getListFood();
        for (Drink d : drinks) {
            cbProduct.addItem(d);
        }
        addPanel.add(new JLabel("Món:"));
        addPanel.add(cbProduct);
        addPanel.add(new JLabel("Số lượng:"));
        txtQuantity = new JTextField(5);
        addPanel.add(txtQuantity);
        btnAddItem = new JButton("Thêm món");
        addPanel.add(btnAddItem);
        btnRefresh = new JButton("Làm mới");
        addPanel.add(btnRefresh);
        btnPay = new JButton("Thanh toán");
        addPanel.add(btnPay);
        add(addPanel, BorderLayout.SOUTH);

        btnAddItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addOrderItem();
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadOrderData();
            }
        });

        btnPay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payBill();
            }
        });
    }

    private void addOrderItem() {
        Drink selectedDrink = (Drink) cbProduct.getSelectedItem();
        String quantityStr = txtQuantity.getText().trim();
        if (selectedDrink == null || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món và nhập số lượng");
            return;
        }
        try {
            int quantity = Integer.parseInt(quantityStr);
            model.BillInfo bi = new model.BillInfo();
            bi.setIdBill(bill.getId());
            bi.setIdFood(selectedDrink.getId());
            bi.setCount(quantity);
            if (BillInfoDAO.insertBillInfo(bi)) {
                // Cập nhật tổng tiền của bill
                BillDAO.updateBillTotalPrice(bill.getId());
                JOptionPane.showMessageDialog(this, "Thêm món thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm món thất bại!");
            }
            loadOrderData();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
        }
    }

    private void loadOrderData() {
        orderTableModel.setRowCount(0);
        String sql = "SELECT bi.id, d.name, d.price, bi.count, (d.price * bi.count) AS total "
                + "FROM BillInfo bi JOIN Drink d ON bi.idFood = d.id WHERE bi.idBill = " + bill.getId();
        try (Connection conn = DBConnection.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                orderTableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("count"),
                    rs.getFloat("price"),
                    rs.getFloat("total")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void payBill() {
        int confirm = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán hóa đơn?");
        if (confirm == JOptionPane.YES_OPTION) {
            bill.setDateCheckOut(new java.util.Date());
            bill.setStatus(1);
            if (BillDAO.checkOut(bill)) {
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
                dispose(); // Đóng cửa sổ chi tiết sau khi thanh toán
            } else {
                JOptionPane.showMessageDialog(this, "Thanh toán thất bại!");
            }
        }
    }
}
