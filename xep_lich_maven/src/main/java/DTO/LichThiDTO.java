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
public class LichThiDTO {

    private Date ngay;
    private int tietBatDau;
    private int tietKetThuc;
    private int soGVCanCap;

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
    
    public int getSoGVCanCap() {
        return soGVCanCap;
    }

    public void setSoGVCanCap(int soGVCanCap) {
        this.soGVCanCap = soGVCanCap;
    }

    // Constructor không tham số
    public LichThiDTO() {
    }

    // Constructor có tham số
    public LichThiDTO(Date ngay, int tietBatDau, int tietKetThuc, int soGVCanCap) {
        this.ngay = ngay;
        this.tietBatDau = tietBatDau;
        this.tietKetThuc = tietKetThuc;
        this.soGVCanCap = soGVCanCap;
    }
}
