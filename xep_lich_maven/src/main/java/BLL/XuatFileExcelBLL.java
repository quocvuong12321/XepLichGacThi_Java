/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.XuatFileExcelDAL;
import DTO.GiangVienDTO;
import DTO.LichThiDTO;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ASUS_TKD
 */
public class XuatFileExcelBLL {

    private XuatFileExcelDAL xuatFileDAL = new XuatFileExcelDAL();

//    public String xuatFileExcel(
//            List<GiangVienDTO> lstGiangVien,
//            List<String> uniqueColumns,
//            int[][] resultMatrix,
//            List<LichThiDTO> lstLichThi,
//            String filePath
//    ) {
//        return xuatFileDAL.xuatFileExcel(lstGiangVien, uniqueColumns, resultMatrix, lstLichThi, filePath);
//    }
    
    public String xuatFileExcel(
            List<GiangVienDTO> lstGiangVien,
            List<String> uniqueColumns,
            int[][] resultMatrix,
            List<LichThiDTO> lstLichThi,
            String filePath
    ) {
        try {
            return xuatFileDAL.xuatFileExcel(lstGiangVien, uniqueColumns, resultMatrix, lstLichThi, filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // hoặc trả về chuỗi thông báo lỗi
        }
    }
    
    
    
}
