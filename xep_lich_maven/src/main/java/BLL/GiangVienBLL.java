/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.GiangVienDAL;
import DTO.GiangVienDTO;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class GiangVienBLL {

    private GiangVienDAL gvDAL = new GiangVienDAL();

    public void loadFileGiangVien(String path, List<GiangVienDTO> lstGiangVien) throws Exception {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Đường dẫn không được để null hoặc trống.");
        }

        if (lstGiangVien == null) {
            throw new IllegalArgumentException("Danh sách giảng viên không được để null.");
        }

        gvDAL.loadFileGiangVien(path, lstGiangVien);
    }
}
