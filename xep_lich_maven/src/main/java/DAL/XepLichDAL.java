/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.GiangVienDTO;
import DTO.LichThiDTO;
import DTO.LichThiXepResult;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 *
 * @author ASUS
 */
public class XepLichDAL {

    private List<LichThiDTO> Cathichuaxep;
    private List<LichThiXepResult> ketquaxeplich;

    public List<LichThiXepResult> xepLichGacThi(List<LichThiDTO> LstLichThi, List<GiangVienDTO> LstGiangVien) {
        ketquaxeplich = new ArrayList<>();
        List<LichThiDTO> Cathidaxep = new ArrayList<>();
        Cathichuaxep = new ArrayList<>();

        // Chiến lược sắp xếp heuristic cho LichThi
        List<LichThiDTO> danhsachlichthi = LstLichThi.stream()
                .sorted(Comparator.comparing(LichThiDTO::getSoGVCanCap).reversed()
                        .thenComparing(LichThiDTO::getNgay)
                        .thenComparing(LichThiDTO::getTietBatDau)
                        .thenComparing(LichThiDTO::getTietKetThuc))
                .collect(Collectors.toList());

        int Solichtoida = danhsachlichthi.stream().mapToInt(LichThiDTO::getSoGVCanCap).sum() / LstGiangVien.size() + 1;

        for (LichThiDTO lichthi : danhsachlichthi) {
            // Chiến lược sắp xếp heuristic cho giảng viên
            List<GiangVienDTO> sortedGiangVien = LstGiangVien.stream()
                    .filter(gv -> !gv.kiemTraTrungLichDay(lichthi) && !gv.kiemTraTrungLichThi(lichthi))
                    .sorted(Comparator.comparingInt((GiangVienDTO gv) -> gv.getLichGacThi().size())
                            .thenComparingInt(gv -> gv.getKhoangCachGanNhat(lichthi))
                            .thenComparingInt(gv -> gv.getLichDay().size()))
                    .collect(Collectors.toList());

            LichThiXepResult ketqua = new LichThiXepResult(lichthi);
            for (GiangVienDTO giangvien : sortedGiangVien) {
                if (lichthi.getSoGVCanCap() == 0) {
                    break;
                }
                if (giangvien.themLichGacThi(lichthi, Solichtoida)) {
                    ketqua.getGiangViens().add(giangvien);
                    lichthi.setSoGVCanCap(lichthi.getSoGVCanCap() - 1);
                }
            }

            if (lichthi.getSoGVCanCap() == 0) {
                Cathidaxep.add(lichthi);
                ketquaxeplich.add(ketqua);
            }
        }

        Cathichuaxep = LstLichThi.stream().filter(t -> !Cathidaxep.contains(t)).collect(Collectors.toList());
        xepLichBacktracking(LstGiangVien, Solichtoida);

        return ketquaxeplich;
    }

    public int[][] chuyenDoiXepLichSangMang(List<LichThiXepResult> ketquaxeplich, List<GiangVienDTO> lstgiangvien) {
        sapxepKetQua();
        // Tạo danh sách các cột duy nhất
        List<Map<String, Object>> Uniquecolumns = ketquaxeplich.stream()
                .sorted(Comparator.comparing(r -> r.getLichThi().getNgay()))
                .map(r -> {
                    if (r.getLichThi() == null) {
                        return null; // Xử lý nếu `LichThi` là null
                    }
                    Map<String, Object> map = new HashMap<>();
                    map.put("Ngay", r.getLichThi().getNgay());
                    map.put("TietBatDau", r.getLichThi().getTietBatDau());
                    map.put("TietKetThuc", r.getLichThi().getTietKetThuc());
                    return map;
                })
                .filter(Objects::nonNull) // Lọc bỏ giá trị null
                .collect(Collectors.toList());

        // Ánh xạ cột vào chỉ số
        Map<Map<String, Object>, Integer> columnIndexMap = new HashMap<>();
        for (int i = 0; i < Uniquecolumns.size(); i++) {
            columnIndexMap.put(Uniquecolumns.get(i), i);
        }

        // Tạo mảng kết quả
        int[][] resultMatrix = new int[lstgiangvien.size()][Uniquecolumns.size()];
        for (LichThiXepResult kq : ketquaxeplich) {
            if (kq.getLichThi() == null) {
                continue;
            }

            Map<String, Object> lich = new HashMap<>();
            lich.put("Ngay", kq.getLichThi().getNgay());
            lich.put("TietBatDau", kq.getLichThi().getTietBatDau());
            lich.put("TietKetThuc", kq.getLichThi().getTietKetThuc());

            int columnIndex = columnIndexMap.get(lich);

            for (GiangVienDTO gv : kq.getGiangViens()) {
                int rowIndex = lstgiangvien.indexOf(gv);
                resultMatrix[rowIndex][columnIndex] = 1;
            }
        }

        return resultMatrix;
    }

