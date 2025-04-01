package yathh.me.quanlycafe;

import javax.swing.SwingUtilities;
import view.LoginView;

public class QuanLyCafe {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}
