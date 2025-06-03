package GUI;

import BLL.GiangVienBLL;
import BLL.LichThiBLL;
import BLL.XepLichBLL;
import BLL.XuatFileExcelBLL;
import DTO.GiangVienDTO;
import DTO.LichThiDTO;
import DTO.LichThiXepResult;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Xep_lich_frm extends javax.swing.JFrame {

    private XepLichBLL xlBLL = new XepLichBLL();
    private LichThiBLL ltBLL = new LichThiBLL();
    private GiangVienBLL gvBLL = new GiangVienBLL();
    private List<LichThiDTO> lstLichThi = new ArrayList<>();
    private List<GiangVienDTO> lstGiangVien = new ArrayList<>();
    private List<LichThiXepResult> lstKqXep = new ArrayList<>();

    private List<String> uniqueColumns;
    private int[][] ketquaxep;

    public Xep_lich_frm() {
        initComponents();
        setTitle("Xếp Lịch Gác Thi");
        setSize(1300, 800);
        this.setLocationRelativeTo(null);
        txtDiemXepLich.setEditable(false);
        txtDiemSauToiUu.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableXepLich = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnLoadGiangVien = new javax.swing.JButton();
        btnLoadLichThi = new javax.swing.JButton();
        btnXepLich = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnToiUuHoa = new javax.swing.JButton();
        btnXepLai = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtDiemXepLich = new javax.swing.JTextField();
        txtDiemSauToiUu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TRƯỜNG ĐẠI HỌC CÔNG THƯƠNG TP. HỒ CHÍ MINH                    ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("XẾP LỊCH GÁC THI");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tableXepLich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableXepLich);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("KHOA CÔNG NGHỆ THÔNG TIN");

        jPanel2.setLayout(new java.awt.GridLayout(4, 1, 0, 30));

        btnLoadGiangVien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLoadGiangVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teacher.png"))); // NOI18N
        btnLoadGiangVien.setText("Load giảng viên");
        btnLoadGiangVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLoadGiangVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadGiangVienActionPerformed(evt);
            }
        });
        jPanel2.add(btnLoadGiangVien);

        btnLoadLichThi.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLoadLichThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/time-management.png"))); // NOI18N
        btnLoadLichThi.setText("Load lịch thi");
        btnLoadLichThi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLoadLichThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadLichThiActionPerformed(evt);
            }
        });
        jPanel2.add(btnLoadLichThi);

        btnXepLich.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXepLich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/timetable.png"))); // NOI18N
        btnXepLich.setText("Xếp lịch");
        btnXepLich.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXepLich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXepLichActionPerformed(evt);
            }
        });
        jPanel2.add(btnXepLich);

        btnXuatFile.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXuatFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logoEx.png"))); // NOI18N
        btnXuatFile.setText("Xuất file");
        btnXuatFile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });
        jPanel2.add(btnXuatFile);

        jPanel3.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        btnToiUuHoa.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnToiUuHoa.setText("Tối ưu hóa");
        btnToiUuHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToiUuHoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnToiUuHoa);

        btnXepLai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnXepLai.setText("Xếp lại");
        btnXepLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXepLaiActionPerformed(evt);
            }
        });
        jPanel3.add(btnXepLai);

        jPanel4.setLayout(new java.awt.GridLayout(1, 2, 40, 0));

        txtDiemXepLich.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel4.add(txtDiemXepLich);

        txtDiemSauToiUu.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel4.add(txtDiemSauToiUu);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Điểm xếp lịch");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Điểm sau tối ưu");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/huit.png"))); // NOI18N
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(397, 397, 397))))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadLichThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadLichThiActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            lstLichThi = ltBLL.docFileLichThi(path);

            // Kiểm tra dữ liệu sau khi tải
            if (lstLichThi != null && !lstLichThi.isEmpty()) {
                updateTable(lstLichThi);
                JOptionPane.showMessageDialog(this, "Tải Lịch Thi Thành Công");
            } else {
                JOptionPane.showMessageDialog(this, "Không có dữ liệu trong file.");
            }
        }
    }//GEN-LAST:event_btnLoadLichThiActionPerformed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        if (lstGiangVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Danh sách giảng viên trống. Không thể xuất file.");
            return;
        }
        if (lstLichThi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Danh sách lịch thi trống. Không thể xuất file.");
            return;
        }
        if (uniqueColumns == null || uniqueColumns.isEmpty() || ketquaxep == null) {
            JOptionPane.showMessageDialog(this, "Dữ liệu chưa được xếp lịch. Vui lòng xếp lịch trước khi xuất file.");
            return;
        }

        // Chọn nơi lưu file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        
        
        // Đặt tên cố định cho file
        fileChooser.setSelectedFile(new java.io.File("LichGacThi.xlsx"));
        
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();

            // Đảm bảo đuôi file là .xlsx
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            // Create an instance of the DAL and export the file
            XuatFileExcelBLL exportBll = new XuatFileExcelBLL();
            String result = exportBll.xuatFileExcel(lstGiangVien, uniqueColumns, ketquaxep, lstLichThi, filePath);

            // Notify the user of the result
            if (result != null) {
                JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!\nĐường dẫn: " + result);
            } else {
                JOptionPane.showMessageDialog(this, "Xuất file Excel thất bại. Vui lòng thử lại.");
            }
        }
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void btnXepLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXepLaiActionPerformed
        lstGiangVien.clear();
        lstLichThi.clear();
        lstKqXep.clear();
        ((DefaultTableModel) tableXepLich.getModel()).setRowCount(0);
        JOptionPane.showMessageDialog(this, "Xác nhận xếp lại");
    }//GEN-LAST:event_btnXepLaiActionPerformed

    private void btnLoadGiangVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadGiangVienActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getPath();
                gvBLL.loadFileGiangVien(path, lstGiangVien);
                updateTableGiangVien(lstGiangVien);
                JOptionPane.showMessageDialog(this, "Tải Giảng Viên Thành Công");
            } catch (Exception ex) {
                Logger.getLogger(Xep_lich_frm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnLoadGiangVienActionPerformed

    private void btnXepLichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXepLichActionPerformed
        if (lstGiangVien.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có giảng viên để xếp lịch");
            return;
        }
        if (lstLichThi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa có lịch thi để xếp");
            return;
        }
        
        List<LichThiDTO> dsLichThi = new ArrayList<>();
    for (LichThiDTO item : lstLichThi) {
    dsLichThi.add(new LichThiDTO(item));
}   
        
        lstKqXep = xlBLL.xepLichGacThi(dsLichThi, lstGiangVien);
        txtDiemXepLich.setText(xlBLL.danhGiaLichThi(lstGiangVien) + " Điểm");
        
        
        
        ketquaxep = xlBLL.chuyenDoiXepLichSangMang(lstKqXep, lstGiangVien);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày ngắn gọn
        uniqueColumns = lstKqXep.stream()
                .map(kq -> {
                    String ngay = dateFormat.format(kq.getLichThi().getNgay());
                    return ngay + " - Tiết " + kq.getLichThi().getTietBatDau() + "-" + kq.getLichThi().getTietKetThuc();
                })
                .distinct()
                .collect(Collectors.toList());

        updateDataGridView(tableXepLich, ketquaxep, lstGiangVien, uniqueColumns);

        int unassignedCount = xlBLL.layDanhSachLichChuaXep().size();
        JOptionPane.showMessageDialog(this, unassignedCount > 0
                ? "Xếp lịch thành công, còn " + unassignedCount + " lịch chưa xếp được"
                : "Xếp lịch thành công");
    }//GEN-LAST:event_btnXepLichActionPerformed

    private void btnToiUuHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToiUuHoaActionPerformed
        lstKqXep = xlBLL.caiTienKetQua(lstKqXep, lstGiangVien);
//        xlBLL.caiTienKetQua(lstKqXep, lstGiangVien);
            
        // Hiển thị điểm sau khi tối ưu
//        txtDiemSauToiUu.setText(xlBLL.danhGiaLichThi(lstKqXep) + " Điểm");
        txtDiemSauToiUu.setText(xlBLL.danhGiaLichThi(lstGiangVien) + " Điểm");
        // Cập nhật lại bảng sau khi tối ưu
//        ketquaxep = xlBLL.chuyenDoiXepLichSangMang(lstKqXep, lstGiangVien);
        ketquaxep = xlBLL.chuyenDoiXepLichSangMang(lstKqXep, lstGiangVien);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Định dạng ngày ngắn gọn
        uniqueColumns = lstKqXep.stream()
                .map(kq -> {
                    String ngay = dateFormat.format(kq.getLichThi().getNgay());
                    return ngay + " - Tiết " + kq.getLichThi().getTietBatDau() + "-" + kq.getLichThi().getTietKetThuc();
                })
                .distinct()
                .collect(Collectors.toList());

//        updateDataGridView(tableXepLich, ketquaxep, lstGiangVien, uniqueColumns);
        updateDataGridView(tableXepLich, ketquaxep, lstGiangVien, uniqueColumns);
    }//GEN-LAST:event_btnToiUuHoaActionPerformed

    private void updateTable(List<LichThiDTO> dataList) {
        DefaultTableModel model = new DefaultTableModel();

        // Định nghĩa các cột
        model.addColumn("Ngày");
        model.addColumn("Tiết Bắt Đầu");
        model.addColumn("Tiết Kết Thúc");
        model.addColumn("Số GV Cần Cặp");

        // Định dạng ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Thêm dữ liệu vào bảng
        for (LichThiDTO lichThi : dataList) {
            model.addRow(new Object[]{
                dateFormat.format(lichThi.getNgay()),
                lichThi.getTietBatDau(),
                lichThi.getTietKetThuc(),
                lichThi.getSoGVCanCap()
            });
        }

        tableXepLich.setModel(model);
    }

    private void updateTableGiangVien(List<GiangVienDTO> dataList) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tên Giảng Viên");
        model.addColumn("Số Lịch Dạy");
        model.addColumn("Số Lịch Gác Thi");

        for (GiangVienDTO gv : dataList) {
            model.addRow(new Object[]{
                gv.getTenGiangVien(),
                gv.getLichDay().size(),
                gv.getLichGacThi().size()
            });
        }

        tableXepLich.setModel(model);
    }

    private void updateDataGridView(JTable dgv, int[][] resultMatrix, List<GiangVienDTO> lstGiangVien, List<String> uniqueColumns) {
        DefaultTableModel model = new DefaultTableModel();

        // Thêm cột đầu tiên là tên giảng viên
        model.addColumn("Giảng Viên");
        for (String col : uniqueColumns) {
            model.addColumn(col);
        }

        // Thêm dữ liệu vào các hàng
        for (int i = 0; i < lstGiangVien.size(); i++) {
            Object[] row = new Object[uniqueColumns.size() + 1];
            row[0] = lstGiangVien.get(i).getTenGiangVien(); // Ghi tên giảng viên vào cột đầu tiên
            for (int j = 0; j < uniqueColumns.size(); j++) {
                row[j + 1] = resultMatrix[i][j] == 1 ? "X" : ""; // Đánh "X" nếu có lịch, ngược lại để trống
            }
            model.addRow(row);
        }

        dgv.setModel(model); // Gán dữ liệu vào JTable

        dgv.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Tắt auto resize mặc định

        for (int column = 0; column < dgv.getColumnCount(); column++) {
            int width = 75; // Độ rộng tối thiểu
            for (int row = 0; row < dgv.getRowCount(); row++) {
                TableCellRenderer renderer = dgv.getCellRenderer(row, column);
                Component comp = dgv.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 10, width);
            }
            dgv.getColumnModel().getColumn(column).setPreferredWidth(width);
        }

        // Đặt lại chiều rộng
        tableXepLich.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = tableXepLich.getColumnModel();
        columnModel.getColumn(0).setMinWidth(150);
        columnModel.getColumn(0).setMaxWidth(150);
        columnModel.getColumn(0).setPreferredWidth(150);
        for (int i = 1; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setMinWidth(150);
            columnModel.getColumn(i).setMaxWidth(150);
            columnModel.getColumn(i).setPreferredWidth(150);
        }

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Xep_lich_frm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoadGiangVien;
    private javax.swing.JButton btnLoadLichThi;
    private javax.swing.JButton btnToiUuHoa;
    private javax.swing.JButton btnXepLai;
    private javax.swing.JButton btnXepLich;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableXepLich;
    private javax.swing.JTextField txtDiemSauToiUu;
    private javax.swing.JTextField txtDiemXepLich;
    // End of variables declaration//GEN-END:variables
}
