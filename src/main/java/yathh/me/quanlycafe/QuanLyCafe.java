package yathh.me.quanlycafe;

import javax.swing.SwingUtilities;
import view.LoginForm;

public class QuanLyCafe {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}
