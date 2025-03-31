package view;

import dao.BillDAO;
import model.Bill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BillPanel extends JPanel {
    private JTable billTable;
    private DefaultTableModel tableModel;
    private BillDAO billDAO = new BillDAO();

    public BillPanel() {
        setLayout(new BorderLayout());

        // Tạo bảng hiển thị hóa đơn
        tableModel = new DefaultTableModel(new String[]{"ID", "Bàn", "Tổng tiền"}, 0);
        billTable = new JTable(tableModel);
        loadBillData();

        // Nút thanh toán
        JButton btnPay = new JButton("Thanh toán");
        btnPay.addActionListener(e -> processPayment());

        // Nút xóa hóa đơn
        JButton btnDelete = new JButton("Xóa hóa đơn");
        btnDelete.addActionListener(e -> deleteBill());

        JPanel panelButtons = new JPanel();
        panelButtons.add(btnPay);
        panelButtons.add(btnDelete);

        add(new JScrollPane(billTable), BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
    }

    private void loadBillData() {
        tableModel.setRowCount(0);
        for (Bill bill : billDAO.getAllBills()) {
            tableModel.addRow(new Object[]{bill.getId(), bill.getTableId(), bill.getTotal()});
        }
    }

    private void processPayment() {
        int selectedRow = billTable.getSelectedRow();
        if (selectedRow != -1) {
            int billId = (int) billTable.getValueAt(selectedRow, 0);
            Bill bill = billDAO.getBillById(billId);
            if (bill != null) {
                JOptionPane.showMessageDialog(this, "Thanh toán hóa đơn ID: " + bill.getId());
                billDAO.payBill(billId);
                loadBillData();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để thanh toán.");
        }
    }

    private void deleteBill() {
        int selectedRow = billTable.getSelectedRow();
        if (selectedRow != -1) {
            int billId = (int) billTable.getValueAt(selectedRow, 0);
            billDAO.deleteBill(billId);
            loadBillData();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để xóa.");
        }
    }
}
