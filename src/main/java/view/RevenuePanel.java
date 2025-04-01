package view;

import javax.swing.*;
import java.awt.*;

public class RevenuePanel extends JPanel {
    public RevenuePanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Revenue Statistics", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);
    }
}
