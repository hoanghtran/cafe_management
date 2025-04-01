package view;

import model.Account;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainView extends JFrame {
    private Account account;
    private JLabel lblWelcome;

    public MainView(Account account) {
        this.account = account;
        setTitle("Quản Lý Cafe - Main");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        lblWelcome = new JLabel("Chào mừng " + account.getDisplayName(), SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(lblWelcome, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnManageAccount = new JButton("Quản lý tài khoản");
        JButton btnManageOrder = new JButton("Quản lý bán hàng");
        JButton btnManageMenu = new JButton("Quản lý thực đơn");
        JButton btnViewRevenue = new JButton("Xem doanh thu");
        JButton btnLogout = new JButton("Đăng xuất");

        buttonPanel.add(btnManageAccount);
        buttonPanel.add(btnManageOrder);
        buttonPanel.add(btnManageMenu);
        buttonPanel.add(btnViewRevenue);
        buttonPanel.add(btnLogout);

        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);

        // Các action listener demo (các chức năng có thể được triển khai chi tiết sau)
        btnManageAccount.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainView.this, "Chức năng quản lý tài khoản chưa được triển khai.");
            }
        });

        btnManageOrder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainView.this, "Chức năng quản lý bán hàng chưa được triển khai.");
            }
        });

        btnManageMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainView.this, "Chức năng quản lý thực đơn chưa được triển khai.");
            }
        });

        btnViewRevenue.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainView.this, "Chức năng xem doanh thu chưa được triển khai.");
            }
        });

        btnLogout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(MainView.this, "Bạn có chắc chắn muốn đăng xuất?");
                if(confirm == JOptionPane.YES_OPTION){
                    new LoginView().setVisible(true);
                    dispose();
                }
            }
        });
    }
}
