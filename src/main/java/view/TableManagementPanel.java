package view;

import dao.TableDAO;
import model.TableDrink;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TableManagementPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private TableDAO tableDAO = new TableDAO();

    public TableManagementPanel() {
        setLayout(new BorderLayout());
        
        model = new DefaultTableModel(new Object[]{"ID", "Tên bàn", "Trạng thái"}, 0);
        table = new JTable(model);
        loadTableData();
        
        JButton btnAdd = new JButton("Thêm bàn");
        btnAdd.addActionListener(e -> addTable());
        
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(btnAdd, BorderLayout.SOUTH);
    }

    private void loadTableData() {
        model.setRowCount(0);
        for (TableDrink t : tableDAO.getAllTables()) {
            model.addRow(new Object[]{t.getId(), t.getName(), t.getStatus()});
        }
    }

    private void addTable() {
        String tableName = JOptionPane.showInputDialog("Nhập tên bàn:");
        if (tableName != null && !tableName.isEmpty()) {
            tableDAO.addTable(tableName);
            loadTableData();
        }
    }
}
