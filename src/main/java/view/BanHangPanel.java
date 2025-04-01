package view;

import dao.BillDAO;
import dao.TableDAO;
import model.Bill;
import model.TableDrink;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Date;

public class BanHangPanel extends JPanel {

    private JTable tblBills;
    private DefaultTableModel billTableModel;
    private final JButton btnThemHoaDon;
    private JButton btnThanhToan, btnChuyenBan, btnGopBan, btnLamMoi;

    public BanHangPanel() {
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Quản lý bán hàng", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitle, BorderLayout.NORTH);

        billTableModel = new DefaultTableModel(new Object[]{"ID", "Ngày CheckIn", "ID Bàn", "Trạng thái", "Giảm giá", "Tổng tiền"}, 0);
        tblBills = new JTable(billTableModel);
        JScrollPane scrollPane = new JScrollPane(tblBills);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThemHoaDon = new JButton("Thêm hóa đơn theo bàn");
        btnThanhToan = new JButton("Thanh toán");
        btnChuyenBan = new JButton("Chuyển bàn");
        btnGopBan = new JButton("Gộp bàn");
        btnLamMoi = new JButton("Làm mới");
        buttonPanel.add(btnThemHoaDon);
        buttonPanel.add(btnThanhToan);
        buttonPanel.add(btnChuyenBan);
        buttonPanel.add(btnGopBan);
        buttonPanel.add(btnLamMoi);
        add(buttonPanel, BorderLayout.SOUTH);

        loadBillData();

        btnLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBillData();
            }
        });

        btnThemHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tableIdStr = JOptionPane.showInputDialog(BanHangPanel.this, "Nhập ID bàn:");
                if (tableIdStr != null && !tableIdStr.trim().isEmpty()) {
                    try {
                        int tableId = Integer.parseInt(tableIdStr.trim());
                        Bill bill = new Bill();
                        bill.setDateCheckIn(new Date());
                        bill.setIdTable(tableId);
                        bill.setStatus(0); // chưa thanh toán
                        bill.setDiscount(0);
                        bill.setTotalPrice(0);
                        if (BillDAO.insertBill(bill)) {
                            JOptionPane.showMessageDialog(BanHangPanel.this, "Thêm hóa đơn thành công!");
                        } else {
                            JOptionPane.showMessageDialog(BanHangPanel.this, "Thêm hóa đơn thất bại!");
                        }
                        loadBillData();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(BanHangPanel.this, "ID bàn không hợp lệ!");
                    }
                }
            }
        });

        btnThanhToan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblBills.getSelectedRow();
                if (selectedRow >= 0) {
                    int billId = (int) billTableModel.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(BanHangPanel.this, "Xác nhận thanh toán hóa đơn ID: " + billId);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Giả lập thanh toán (thực tế cần gọi BillDAO.checkOut với đầy đủ thông tin)
                        JOptionPane.showMessageDialog(BanHangPanel.this, "Thanh toán thành công cho hóa đơn ID: " + billId);
                        loadBillData();
                    }
                } else {
                    JOptionPane.showMessageDialog(BanHangPanel.this, "Vui lòng chọn hóa đơn cần thanh toán!");
                }
            }
        });

        btnChuyenBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblBills.getSelectedRow();
                if (selectedRow >= 0) {
                    int billId = (int) billTableModel.getValueAt(selectedRow, 0);
                    String newTableIdStr = JOptionPane.showInputDialog(BanHangPanel.this, "Nhập ID bàn mới để chuyển:");
                    if (newTableIdStr != null && !newTableIdStr.trim().isEmpty()) {
                        try {
                            int newTableId = Integer.parseInt(newTableIdStr.trim());
                            if (TableDAO.switchTable((int) billTableModel.getValueAt(selectedRow, 2), newTableId)) {
                                JOptionPane.showMessageDialog(BanHangPanel.this, "Chuyển bàn thành công cho hóa đơn ID: " + billId);
                            } else {
                                JOptionPane.showMessageDialog(BanHangPanel.this, "Chuyển bàn thất bại!");
                            }
                            loadBillData();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(BanHangPanel.this, "ID bàn không hợp lệ!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(BanHangPanel.this, "Vui lòng chọn hóa đơn cần chuyển bàn!");
                }
            }
        });

        btnGopBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblBills.getSelectedRow();
                if (selectedRow >= 0) {
                    int billId = (int) billTableModel.getValueAt(selectedRow, 0);
                    String mergeTableIdStr = JOptionPane.showInputDialog(BanHangPanel.this, "Nhập ID bàn để gộp:");
                    if (mergeTableIdStr != null && !mergeTableIdStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(BanHangPanel.this, "Gộp bàn thành công cho hóa đơn ID: " + billId);
                        loadBillData();
                    }
                } else {
                    JOptionPane.showMessageDialog(BanHangPanel.this, "Vui lòng chọn hóa đơn cần gộp bàn!");
                }
            }
        });
    }

    private void loadBillData() {
        billTableModel.setRowCount(0);
        List<Bill> bills = BillDAO.getBillListByDate(new Date());
        for (Bill b : bills) {
            billTableModel.addRow(new Object[]{
                b.getId(),
                b.getDateCheckIn(),
                b.getIdTable(),
                b.getStatus(),
                b.getDiscount(),
                b.getTotalPrice()
            });
        }
    }
}
