package view;

import dao.AccountDAO;
import model.Account;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AccountInfoPanel extends JPanel {

    private Account account;
    private JTextField txtUsername, txtDisplayName, txtPassword;
    private JButton btnCapNhat;

    public AccountInfoPanel(Account account) {
        this.account = account;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitle = new JLabel("Thông tin tài khoản cá nhân");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        txtUsername = new JTextField(20);
        txtUsername.setText(account.getUsername());
        txtUsername.setEditable(false);
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("DisplayName:"), gbc);
        gbc.gridx = 1;
        txtDisplayName = new JTextField(20);
        txtDisplayName.setText(account.getDisplayName());
        add(txtDisplayName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        txtPassword = new JTextField(20);
        txtPassword.setText(account.getPassword());
        add(txtPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        btnCapNhat = new JButton("Cập nhật thông tin");
        add(btnCapNhat, gbc);

        btnCapNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                account.setDisplayName(txtDisplayName.getText().trim());
                account.setPassword(txtPassword.getText().trim());
                if (AccountDAO.updateAccount(account)) {
                    JOptionPane.showMessageDialog(AccountInfoPanel.this, "Cập nhật thông tin thành công!");
                } else {
                    JOptionPane.showMessageDialog(AccountInfoPanel.this, "Cập nhật thông tin thất bại!");
                }
            }
        });
    }
}
