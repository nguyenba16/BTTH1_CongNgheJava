import java.util.Random;
import java.util.Scanner;

//### Bài 1:
//Chỉ sử dụng bán kính r$, không được sử dụng bất kỳ hằng
// số nào khác, hãy xấp xỉ diện tích của hình tròn tâm $O(0 ,0)$ bán kính $r$.
public class Main {
    public static double calculateAreaOfCircle (int r, int points){
        Random random = new Random();
        int pointsInsideCircle = 0;
        for (int i = 0; i < points; i++) {
            double x = (random.nextDouble() * 2 - 1) * r;
            double y = (random.nextDouble() * 2 - 1) * r;
            if (x * x + y * y <= r * r) {
                pointsInsideCircle++;
            }
        }
        return (double) pointsInsideCircle / points * (4 * r * r);
    }

    public static void main(String[] args) {
        System.out.print("Nhập giá trị của r: ");
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int points = 1000000;
        double kq = calculateAreaOfCircle(r, points);
        System.out.println("Diện tích của hình tròn có bán kính r=" +r+ " là: "+kq);
    }
}