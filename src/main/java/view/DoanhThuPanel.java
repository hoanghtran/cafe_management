package view;

import dao.RevenueDAO;
import model.Revenue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.List;
import java.util.Date;

public class DoanhThuPanel extends JPanel {
    private JTextField txtFromDate, txtToDate;
    private JButton btnXem;
    private JTable tblRevenue;
    private DefaultTableModel revenueTableModel;
    
    public DoanhThuPanel() {
        setLayout(new BorderLayout());
        JLabel lblTitle = new JLabel("Xem doanh thu", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitle, BorderLayout.NORTH);
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Từ (yyyy-MM-dd):"));
        txtFromDate = new JTextField(10);
        topPanel.add(txtFromDate);
        topPanel.add(new JLabel("Đến (yyyy-MM-dd):"));
        txtToDate = new JTextField(10);
        topPanel.add(txtToDate);
        btnXem = new JButton("Hiển thị");
        topPanel.add(btnXem);
        add(topPanel, BorderLayout.NORTH);
        
        revenueTableModel = new DefaultTableModel(new Object[]{"Ngày", "Tổng doanh thu"}, 0);
        tblRevenue = new JTable(revenueTableModel);
        JScrollPane scrollPane = new JScrollPane(tblRevenue);
        add(scrollPane, BorderLayout.CENTER);
        
        btnXem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                loadRevenueData();
            }
        });
    }
    
    private void loadRevenueData() {
        revenueTableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fromDate = sdf.parse(txtFromDate.getText().trim());
            Date toDate = sdf.parse(txtToDate.getText().trim());
            List<Revenue> list = RevenueDAO.statisticalBill(fromDate, toDate);
            for(Revenue r : list) {
                revenueTableModel.addRow(new Object[]{sdf.format(r.getDate()), r.getTotalRevenue()});
            }
        } catch(ParseException ex) {
            JOptionPane.showMessageDialog(DoanhThuPanel.this, "Định dạng ngày không hợp lệ!");
        }
    }
}