    public static int danhGiaLichThi(List<LichThiXepResult> danhSachKetQua) {
        return danhSachKetQua.stream()
                .mapToInt(lichThiResult -> lichThiResult.getGiangViens().stream()
                .mapToInt(gv -> gv.getLichGacThi().stream()
                .mapToInt(lichGacThi -> GiangVienDTO.tinhKhoangCach(lichThiResult.getLichThi(), lichGacThi))
                .sum())
                .sum())
                .sum();
    }

    public List<LichThiDTO> layDanhSachLichChuaXep() {
        return Cathichuaxep != null && !Cathichuaxep.isEmpty() ? Cathichuaxep : new ArrayList<>();
    }

    public boolean xepLichBacktracking(List<GiangVienDTO> LstGiangVien, int Solichtoida) {
        if (Cathichuaxep.isEmpty()) {
            return true;
        }

        LichThiDTO lichthi = Cathichuaxep.get(0);

        List<GiangVienDTO> sortedGiangVien = LstGiangVien.stream()
                .filter(gv -> !gv.kiemTraTrungLichDay(lichthi) && !gv.kiemTraTrungLichThi(lichthi))
                .collect(Collectors.toList());

        for (GiangVienDTO giangvien : sortedGiangVien) {
            if (lichthi.getSoGVCanCap() > 0 && giangvien.themLichGacThi(lichthi)) {
                lichthi.setSoGVCanCap(lichthi.getSoGVCanCap() - 1);

                if (lichthi.getSoGVCanCap() == 0) {
                    Cathichuaxep.remove(lichthi);
                }

                if (xepLichBacktracking(LstGiangVien, Solichtoida)) {
                    return true;
                }

                giangvien.getLichGacThi().remove(lichthi);
                lichthi.setSoGVCanCap(lichthi.getSoGVCanCap() + 1);
            }
        }
        return false;
    }

    private void sapxepKetQua() {
        ketquaxeplich.sort(Comparator
                .comparing((LichThiXepResult r) -> r.getLichThi().getNgay())
                .thenComparing(r -> r.getLichThi().getTietBatDau())
                .thenComparing(r -> r.getLichThi().getTietKetThuc()));

    }

    private void dotBien(List<LichThiXepResult> ketquaxeplich, List<GiangVienDTO> LstGiangVien, int soDotBien) {
        Random rand = new Random();
        for (int i = 0; i < soDotBien; i++) {
            LichThiXepResult randomLich = ketquaxeplich.get(rand.nextInt(ketquaxeplich.size()));

            if (!randomLich.getGiangViens().isEmpty()) {
                GiangVienDTO randomGV = randomLich.getGiangViens().get(rand.nextInt(randomLich.getGiangViens().size()));

                GiangVienDTO giangVienThayThe = LstGiangVien.stream()
                        .filter(gv -> !gv.kiemTraTrungLichDay(randomLich.getLichThi()))
                        .filter(gv -> !gv.kiemTraTrungLichThi(randomLich.getLichThi()))
                        .filter(gv -> !randomLich.getGiangViens().contains(gv))
                        .min(Comparator.comparingInt(gv -> gv.getLichGacThi().size()))
                        .orElse(null);

                if (giangVienThayThe != null) {
                    randomLich.getGiangViens().remove(randomGV);
                    randomLich.getGiangViens().add(giangVienThayThe);

                    randomGV.getLichGacThi().remove(randomLich.getLichThi());
                    giangVienThayThe.themLichGacThi(randomLich.getLichThi());
                }
            }
        }
    }

    public void caiTienKetQua(List<LichThiXepResult> ketquaxeplich, List<GiangVienDTO> LstGiangVien) {
        final int soTheHe = 75;
        if (ketquaxeplich == null || LstGiangVien == null || soTheHe <= 0) {
            throw new IllegalArgumentException("Tham số không hợp lệ: Danh sách hoặc số thế hệ không đúng.");
        }

        int bestScore = danhGiaLichThi(ketquaxeplich);

        for (int i = 0; i < soTheHe; i++) {
            // Tạo bản sao của danh sách hiện tại
            List<LichThiXepResult> cloneKetQua = ketquaxeplich.stream()
                    .map(kq -> new LichThiXepResult(kq.getLichThi(), new ArrayList<>(kq.getGiangViens())))
                    .collect(Collectors.toList());

            // Thực hiện đột biến
            dotBien(cloneKetQua, LstGiangVien, 30); // Số lượng đột biến được truyền ở đây là 30

            // Tính điểm fitness sau khi đột biến
            int newScore = danhGiaLichThi(cloneKetQua);

            // Nếu kết quả mới tốt hơn, chấp nhận thay đổi
            if (newScore < bestScore) {
                ketquaxeplich.clear();
                ketquaxeplich.addAll(cloneKetQua);
                bestScore = newScore;
            }
        }
    }

}
