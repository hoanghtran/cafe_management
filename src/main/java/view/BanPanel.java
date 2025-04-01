package view;

import dao.TableDAO;
import model.TableDrink;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BanPanel extends JPanel {
    private JTable tblTables;
    private DefaultTableModel tableModel;
    private JTextField txtTableId, txtTableName, txtStatus;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    
    public BanPanel() {
        setLayout(new BorderLayout());
        JLabel lblTitle = new JLabel("Quản lý bàn", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitle, BorderLayout.NORTH);
        
        tableModel = new DefaultTableModel(new Object[]{"ID", "Tên bàn", "Trạng thái"}, 0);
        tblTables = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblTables);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel inputPanel = new JPanel(new GridLayout(3,2,5,5));
        inputPanel.add(new JLabel("ID:"));
        txtTableId = new JTextField();
        txtTableId.setEditable(false);
        inputPanel.add(txtTableId);
        inputPanel.add(new JLabel("Tên bàn:"));
        txtTableName = new JTextField();
        inputPanel.add(txtTableName);
        inputPanel.add(new JLabel("Trạng thái:"));
        txtStatus = new JTextField();
        inputPanel.add(txtStatus);
        add(inputPanel, BorderLayout.SOUTH);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm bàn");
        btnSua = new JButton("Sửa bàn");
        btnXoa = new JButton("Xóa bàn");
        btnLamMoi = new JButton("Làm mới");
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);
        add(buttonPanel, BorderLayout.NORTH);
        
        loadTableData();
        
        tblTables.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                int row = tblTables.getSelectedRow();
                if(row >= 0){
                    txtTableId.setText(tableModel.getValueAt(row, 0).toString());
                    txtTableName.setText(tableModel.getValueAt(row, 1).toString());
                    txtStatus.setText(tableModel.getValueAt(row, 2).toString());
                }
            }
        });
        
        btnThem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String name = txtTableName.getText().trim();
                String status = txtStatus.getText().trim();
                if(name.isEmpty() || status.isEmpty()){
                    JOptionPane.showMessageDialog(BanPanel.this, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }
                TableDrink table = new TableDrink(0, name, status);
                if(TableDAO.addNewTable(table)){
                    JOptionPane.showMessageDialog(BanPanel.this, "Thêm bàn thành công!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(BanPanel.this, "Thêm bàn thất bại!");
                }
            }
        });
        
        btnSua.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(txtTableId.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(BanPanel.this, "Vui lòng chọn bàn cần sửa!");
                    return;
                }
                int id = Integer.parseInt(txtTableId.getText().trim());
                String name = txtTableName.getText().trim();
                String status = txtStatus.getText().trim();
                if(name.isEmpty() || status.isEmpty()){
                    JOptionPane.showMessageDialog(BanPanel.this, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }
                TableDrink table = new TableDrink(id, name, status);
                if(TableDAO.editTable(table)){
                    JOptionPane.showMessageDialog(BanPanel.this, "Sửa bàn thành công!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(BanPanel.this, "Sửa bàn thất bại!");
                }
            }
        });
        
        btnXoa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(txtTableId.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(BanPanel.this, "Vui lòng chọn bàn cần xóa!");
                    return;
                }
                int id = Integer.parseInt(txtTableId.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(BanPanel.this, "Bạn có chắc chắn xóa bàn này?");
                if(confirm == JOptionPane.YES_OPTION){
                    if(TableDAO.deleteTable(id)){
                        JOptionPane.showMessageDialog(BanPanel.this, "Xóa bàn thành công!");
                        loadTableData();
                    } else {
                        JOptionPane.showMessageDialog(BanPanel.this, "Xóa bàn thất bại!");
                    }
                }
            }
        });
        
        btnLamMoi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                txtTableId.setText("");
                txtTableName.setText("");
                txtStatus.setText("");
            }
        });
    }
    
    private void loadTableData() {
        tableModel.setRowCount(0);
        List<TableDrink> list = TableDAO.loadTableList();
        for(TableDrink t : list) {
            tableModel.addRow(new Object[]{t.getId(), t.getName(), t.getStatus()});
        }
    }
}
