package view;

import dao.CategoryDAO;
import model.Category;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DanhMucThucDonPanel extends JPanel {
    private JTable tblCategories;
    private DefaultTableModel categoryTableModel;
    private JTextField txtCategoryId, txtCategoryName;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi;
    
    public DanhMucThucDonPanel() {
        setLayout(new BorderLayout());
        JLabel lblTitle = new JLabel("Quản lý danh mục thực đơn", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitle, BorderLayout.NORTH);
        
        categoryTableModel = new DefaultTableModel(new Object[]{"ID", "Tên danh mục"}, 0);
        tblCategories = new JTable(categoryTableModel);
        JScrollPane scrollPane = new JScrollPane(tblCategories);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel inputPanel = new JPanel(new GridLayout(2,2,5,5));
        inputPanel.add(new JLabel("ID:"));
        txtCategoryId = new JTextField();
        txtCategoryId.setEditable(false);
        inputPanel.add(txtCategoryId);
        inputPanel.add(new JLabel("Tên danh mục:"));
        txtCategoryName = new JTextField();
        inputPanel.add(txtCategoryName);
        add(inputPanel, BorderLayout.SOUTH);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnThem = new JButton("Thêm danh mục");
        btnSua = new JButton("Sửa danh mục");
        btnXoa = new JButton("Xóa danh mục");
        btnLamMoi = new JButton("Làm mới");
        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnLamMoi);
        add(buttonPanel, BorderLayout.NORTH);
        
        loadCategoryData();
        
        tblCategories.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                int row = tblCategories.getSelectedRow();
                if(row >= 0){
                    txtCategoryId.setText(categoryTableModel.getValueAt(row, 0).toString());
                    txtCategoryName.setText(categoryTableModel.getValueAt(row, 1).toString());
                }
            }
        });
        
        btnThem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String name = txtCategoryName.getText().trim();
                if(name.isEmpty()){
                    JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Tên danh mục không được để trống!");
                    return;
                }
                Category cat = new Category(0, name);
                if(CategoryDAO.addNewCategory(cat)){
                    JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Thêm danh mục thành công!");
                    loadCategoryData();
                } else {
                    JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Thêm danh mục thất bại!");
                }
            }
        });
        
        btnSua.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(txtCategoryId.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Vui lòng chọn danh mục cần sửa!");
                    return;
                }
                int id = Integer.parseInt(txtCategoryId.getText().trim());
                String name = txtCategoryName.getText().trim();
                if(name.isEmpty()){
                    JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Tên danh mục không được để trống!");
                    return;
                }
                Category cat = new Category(id, name);
                if(CategoryDAO.editCategory(cat)){
                    JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Sửa danh mục thành công!");
                    loadCategoryData();
                } else {
                    JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Sửa danh mục thất bại!");
                }
            }
        });
        
        btnXoa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(txtCategoryId.getText().trim().isEmpty()){
                    JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Vui lòng chọn danh mục cần xóa!");
                    return;
                }
                int id = Integer.parseInt(txtCategoryId.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(DanhMucThucDonPanel.this, "Bạn có chắc chắn xóa danh mục này?");
                if(confirm == JOptionPane.YES_OPTION){
                    if(CategoryDAO.deleteCategory(id)){
                        JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Xóa danh mục thành công!");
                        loadCategoryData();
                    } else {
                        JOptionPane.showMessageDialog(DanhMucThucDonPanel.this, "Xóa danh mục thất bại!");
                    }
                }
            }
        });
        
        btnLamMoi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                txtCategoryId.setText("");
                txtCategoryName.setText("");
            }
        });
    }
    
    private void loadCategoryData() {
        categoryTableModel.setRowCount(0);
        List<Category> list = CategoryDAO.getListCategory();
        for(Category cat : list) {
            categoryTableModel.addRow(new Object[]{cat.getId(), cat.getName()});
        }
    }
}
