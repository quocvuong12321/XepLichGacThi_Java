/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class LichDayDTO {
    private Date ngay;
    private int tietBatDau;
    private int tietKetThuc;

    // Constructor không tham số
    public LichDayDTO() {}

    // Constructor có tham số (giống C#)
    public LichDayDTO(Date ngay, int tietBatDau, int tietKetThuc) {
        this.ngay = ngay;
        this.tietBatDau = tietBatDau;
        this.tietKetThuc = tietKetThuc;
    }

    // Getter và Setter
    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTietBatDau() {
        return tietBatDau;
    }

    public void setTietBatDau(int tietBatDau) {
        this.tietBatDau = tietBatDau;
    }

    public int getTietKetThuc() {
        return tietKetThuc;
    }

    public void setTietKetThuc(int tietKetThuc) {
        this.tietKetThuc = tietKetThuc;
    }
    
    
    
}
