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
public class GiangVienDTO {

    private String tenGiangVien;
    private List<LichDayDTO> lichDay;
    private List<LichThiDTO> lichGacThi;

    // Constructor không tham số
    public GiangVienDTO() {
        lichDay = new ArrayList<>();
        lichGacThi = new ArrayList<>();
    }

    // Constructor có tham số
    public GiangVienDTO(String tenGiangVien, List<LichDayDTO> lichDay) {
        this.tenGiangVien = tenGiangVien;
        this.lichDay = lichDay;
        this.lichGacThi = new ArrayList<>();
    }

    public GiangVienDTO(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
        this.lichDay = new ArrayList<>();
        this.lichGacThi = new ArrayList<>();
    }

    // Getter và Setter
    public String getTenGiangVien() {
        return tenGiangVien;
    }

    public void setTenGiangVien(String tenGiangVien) {
        this.tenGiangVien = tenGiangVien;
    }

    public List<LichDayDTO> getLichDay() {
        return lichDay;
    }

    public void setLichDay(List<LichDayDTO> lichDay) {
        this.lichDay = lichDay;
    }

    public List<LichThiDTO> getLichGacThi() {
        if (lichGacThi == null) {
            return new ArrayList<>(); // Trả về danh sách rỗng thay vì null
        }
        return lichGacThi;
    }

    public void setLichGacThi(List<LichThiDTO> lichGacThi) {
        this.lichGacThi = lichGacThi;
    }

    // Phương thức kiểm tra lịch dạy trùng
    public boolean kiemTraTrungLichDay(LichThiDTO lt) {
        for (LichDayDTO lichday : lichDay) {
            if (lt.getNgay().equals(lichday.getNgay())) {
                if ((lichday.getTietKetThuc() >= lt.getTietBatDau() && lichday.getTietKetThuc() <= lt.getTietKetThuc())
                        || (lt.getTietKetThuc() >= lichday.getTietBatDau() && lt.getTietKetThuc() <= lichday.getTietKetThuc())
                        || (lichday.getTietBatDau() >= lt.getTietBatDau() && lichday.getTietBatDau() <= lt.getTietKetThuc())
                        || (lt.getTietBatDau() >= lichday.getTietBatDau() && lt.getTietBatDau() <= lichday.getTietKetThuc())) {
                    return true;
                }
            }
        }
        return false;
    }

    // Phương thức kiểm tra lịch thi trùng
    public boolean kiemTraTrungLichThi(LichThiDTO lt2) {
        for (LichThiDTO lt1 : lichGacThi) {
            if (lt1.getNgay().equals(lt2.getNgay())) {
                if ((lt1.getTietKetThuc() >= lt2.getTietBatDau() && lt1.getTietKetThuc() <= lt2.getTietKetThuc())
                        || (lt2.getTietKetThuc() >= lt1.getTietBatDau() && lt2.getTietKetThuc() <= lt1.getTietKetThuc())
                        || (lt1.getTietBatDau() >= lt2.getTietBatDau() && lt1.getTietBatDau() <= lt2.getTietKetThuc())
                        || (lt2.getTietBatDau() >= lt1.getTietBatDau() && lt2.getTietBatDau() <= lt1.getTietKetThuc())) {
                    return true;
                }
            }
        }
        return false;
    }

    // Phương thức thêm lịch gác thi
    public boolean themLichGacThi(LichThiDTO lt, int soLichGac) {
        if (lichGacThi.size() < soLichGac) {
            lichGacThi.add(lt);
            return true;
        }
        return false;
    }

    public boolean themLichGacThi(LichThiDTO lt) {
        lichGacThi.add(lt);
        return true;
    }

    // Phương thức tính khoảng cách giữa hai lịch thi
    public static int tinhKhoangCach(LichThiDTO lich1, LichThiDTO lich2) {
        int ngayKhoangCach = Math.abs((int) ((lich1.getNgay().getTime() - lich2.getNgay().getTime()) / (1000 * 60 * 60 * 24)));

        int tietKhoangCach = 0;
        if (lich1.getNgay().equals(lich2.getNgay())) {
            if (lich1.getTietKetThuc() < lich2.getTietBatDau()) {
                tietKhoangCach = lich2.getTietBatDau() - lich1.getTietKetThuc();
            } else if (lich2.getTietKetThuc() < lich1.getTietBatDau()) {
                tietKhoangCach = lich1.getTietBatDau() - lich2.getTietKetThuc();
            } else {
                tietKhoangCach = 0;
            }
        }

        return ngayKhoangCach * 2 + tietKhoangCach;
    }

    public int getKhoangCachGanNhat(LichThiDTO lichthiMoi) {
        if (lichGacThi.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        return lichGacThi.stream()
                .mapToInt(lichthi -> tinhKhoangCach(lichthi, lichthiMoi))
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GiangVienDTO) {
            return this.tenGiangVien.equals(((GiangVienDTO) obj).tenGiangVien);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return tenGiangVien.hashCode();
    }
}
