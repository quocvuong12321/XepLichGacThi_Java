/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.XepLichDAL;
import DTO.GiangVienDTO;
import DTO.LichThiDTO;
import DTO.LichThiXepResult;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class XepLichBLL {
    private XepLichDAL xlDAL = new XepLichDAL();

    public List<LichThiXepResult> xepLichGacThi(List<LichThiDTO> lstLichThi, List<GiangVienDTO> lstGiangVien) {
        if (lstLichThi == null || lstGiangVien == null) {
            throw new IllegalArgumentException("Danh sách lịch thi hoặc giảng viên không được để null.");
        }
        return xlDAL.xepLichGacThi(lstLichThi, lstGiangVien);
    }

    public int[][] chuyenDoiXepLichSangMang(List<LichThiXepResult> ketQuaXepLich, List<GiangVienDTO> lstGiangVien) {
        if (ketQuaXepLich == null || lstGiangVien == null) {
            throw new IllegalArgumentException("Danh sách kết quả hoặc giảng viên không được để null.");
        }
        return xlDAL.chuyenDoiXepLichSangMang(ketQuaXepLich, lstGiangVien);
    }

    public static int danhGiaLichThi(List<LichThiXepResult> danhSachKetQua) {
        if (danhSachKetQua == null) {
            throw new IllegalArgumentException("Danh sách kết quả không được để null.");
        }
        return XepLichDAL.danhGiaLichThi(danhSachKetQua);
    }

    public List<LichThiDTO> layDanhSachLichChuaXep() {
        return xlDAL.layDanhSachLichChuaXep();
    }

    public void caiTienKetQua(List<LichThiXepResult> ketQuaXepLich, List<GiangVienDTO> lstGiangVien) {
        if (ketQuaXepLich == null || lstGiangVien == null) {
            throw new IllegalArgumentException("Danh sách kết quả hoặc giảng viên không được để null.");
        }
        xlDAL.caiTienKetQua(ketQuaXepLich, lstGiangVien);
    }
}
