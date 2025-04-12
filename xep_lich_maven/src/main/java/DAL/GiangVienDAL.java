/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.GiangVienDTO;
import DTO.LichDayDTO;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class GiangVienDAL {

    public void loadFileGiangVien(String path, List<GiangVienDTO> lstGiangVien) throws Exception {
        if (lstGiangVien == null) {
            throw new IllegalArgumentException("Danh sách giảng viên không được để null.");
        }
        if (path == null || path.isEmpty()) {
            return; // Trả về nếu đường dẫn không hợp lệ
        }

        FileInputStream fis = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        Date ngay = null; // Biến lưu ngày hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Row row : sheet) {
            Cell firstCell = row.getCell(0); // Ô đầu tiên của hàng
            if (firstCell != null && isDateCell(firstCell.toString())) {
                ngay = sdf.parse(firstCell.toString());
                continue;
            }

            if (firstCell != null && firstCell.getCellType() == CellType.NUMERIC) {
                String tenGiangVien = row.getCell(9).getStringCellValue().trim();
                String valueTiet = row.getCell(8).getStringCellValue().trim();

                if (tenGiangVien.isEmpty() || valueTiet.isEmpty()) {
                    continue;
                }

                String[] arrTiet = valueTiet.split(" → ");
                int tietBatDau = Integer.parseInt(arrTiet[0]);
                int tietKetThuc = Integer.parseInt(arrTiet[1]);

                GiangVienDTO gv = lstGiangVien.stream()
                        .filter(t -> t.getTenGiangVien().equals(tenGiangVien))
                        .findFirst()
                        .orElse(null);

                if (gv != null) {
                    gv.getLichDay().add(new LichDayDTO(ngay, tietBatDau, tietKetThuc));
                } else {
                    LichDayDTO newLichDay = new LichDayDTO(ngay, tietBatDau, tietKetThuc);
                    GiangVienDTO newGiangVien = new GiangVienDTO(tenGiangVien);
                    newGiangVien.getLichDay().add(newLichDay);
                    lstGiangVien.add(newGiangVien);
                }
            }
        }
        fis.close();
    }

    private boolean isDateCell(String cellValue) {
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(cellValue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
