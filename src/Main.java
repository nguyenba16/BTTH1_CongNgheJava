import java.util.Random;
//### Bài 2:
//Xấp xỉ giá trị của $\pi$ thông qua đường tròn đơn vị (tâm $O(0, 0)$ bán kính $r=1$).

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
        int r = 1;
        int points = 1000000;
        double dientich = calculateAreaOfCircle(r, points);
        double pi = dientich/(r*r);
        System.out.println("Gia tri cua pi la: "+ pi);
    }
}