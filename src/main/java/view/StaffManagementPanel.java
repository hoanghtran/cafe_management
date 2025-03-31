package view;

import javax.swing.*;
import java.awt.*;

public class StaffManagementPanel extends JPanel {
    public StaffManagementPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Staff Management Panel", SwingConstants.CENTER), BorderLayout.CENTER);
    }
}
