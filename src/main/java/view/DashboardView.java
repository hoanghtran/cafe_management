package view;

import model.Account;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardView extends JFrame {
    private Account account;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public DashboardView(Account account) {
        this.account = account;
        setTitle("Quản Lý Cafe - Dashboard");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }
    
    private void initComponents() {
        // Tạo menu bar
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuHeThong = new JMenu("Hệ Thống");
        JMenuItem menuItemThongTinTK = new JMenuItem("Thông tin tài khoản");
        JMenuItem menuItemDangXuat = new JMenuItem("Đăng xuất");
        menuHeThong.add(menuItemThongTinTK);
        menuHeThong.add(menuItemDangXuat);
        
        JMenu menuQuanLy = new JMenu("Quản Lý");
        JMenuItem menuItemBanHang = new JMenuItem("Quản lý bán hàng");
        JMenuItem menuItemTaiKhoan = new JMenuItem("Quản lý tài khoản");
        JMenuItem menuItemDanhMuc = new JMenuItem("Quản lý danh mục thực đơn");
        JMenuItem menuItemThucDon = new JMenuItem("Quản lý thực đơn");
        JMenuItem menuItemBan = new JMenuItem("Quản lý bàn");
        JMenuItem menuItemDoanhThu = new JMenuItem("Xem doanh thu");
        menuQuanLy.add(menuItemBanHang);
        menuQuanLy.add(menuItemTaiKhoan);
        menuQuanLy.add(menuItemDanhMuc);
        menuQuanLy.add(menuItemThucDon);
        menuQuanLy.add(menuItemBan);
        menuQuanLy.add(menuItemDoanhThu);
        
        menuBar.add(menuHeThong);
        menuBar.add(menuQuanLy);
        setJMenuBar(menuBar);
        
        // Tạo mainPanel với CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(new BanHangPanel(), "BanHang");
        mainPanel.add(new TaiKhoanPanel(), "TaiKhoan");
        mainPanel.add(new DanhMucThucDonPanel(), "DanhMuc");
        mainPanel.add(new ThucDonPanel(), "ThucDon");
        mainPanel.add(new BanPanel(), "Ban");
        mainPanel.add(new DoanhThuPanel(), "DoanhThu");
        mainPanel.add(new AccountInfoPanel(account), "ThongTinTK");
        
        add(mainPanel);
        
        // Định nghĩa hành động cho menu
        menuItemBanHang.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "BanHang");
            }
        });
        
        menuItemTaiKhoan.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "TaiKhoan");
            }
        });
        
        menuItemDanhMuc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "DanhMuc");
            }
        });
        
        menuItemThucDon.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "ThucDon");
            }
        });
        
        menuItemBan.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Ban");
            }
        });
        
        menuItemDoanhThu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "DoanhThu");
            }
        });
        
        menuItemThongTinTK.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "ThongTinTK");
            }
        });
        
        menuItemDangXuat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(DashboardView.this, "Bạn có chắc chắn muốn đăng xuất?");
                if(confirm == JOptionPane.YES_OPTION){
                    new LoginView().setVisible(true);
                    dispose();
                }
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                DashboardView dv = new DashboardView(new model.Account("admin", "Admin", "123", 1));
                dv.setVisible(true);
            }
        });
    }
}
