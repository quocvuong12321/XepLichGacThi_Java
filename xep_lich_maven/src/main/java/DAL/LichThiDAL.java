package DAL;

import DTO.LichThiDTO;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS
 */
public class LichThiDAL {
    
    public List<LichThiDTO> docFileLichThi(String path) {
        List<LichThiDTO> lstLichThi = new ArrayList<>();

        if (path == null || path.isEmpty()) {
            System.out.println("Đường dẫn file không hợp lệ.");
            return lstLichThi;
        }

        try (FileInputStream fis = new FileInputStream(path); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

            if (sheet == null) {
                System.out.println("Sheet đầu tiên không tồn tại.");
                return lstLichThi;
            }

            int lastColumn = sheet.getRow(5).getLastCellNum(); // Tìm số cột cuối cùng

            for (int colIndex = 4; colIndex < lastColumn; colIndex++) {
                Date lastDate = getMergedCellValueAsDate(sheet, 5, colIndex);
                if (lastDate == null) {
                    System.out.println("Ô ngày không hợp lệ tại cột " + colIndex);
                    continue;
                }

                for (int rowIndex = 7; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    Row rowTiet = sheet.getRow(rowIndex);
                    if (rowTiet == null) {
                        continue;
                    }

                    Cell cellTiet = rowTiet.getCell(colIndex);
                    if (cellTiet == null || cellTiet.getCellType() != CellType.STRING || cellTiet.getStringCellValue().isEmpty()) {
                        continue;
                    }

                    String tiet = cellTiet.getStringCellValue();
                    if (!tiet.contains(" → ")) {
                        System.out.println("Dữ liệu tiết không đúng định dạng tại cột " + colIndex + " hàng " + rowIndex);
                        continue;
                    }

                    String[] tietRange = tiet.split(" → ");
                    if (tietRange.length != 2) {
                        System.out.println("Dữ liệu tiết không hợp lệ tại cột " + colIndex + " hàng " + rowIndex);
                        continue;
                    }

                    int tietBatDau = Integer.parseInt(tietRange[0]);
                    int tietKetThuc = Integer.parseInt(tietRange[1]);

                    Cell cellSoGV = sheet.getRow(rowIndex + 1).getCell(colIndex);
                    int soGVCanCap = (cellSoGV != null && cellSoGV.getCellType() == CellType.NUMERIC) ? (int) cellSoGV.getNumericCellValue() : 0;

                    LichThiDTO lt = new LichThiDTO(lastDate, tietBatDau, tietKetThuc, soGVCanCap);
                    lstLichThi.add(lt);
                    System.out.println("Đã thêm lịch thi: " + lt);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
            e.printStackTrace();
        }

        return lstLichThi;
    }

    private Date getMergedCellValueAsDate(Sheet sheet, int rowIndex, int colIndex) {
        Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
        for (CellRangeAddress mergedRegion : sheet.getMergedRegions()) {
            if (mergedRegion.isInRange(rowIndex, colIndex)) {
                cell = sheet.getRow(mergedRegion.getFirstRow()).getCell(mergedRegion.getFirstColumn());
                break;
            }
        }

        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            } else if (cell.getCellType() == CellType.STRING) {
                try {
                    return new SimpleDateFormat("dd/MM/yyyy").parse(cell.getStringCellValue());
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

}
