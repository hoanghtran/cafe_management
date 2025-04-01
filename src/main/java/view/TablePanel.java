package view;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    public TablePanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Table Management", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);
    }
}
