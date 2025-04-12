package DAL;

import DTO.GiangVienDTO;
import DTO.LichThiDTO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ASUS_TKD
 */
public class XuatFileExcelDAL {

    public String xuatFileExcel(List<GiangVienDTO> lstGiangVien, List<String> uniqueColumns, int[][] resultMatrix, List<LichThiDTO> lstLichThi, String filePath) throws FileNotFoundException, IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Lịch Gác Thi");

        // Thêm logo
        InputStream inputStream = XuatFileExcelDAL.class.getResourceAsStream("/huit.png");
        if (inputStream != null) {
            byte[] imageBytes = IOUtils.toByteArray(inputStream);
            int pictureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
            inputStream.close();

            CreationHelper helper = workbook.getCreationHelper();
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            anchor.setCol2(2);
            anchor.setRow2(11);
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            pict.resize(0.8);
        }

        // Tạo style viền cho ô
        CellStyle borderedStyle = workbook.createCellStyle();
        borderedStyle.setBorderTop(BorderStyle.THIN);
        borderedStyle.setBorderBottom(BorderStyle.THIN);
        borderedStyle.setBorderLeft(BorderStyle.THIN);
        borderedStyle.setBorderRight(BorderStyle.THIN);
        borderedStyle.setAlignment(HorizontalAlignment.CENTER);
        borderedStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // Tiêu đề
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(2); // Cột C
        titleCell.setCellValue(" BỘ CÔNG THƯƠNG \n TRƯỜNG ĐẠI HỌC CÔNG THƯƠNG TP. HCM ");

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setWrapText(true);
        titleStyle.setIndention((short) 3);

