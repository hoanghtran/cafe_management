package view;

import dao.DrinkDAO;
import dao.CategoryDAO;
import model.Drink;
import model.Category;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ThucDonPanel extends JPanel {
    private JTable tblDrinks;
    private DefaultTableModel drinkTableModel;
    private JTextField txtDrinkId, txtDrinkName, txtPrice;
    private JComboBox<Category> cbCategory;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    
    public ThucDonPanel() {
        setLayout(new BorderLayout());
        JLabel lblTitle = new JLabel("Quản lý thực đơn", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitle, BorderLayout.NORTH);
        
        drinkTableModel = new DefaultTableModel(new Object[]{"ID", "Tên món", "Danh mục", "Giá"}, 0);
        tblDrinks = new JTable(drinkTableModel);
        JScrollPane scrollPane = new JScrollPane(tblDrinks);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel inputPanel = new JPanel(new GridLayout(4,2,5,5));
        inputPanel.add(new JLabel("ID:"));
        txtDrinkId = new JTextField();
        txtDrinkId.setEditable(false);
        inputPanel.add(txtDrinkId);
        inputPanel.add(new JLabel("Tên món:"));
        txtDrinkName = new JTextField();
        inputPanel.add(txtDrinkName);
        inputPanel.add(new JLabel("Danh mục:"));
        cbCategory = new JComboBox<>();
        loadCategories();
        inputPanel.add(cbCategory);
        inputPanel.add(new JLabel("Giá:"));
        txtPrice = new JTextField();
        inputPanel.add(txtPrice);
        add(inputPanel, BorderLayout.SOUTH);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm món");
        btnSua = new JButton("Sửa món");
        btnXoa = new JButton("Xóa món");
        btnLamMoi = new JButton("Làm mới");
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);
        add(buttonPanel, BorderLayout.NORTH);
        
        loadDrinkData();
        
        tblDrinks.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                int row = tblDrinks.getSelectedRow();
                if(row >= 0){
                    txtDrinkId.setText(drinkTableModel.getValueAt(row, 0).toString());
                    txtDrinkName.setText(drinkTableModel.getValueAt(row, 1).toString());
                    int catId = (int) drinkTableModel.getValueAt(row, 2);
                    for(int i = 0; i < cbCategory.getItemCount(); i++){
                        Category cat = cbCategory.getItemAt(i);
                        if(cat.getId() == catId){
                            cbCategory.setSelectedIndex(i);
                            break;
                        }
                    }
                    txtPrice.setText(drinkTableModel.getValueAt(row, 3).toString());
                }
            }
        });
        
        btnThem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String name = txtDrinkName.getText().trim();
                Category cat = (Category) cbCategory.getSelectedItem();
                String priceStr = txtPrice.getText().trim();
                if(name.isEmpty() || priceStr.isEmpty()){
                    JOptionPane.showMessageDialog(ThucDonPanel.this, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }
                try {
                    float price = Float.parseFloat(priceStr);
                    Drink drink = new Drink(0, name, cat.getId(), price);
                    if(DrinkDAO.insertFood(drink)){
                        JOptionPane.showMessageDialog(ThucDonPanel.this, "Thêm món thành công!");
                        loadDrinkData();
                    } else {
                        JOptionPane.showMessageDialog(ThucDonPanel.this, "Thêm món thất bại!");
                    }
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ThucDonPanel.this, "Giá không hợp lệ!");
                }
            }
        });
        
        btnSua.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(txtDrinkId.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(ThucDonPanel.this, "Vui lòng chọn món cần sửa!");
                    return;
                }
                int id = Integer.parseInt(txtDrinkId.getText().trim());
                String name = txtDrinkName.getText().trim();
                Category cat = (Category) cbCategory.getSelectedItem();
                String priceStr = txtPrice.getText().trim();
                if(name.isEmpty() || priceStr.isEmpty()){
                    JOptionPane.showMessageDialog(ThucDonPanel.this, "Vui lòng nhập đầy đủ thông tin!");
                    return;
                }
                try {
                    float price = Float.parseFloat(priceStr);
                    Drink drink = new Drink(id, name, cat.getId(), price);
                    if(DrinkDAO.updateFood(drink)){
                        JOptionPane.showMessageDialog(ThucDonPanel.this, "Sửa món thành công!");
                        loadDrinkData();
                    } else {
                        JOptionPane.showMessageDialog(ThucDonPanel.this, "Sửa món thất bại!");
                    }
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ThucDonPanel.this, "Giá không hợp lệ!");
                }
            }
        });
        
        btnXoa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(txtDrinkId.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(ThucDonPanel.this, "Vui lòng chọn món cần xóa!");
                    return;
                }
                int id = Integer.parseInt(txtDrinkId.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(ThucDonPanel.this, "Bạn có chắc chắn xóa món này?");
                if(confirm == JOptionPane.YES_OPTION){
                    if(DrinkDAO.deleteFood(id)){
                        JOptionPane.showMessageDialog(ThucDonPanel.this, "Xóa món thành công!");
                        loadDrinkData();
                    } else {
                        JOptionPane.showMessageDialog(ThucDonPanel.this, "Xóa món thất bại!");
                    }
                }
            }
        });
        
        btnLamMoi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                txtDrinkId.setText("");
                txtDrinkName.setText("");
                txtPrice.setText("");
                cbCategory.setSelectedIndex(0);
            }
        });
    }
    
    private void loadCategories() {
        cbCategory.removeAllItems();
        List<Category> list = CategoryDAO.getListCategory();
        for(Category cat : list) {
            cbCategory.addItem(cat);
        }
        cbCategory.setRenderer(new DefaultListCellRenderer(){
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Category){
                    Category cat = (Category) value;
                    setText(cat.getName() + " (ID: " + cat.getId() + ")");
                }
                return this;
            }
        });
    }
    
    private void loadDrinkData() {
        drinkTableModel.setRowCount(0);
        List<Drink> list = DrinkDAO.getListFood();
        for(Drink d : list) {
            drinkTableModel.addRow(new Object[]{d.getId(), d.getName(), d.getIdCategory(), d.getPrice()});
        }
    }
}
