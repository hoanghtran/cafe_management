package view;

import javax.swing.*;
import model.Account;

public class MainForm extends JFrame {
    public MainForm(Account account) {
        setTitle("Quản lý quán café - " + account.getDisplayName());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        // Nếu là Admin thì hiển thị các tab quản lý nhân viên & thực đơn
        if (account.getType() == 1) {
            tabbedPane.addTab("Quản lý nhân viên", new StaffManagementPanel());
            tabbedPane.addTab("Quản lý thực đơn", new MenuManagementPanel());
        }
        tabbedPane.addTab("Bán hàng", new SalesPanel());
        tabbedPane.addTab("Quản lý bàn", new TableManagementPanel());
        tabbedPane.addTab("Hóa đơn", new BillPanel());

        add(tabbedPane);
    }
}
