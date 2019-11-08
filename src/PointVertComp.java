import java.util.Comparator;

public class PointVertComp implements Comparator <Point> {

    @Override
    public int compare(Point o1, Point o2) {
        return o1.compareToY(o2);
    }
}
