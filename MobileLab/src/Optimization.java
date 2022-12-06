import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Optimization {


    public static void main(String[] args) {
        List<Segment> allSegments = new ArrayList<>();

        Segment s1 = new Segment(15, 0, 15, 3210);
        Segment s2 = new Segment(0, 15, 6000, 15);
        Segment s3 = new Segment(1500, 0, 1500, 3210);
        Segment s4 = new Segment(15, 1015, 1500, 1015);
        Segment s5 = new Segment(15, 2015, 1500, 2015);
        Segment s6 = new Segment(15, 3015, 1500, 3015);
        Segment s7 = new Segment(2550, 0, 2550, 3210);
        Segment s8 = new Segment(1500, 1415, 2550, 1415);
        Segment s9 = new Segment(1500, 2815, 2550, 2815);
        Segment s10 = new Segment(3991, 0, 3991, 3210);
        Segment s11 = new Segment(2550, 515, 3991, 515);
        Segment s12 = new Segment(2550, 1015, 3991, 1015);
        Segment s13 = new Segment(2550, 1515, 3991, 1515);
        Segment s14 = new Segment(2550, 2015, 3991, 2015);
        Segment s15 = new Segment(2550, 2765, 3991, 2765);
        Segment s16 = new Segment(3250, 2015, 3250, 2765);
        Segment s17 = new Segment(4789, 0, 4789, 3210);
        Segment s18 = new Segment(3991, 1515, 4789, 1515);
        Segment s19 = new Segment(3991, 3015, 4789, 3015);
        Segment s20 = new Segment(5843, 0, 5843, 3210);
        Segment s21 = new Segment(4789, 1123, 5843, 1123);
        Segment s22 = new Segment(5316, 15, 5316, 1123);

        allSegments.add(s1);
        allSegments.add(s2);
        allSegments.add(s3);
        allSegments.add(s4);
        allSegments.add(s5);
        allSegments.add(s6);
        allSegments.add(s7);
        allSegments.add(s8);
        allSegments.add(s9);
        allSegments.add(s10);
        allSegments.add(s11);
        allSegments.add(s12);
        allSegments.add(s13);
        allSegments.add(s14);
        allSegments.add(s15);
        allSegments.add(s16);
        allSegments.add(s17);
        allSegments.add(s18);
        allSegments.add(s19);
        allSegments.add(s20);
        allSegments.add(s21);
        allSegments.add(s22);

        Figure f1 = new Figure(new Point(0, 0), new Point(1470, 0),
                new Point(1200, 1000), new Point(0, 1000),
                new Point(15, 15));

        Figure f2 = new Figure(new Point(0, 0), new Point(1470, 0),
                new Point(1200, 1000), new Point(0, 1000),
                new Point(15, 1015));

        Figure f3 = new Figure(new Point(15, 0), new Point(1485, 0),
                new Point(1485, 1000), new Point(285, 1000),
                new Point(15, 2015));

        Figure f4 = new Figure(new Point(0, 0), new Point(798, 0),
                new Point(798, 1485), new Point(0, 1000),
                new Point(3991, 15));

        Figure f5 = new Figure(new Point(0, 0), new Point(798, 0),
                new Point(798, 1200), new Point(0, 1485),
                new Point(3991, 1515));

        Figure f6 = new Figure(new Point(15, 0), new Point(685, 0),
                new Point(600, 735), new Point(150, 735),
                new Point(2550, 2015));

        allSegments.addAll(fromFigureToSegments(f1));
        allSegments.addAll(fromFigureToSegments(f2));
        allSegments.addAll(fromFigureToSegments(f3));
        allSegments.addAll(fromFigureToSegments(f4));
        allSegments.addAll(fromFigureToSegments(f5));
        allSegments.addAll(fromFigureToSegments(f6));

        System.out.println(allSegments.size());
        for (int i = 0; i < allSegments.size(); i++) {
            System.out.println(allSegments.get(i).toString());
        }

        allSegments = removeOverlaidSegments(allSegments);
        System.out.println(allSegments.size());

        for (int i = 0; i < allSegments.size(); i++) {
            System.out.println(allSegments.get(i).toString());
        }

        System.out.println("После оптимизации");
        List<SegmentByPoint> res = optimizingSegments(allSegments);

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).toString());
        }
    }


    //преобразование фигуры в отрезки
    public static List<Segment> fromFigureToSegments(Figure figure) {
        List<Segment> segments = new ArrayList<>();
        Segment segment1 = new Segment(figure.point1.x + figure.pointSKL.x,
                figure.point1.y + figure.pointSKL.y,
                figure.point2.x + figure.pointSKL.x,
                figure.point2.y + figure.pointSKL.y);


        Segment segment2 = new Segment(figure.point2.x + figure.pointSKL.x,
                figure.point2.y + figure.pointSKL.y,
                figure.point3.x + figure.pointSKL.x,
                figure.point3.y + figure.pointSKL.y);

        Segment segment3 = new Segment(figure.point3.x + figure.pointSKL.x,
                figure.point3.y + figure.pointSKL.y,
                figure.point4.x + figure.pointSKL.x,
                figure.point4.y + figure.pointSKL.y);

        Segment segment4 = new Segment(figure.point4.x + figure.pointSKL.x,
                figure.point4.y + figure.pointSKL.y,
                figure.point1.x + figure.pointSKL.x,
                figure.point1.y + figure.pointSKL.y);
        segments.add(segment1);
        segments.add(segment2);
        segments.add(segment3);
        segments.add(segment4);

        return segments;
    }


    //удаление наложенных отрезков
    public static List<Segment> removeOverlaidSegments(List<Segment> allSegments) {
        List<Segment> result = new ArrayList<>();
        result.addAll(allSegments);

        Set<Segment> set = new HashSet<>();
        List<Segment> seg = new ArrayList<>();

        for (int i = 0; i < allSegments.size(); i++) {
            for (int j = 0; j < result.size(); j++) {
                if ((allSegments.get(i).y1 < result.get(j).y1 &&
                        allSegments.get(i).y2 > result.get(j).y1 &&
                        allSegments.get(i).y1 < result.get(j).y2 &&
                        allSegments.get(i).y2 > result.get(j).y2) &&
                        allSegments.get(i).x1 == result.get(j).x1 &&
                        allSegments.get(i).x2 == result.get(j).x2) set.add(result.get(j));


                if ((allSegments.get(i).x1 < result.get(j).x1 &&
                        allSegments.get(i).x2 > result.get(j).x1 &&
                        allSegments.get(i).x1 < result.get(j).x2 &&
                        allSegments.get(i).x2 > result.get(j).x2) &&
                        allSegments.get(i).y1 == result.get(j).y1 &&
                        allSegments.get(i).y2 == result.get(j).y2) set.add(result.get(j));

            }
        }
        for (Segment s : set) {
            result.remove(s);
        }

        return result;
    }

    // оптимизация списка отрезков
    public static List<SegmentByPoint> optimizingSegments(List<Segment> allSegments) {

        List<SegmentByPoint> segments = new ArrayList<>();

        List<SegmentByPoint> result = new ArrayList<>();

        for (Segment s : allSegments) {
            segments.add(toSegmentByPoint(s));
        }
        Point controlPoint = new Point(0, 0);
        int minDist = Integer.MAX_VALUE;
        int index = 0;
        int p = 0;
        SegmentByPoint res;
        Point tempPoint;

        while (!segments.isEmpty()) {
            for (int j = 0; j < segments.size(); j++) {
                if (minDist > Math.min(distance(controlPoint, segments.get(j).point1),
                        distance(controlPoint, segments.get(j).point2))) {
                    minDist = Math.min(distance(controlPoint, segments.get(j).point1),
                            distance(controlPoint, segments.get(j).point2));
                    index = j;
                    if (distance(controlPoint, segments.get(j).point1) < distance(controlPoint, segments.get(j).point2))
                        p = 1;
                    else p = 2;
                }
            }

            res = segments.get(index);
            if (p == 2) {
                tempPoint = res.point1;
                res.point1 = res.point2;
                res.point2 = tempPoint;
            }

            result.add(res);
            segments.remove(segments.get(index));
            controlPoint = res.point2;
            minDist = Integer.MAX_VALUE;

        }

        return result;
    }


    //расстояние между двумя точками
    public static int distance(Point point1, Point point2) {
        return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
    }

    // преобразовние отрезка в отрезок по двум точкам
    public static SegmentByPoint toSegmentByPoint(Segment segment) {
        return new SegmentByPoint(new Point(segment.x1, segment.y1), new Point(segment.x2, segment.y2));
    }
}


class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Figure {
    Point point1;
    Point point2;
    Point point3;
    Point point4;
    Point pointSKL;

    public Figure(Point point1, Point point2, Point point3, Point point4, Point pointSKL) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.pointSKL = pointSKL;
    }
}

// отрезок представлен 2мя точками
class SegmentByPoint {
    Point point1;
    Point point2;

    public SegmentByPoint(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    @Override
    public String toString() {
        return point1.x + "," + point1.y + "," + point2.x + "," + point2.y;
    }
}

class Segment {
    int x1;
    int y1;
    int x2;
    int y2;

    public Segment(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return x1 + "," + y1 + "," + x2 + "," + y2;
    }
}
