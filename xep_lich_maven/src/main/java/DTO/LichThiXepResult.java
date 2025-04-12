/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class LichThiXepResult {

    private LichThiDTO lichThi;
    private List<GiangVienDTO> giangViens;
    
    
    // Constructor không tham số
    public LichThiXepResult() {
        this.giangViens = new ArrayList<>();
    }

    // Constructor có tham số (tương tự như C#)
    public LichThiXepResult(LichThiDTO lichThi) {
        this.lichThi = lichThi;
        this.giangViens = new ArrayList<>();
    }

    public LichThiXepResult(LichThiDTO lichThi, List<GiangVienDTO> giangViens) {
        this.lichThi = lichThi;
        this.giangViens = giangViens;
    }

    // Getter và Setter
    public LichThiDTO getLichThi() {
        return lichThi;
    }

    public void setLichThi(LichThiDTO lichThi) {
        this.lichThi = lichThi;
    }

    public List<GiangVienDTO> getGiangViens() {
        return giangViens;
    }

    public void setGiangViens(List<GiangVienDTO> giangViens) {
        this.giangViens = giangViens;
    }
}
