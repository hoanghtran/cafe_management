package view;

import dao.AccountDAO;
import model.Account;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TaiKhoanPanel extends JPanel {
    private JTable tblAccounts;
    private DefaultTableModel accountTableModel;
    private JTextField txtUsername, txtDisplayName, txtPassword, txtType;
    private JButton btnThem, btnSua, btnXoa, btnReset, btnLamMoi;
    
    public TaiKhoanPanel() {
        setLayout(new BorderLayout());
        JLabel lblTitle = new JLabel("Quản lý tài khoản", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitle, BorderLayout.NORTH);
        
        accountTableModel = new DefaultTableModel(new Object[]{"Username", "DisplayName", "Password", "Type"}, 0);
        tblAccounts = new JTable(accountTableModel);
        JScrollPane scrollPane = new JScrollPane(tblAccounts);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel inputPanel = new JPanel(new GridLayout(5,2,5,5));
        inputPanel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        inputPanel.add(txtUsername);
        
        inputPanel.add(new JLabel("DisplayName:"));
        txtDisplayName = new JTextField();
        inputPanel.add(txtDisplayName);
        
        inputPanel.add(new JLabel("Password:"));
        txtPassword = new JTextField();
        inputPanel.add(txtPassword);
        
        inputPanel.add(new JLabel("Type:"));
        txtType = new JTextField();
        inputPanel.add(txtType);
        
        btnLamMoi = new JButton("Làm mới");
        inputPanel.add(btnLamMoi);
        
        add(inputPanel, BorderLayout.SOUTH);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnReset = new JButton("Reset Password");
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnReset);
        add(buttonPanel, BorderLayout.NORTH);
        
        loadAccountData();
        
        tblAccounts.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                int row = tblAccounts.getSelectedRow();
                if(row >= 0) {
                    txtUsername.setText(accountTableModel.getValueAt(row, 0).toString());
                    txtDisplayName.setText(accountTableModel.getValueAt(row, 1).toString());
                    txtPassword.setText(accountTableModel.getValueAt(row, 2).toString());
                    txtType.setText(accountTableModel.getValueAt(row, 3).toString());
                }
            }
        });
        
        btnThem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Account acc = new Account(txtUsername.getText().trim(), txtDisplayName.getText().trim(), txtPassword.getText().trim(), Integer.parseInt(txtType.getText().trim()));
                if(AccountDAO.insertAccount(acc)){
                    JOptionPane.showMessageDialog(TaiKhoanPanel.this, "Thêm tài khoản thành công!");
                    loadAccountData();
                } else {
                    JOptionPane.showMessageDialog(TaiKhoanPanel.this, "Thêm tài khoản thất bại!");
                }
            }
        });
        
        btnSua.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Account acc = new Account(txtUsername.getText().trim(), txtDisplayName.getText().trim(), txtPassword.getText().trim(), Integer.parseInt(txtType.getText().trim()));
                if(AccountDAO.updateAccount(acc)){
                    JOptionPane.showMessageDialog(TaiKhoanPanel.this, "Sửa tài khoản thành công!");
                    loadAccountData();
                } else {
                    JOptionPane.showMessageDialog(TaiKhoanPanel.this, "Sửa tài khoản thất bại!");
                }
            }
        });
        
        btnXoa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                int confirm = JOptionPane.showConfirmDialog(TaiKhoanPanel.this, "Bạn có chắc chắn xóa tài khoản: " + username + " ?");
                if(confirm == JOptionPane.YES_OPTION){
                    if(AccountDAO.deleteAccount(username)){
                        JOptionPane.showMessageDialog(TaiKhoanPanel.this, "Xóa tài khoản thành công!");
                        loadAccountData();
                    } else {
                        JOptionPane.showMessageDialog(TaiKhoanPanel.this, "Xóa tài khoản thất bại!");
                    }
                }
            }
        });
        
        btnReset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String newPassword = JOptionPane.showInputDialog(TaiKhoanPanel.this, "Nhập mật khẩu mới:");
                if(newPassword != null && !newPassword.trim().isEmpty()){
                    if(AccountDAO.resetPassword(username, newPassword.trim())){
                        JOptionPane.showMessageDialog(TaiKhoanPanel.this, "Reset mật khẩu thành công!");
                        loadAccountData();
                    } else {
                        JOptionPane.showMessageDialog(TaiKhoanPanel.this, "Reset mật khẩu thất bại!");
                    }
                }
            }
        });
        
        btnLamMoi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                txtUsername.setText("");
                txtDisplayName.setText("");
                txtPassword.setText("");
                txtType.setText("");
            }
        });
    }
    
    private void loadAccountData() {
        accountTableModel.setRowCount(0);
        List<Account> list = AccountDAO.getListAccount();
        for(Account a : list) {
            accountTableModel.addRow(new Object[]{a.getUsername(), a.getDisplayName(), a.getPassword(), a.getType()});
        }
    }
}
