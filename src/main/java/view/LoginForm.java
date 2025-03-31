package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.AccountDAO;
import model.Account;

public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginForm() {
        setTitle("Đăng nhập hệ thống");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Đăng nhập");

        panel.add(new JLabel("Tên đăng nhập:"));
        panel.add(txtUsername);
        panel.add(new JLabel("Mật khẩu:"));
        panel.add(txtPassword);
        panel.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountDAO dao = new AccountDAO();
                Account acc = dao.login(txtUsername.getText(), new String(txtPassword.getPassword()));
                if (acc != null) {
                    new MainForm(acc).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thất bại!");
                }
            }
        });

        add(panel);
    }
}
