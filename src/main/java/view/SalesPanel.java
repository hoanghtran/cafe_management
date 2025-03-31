package view;

import javax.swing.*;
import java.awt.*;

public class SalesPanel extends JPanel {
    public SalesPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Sales Panel", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
