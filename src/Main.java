import java.io.*;
import java.util.*;

public class Main {
    private static final int SO_TU_TOI_DA = 5; // Số từ tối đa trong câu sinh ra
    private static Map<String, Integer> tanSuatTu = new HashMap<>(); // Tần suất từ
    private static Map<String, List<String>> tuKeTiep = new HashMap<>(); // Danh sách từ đi sau từ hiện tại
    private static Set<String> tuVung = new HashSet<>(); // Tập hợp từ hợp lệ

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Đang tải dữ liệu...");
        if (!taiDuLieu("UIT-ViOCD.txt")) {
            System.out.println("Lỗi tải dữ liệu từ file");
            return;
        }

        // Vòng lặp để nhập lại nếu từ không hợp lệ
        String tuBatDau;
        while (true) {
            System.out.print("Nhập từ đầu tiên: ");
            tuBatDau = sc.next().toLowerCase();
            if (tuVung.contains(tuBatDau)) {
                break;
            } else {
                System.out.println("Từ không tồn tại. Nhập lại: ");
            }
        }
        sc.close();
        String kq = sinhVanBan(tuBatDau);
        System.out.println("Output: " + kq);
    }

    private static boolean taiDuLieu(String fileName) {
        try {
            BufferedReader docTep = new BufferedReader(new FileReader(fileName));
            String col;

            while ((col = docTep.readLine()) != null) {
                String[] cacTu = col.toLowerCase().split("\\s+"); // Tách các từ
                for (String tu : cacTu) {
                    tanSuatTu.put(tu, tanSuatTu.getOrDefault(tu, 0) + 1); // Đếm số lần xuất hiện của từ
                }

                for (int i = 0; i < cacTu.length - 1; i++) {
                    String tuHienTai = cacTu[i];
                    String tuKe = cacTu[i + 1];
                    tuKeTiep.putIfAbsent(tuHienTai, new ArrayList<>());
                    tuKeTiep.get(tuHienTai).add(tuKe);
                }
            }
            docTep.close();
            for (String tu : tanSuatTu.keySet()) {
                if (tanSuatTu.get(tu) >= 5) {
                    tuVung.add(tu);
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Lỗi: " + e.getMessage());
            return false;
        }
    }

    private static String sinhVanBan(String tuBatDau) {
        StringBuilder vanBan = new StringBuilder(tuBatDau);
        String tuHienTai = tuBatDau;

        for (int i = 0; i < SO_TU_TOI_DA - 1; i++) { // Giới hạn tổng cộng 5 từ
            if (!tuKeTiep.containsKey(tuHienTai) || tuKeTiep.get(tuHienTai).isEmpty()) {
                break;
            }
            List<String> danhSachTu = tuKeTiep.get(tuHienTai);
            String tuMoi = danhSachTu.get(0);
            vanBan.append(" ").append(tuMoi);
            tuHienTai = tuMoi;
        }
        return vanBan.toString();
    }
}