        XSSFFont titleFont = (XSSFFont) workbook.createFont();
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 18);
        titleStyle.setFont(titleFont);

        titleCell.setCellStyle(titleStyle);
        // Merge từ Cột C (index 2) đến Cột J (index 9), hàng 0 đến 2
        sheet.addMergedRegion(new CellRangeAddress(0, 2, 2, 9));

        XSSFFont boldFont = (XSSFFont) workbook.createFont();
        boldFont.setBold(true);

        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setFont(boldFont);
        boldStyle.setBorderTop(BorderStyle.THIN);
        boldStyle.setBorderBottom(BorderStyle.THIN);
        boldStyle.setBorderLeft(BorderStyle.THIN);
        boldStyle.setBorderRight(BorderStyle.THIN);

        // Tiêu đề ngày/tiết/thứ
        Row dateRow = sheet.createRow(9);
        Row dayRow = sheet.createRow(10);
        Row periodRow = sheet.createRow(11);

        dateRow.createCell(0).setCellValue("Ngày");
        dayRow.createCell(0).setCellValue("Thứ");
        periodRow.createCell(0).setCellValue("Tiết:");

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", new Locale("vi", "VN"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Map<String, Integer[]> mergedRegions = new HashMap<>();

        for (int i = 0; i < uniqueColumns.size(); i++) {
            String[] parts = uniqueColumns.get(i).split(" ", 2);
            String date = parts[0];
            String period = (parts.length > 1) ? parts[1].replace("- Tiết ", "") : ""; // Remove "- Tiết "

            Date parsedDate;
            try {
                parsedDate = dateFormat.parse(date);
            } catch (Exception e) {
                parsedDate = null;
            }

            Cell dateCell = dateRow.createCell(i + 1);
            if (mergedRegions.containsKey(date)) {
                Integer[] range = mergedRegions.get(date);
                range[1] = i + 1;
                mergedRegions.put(date, range);
            } else {
                mergedRegions.put(date, new Integer[]{i + 1, i + 1});
            }

            Cell dayCell = dayRow.createCell(i + 1);
            Cell periodCell = periodRow.createCell(i + 1);

            dateCell.setCellValue(date);
            dayCell.setCellValue(parsedDate != null ? dayFormat.format(parsedDate) : "");
            periodCell.setCellValue(period);

            dateCell.setCellStyle(borderedStyle);
            dayCell.setCellStyle(borderedStyle);
            periodCell.setCellStyle(borderedStyle);
        }

        for (Map.Entry<String, Integer[]> entry : mergedRegions.entrySet()) {
            Integer[] range = entry.getValue();
            if (range[0] != range[1]) {
                sheet.addMergedRegion(new CellRangeAddress(dateRow.getRowNum(), dateRow.getRowNum(), range[0], range[1]));
            }
        }

        // Hàng tổng số ca
        Row requiredRow = sheet.createRow(12);
        Row assignedRow = sheet.createRow(13);
        Row unassignedRow = sheet.createRow(14);

        requiredRow.createCell(0).setCellValue("Số ca gác thi cần cấp:");
        assignedRow.createCell(0).setCellValue("Số ca gác thi đã cấp:");
        unassignedRow.createCell(0).setCellValue("Số ca gác thi chưa cấp:");

        int totalAssigned = 0, totalUnassigned = 0;
        SimpleDateFormat dateFormatDTO = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < uniqueColumns.size(); i++) {
            // Lấy thông tin ngày và tiết học
            String[] parts = uniqueColumns.get(i).split(" ", 2);
            String date = parts[0];
            String period = parts.length > 1 ? parts[1].replace("- Tiết ", "") : "";

            // Tìm đối tượng LichThiDTO phù hợp
            LichThiDTO lichThi = lstLichThi.stream()
                    .filter(lt -> dateFormatDTO.format(lt.getNgay()).equals(date)
                    && String.format("%d-%d", lt.getTietBatDau(), lt.getTietKetThuc()).equals(period))
                    .findFirst()
                    .orElse(null);

            // Nếu không tìm thấy, đặt giá trị mặc định
            int requiredCount = lichThi != null ? lichThi.getSoGVCanCap() : 0;
            // int requiredCount = lstLichThi.get(i).getSoGVCanCap();

            // Tính số ca đã cấp
            int assignedCount = 0;
            for (int j = 0; j < lstGiangVien.size(); j++) {
                if (resultMatrix[j][i] == 1) {
                    assignedCount++;
                }
            }

            int unassignedCount = requiredCount - assignedCount;

            Cell requiredCell = requiredRow.createCell(i + 1);
            Cell assignedCell = assignedRow.createCell(i + 1);
            Cell unassignedCell = unassignedRow.createCell(i + 1);

            requiredCell.setCellValue(requiredCount);
            assignedCell.setCellValue(assignedCount);
            unassignedCell.setCellValue(Math.max(0, unassignedCount));

            requiredCell.setCellStyle(borderedStyle);
            assignedCell.setCellStyle(borderedStyle);
            unassignedCell.setCellStyle(borderedStyle);

            totalAssigned += assignedCount;
            totalUnassigned += unassignedCount;
        }

        // Border viền cho cột
        requiredRow.getCell(0).setCellStyle(borderedStyle);
        assignedRow.getCell(0).setCellStyle(borderedStyle);
        unassignedRow.getCell(0).setCellStyle(borderedStyle);
        dateRow.getCell(0).setCellStyle(borderedStyle);
        dayRow.getCell(0).setCellStyle(borderedStyle);
        periodRow.getCell(0).setCellStyle(borderedStyle);

        //In đậm cho cột
        requiredRow.getCell(0).setCellStyle(boldStyle);
        assignedRow.getCell(0).setCellStyle(boldStyle);
        unassignedRow.getCell(0).setCellStyle(boldStyle);
        dateRow.getCell(0).setCellStyle(boldStyle);
        dayRow.getCell(0).setCellStyle(boldStyle);
        periodRow.getCell(0).setCellStyle(boldStyle);

        // Danh sách giảng viên
        int startRow = 17;
        for (int i = 0; i < lstGiangVien.size(); i++) {
            Row row = sheet.createRow(startRow + i);

            Cell nameCell = row.createCell(0);
            nameCell.setCellValue(lstGiangVien.get(i).getTenGiangVien());
            nameCell.setCellStyle(borderedStyle);
            nameCell.setCellStyle(boldStyle);

            for (int j = 0; j < uniqueColumns.size(); j++) {
                Cell cell = row.createCell(j + 1);
                cell.setCellValue(resultMatrix[i][j] == 1 ? "X" : "");
                cell.setCellStyle(borderedStyle);
            }
        }

        // Đặt độ rộng cột
        sheet.setColumnWidth(0, 30 * 256); // Cột A
        for (int i = 1; i < uniqueColumns.size() + 1; i++) {
            sheet.setColumnWidth(i, 15 * 256);
        }

        dateRow.setHeightInPoints(50); // Đặt chiều cao cho dòng "Ngày"

        // Ghi file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            workbook.close();
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
