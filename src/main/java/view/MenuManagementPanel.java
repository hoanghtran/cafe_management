package view;

import javax.swing.*;
import java.awt.*;

public class MenuManagementPanel extends JPanel {
    public MenuManagementPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Menu Management Panel", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
