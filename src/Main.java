import java.util.Scanner;
import java.util.*;

class Point {
    int x;
    int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static int calculate(Point p1, Point p2, Point p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
    }

    public static List<Point> convexresult(Point[] points) {
        Arrays.sort(points, (a, b) -> {
            if (a.y == b.y)
                return a.x - b.x;
            return a.y - b.y;
        });
        List<Point> result = new ArrayList<>();

        for (Point p : points) {
            while (result.size() >= 2 && calculate(result.get(result.size() - 2), result.get(result.size() - 1), p) <= 0) {
                result.remove(result.size() - 1);
            }
            result.add(p);
        }
        int lowerSize = result.size();

        for (int i = points.length - 2; i >= 0; i--) {
            Point p = points[i];
            while (result.size() > lowerSize && calculate(result.get(result.size() - 2), result.get(result.size() - 1), p) <= 0) {
                result.remove(result.size() - 1);
            }
            result.add(p);
        }
        Set<Point> uniqueresult = new LinkedHashSet<>(result);
        return new ArrayList<>(uniqueresult);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số lượng trạm : ");
        int n = scanner.nextInt();
        Point[] points = new Point[n];

        System.out.println("Nhập tọa độ các trạm là :");
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }
        scanner.close();

        List<Point> result = convexresult(points);
        result.sort((a, b) -> {
            if (a.x == b.x){
                return a.y - b.y;
            }
            return a.x - b.x;
        });
        System.out.println("Kết quả :");
        for (Point p : result) {
            System.out.println(p.x + " " + p.y);
        }
    }
}
