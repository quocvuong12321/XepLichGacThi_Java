/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.LichThiDAL;
import DTO.LichThiDTO;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class LichThiBLL {
    private LichThiDAL ltDAL = new LichThiDAL();

    public List<LichThiDTO> docFileLichThi(String path) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Đường dẫn không được để null hoặc trống.");
        }
        return ltDAL.docFileLichThi(path);
    }
}
