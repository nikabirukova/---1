package consoleTasks.tableMethod.withTreeSet;

import consoleTasks.tableMethod.Interpolator;
import consoleTasks.tableMethod.Point2D;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetInterpolation extends Interpolator {

    TreeSet<Point2D> data;

    public TreeSetInterpolation() {
        data = new TreeSet<>();
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int numPoints() {
        return data.size();
    }

    @Override
    public void addPoint(Point2D pt) {
        data.add(pt);
    }

    @Override
    public Point2D getPoint(int index) {
        Iterator<Point2D> iterator = data.iterator();

        for (int i = 0; i < numPoints(); i++) {
            Point2D point = iterator.next();
            if (index == i) {
                return point;
            }
        }
        return null;
    }

    @Override
    public void setPoint(int index, Point2D pt) {
        Iterator<Point2D> iterator = data.iterator();

        for (int i = 0; i < numPoints(); i++) {
            Point2D point = iterator.next();
            if (index == i) {
                data.remove(point);
                addPoint(pt);
                break;
            }
        }
    }

    @Override
    public void removeLastPoint() {
    }

    @Override
    public void sort() {
    }
}
