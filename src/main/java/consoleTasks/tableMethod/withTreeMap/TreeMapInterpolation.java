package consoleTasks.tableMethod.withTreeMap;

import consoleTasks.tableMethod.Interpolator;
import consoleTasks.tableMethod.Point2D;

import java.util.TreeMap;

public class TreeMapInterpolation extends Interpolator {

    TreeMap<Integer, Point2D> data;

    public TreeMapInterpolation() {
        data = new TreeMap<>();
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
        if (numPoints() == 0) {
            data.put(0, pt);
        } else {
            data.put(data.lastKey() + 1, pt);
        }
    }

    @Override
    public Point2D getPoint(int index) {
        return data.get(index);
    }

    @Override
    public void setPoint(int index, Point2D pt) {
        data.replace(index, pt);
    }

    @Override
    public void removeLastPoint() {
        data.remove(data.lastKey());
    }

    @Override
    public void sort() {
        for (int i = 0; i < numPoints(); i++) {
            for (int j = 0; j < numPoints() - i - 1; j++) {
                if (getPoint(j).getX() > getPoint(j + 1).getX()) {
                    Point2D temp = getPoint(j);
                    setPoint(j, getPoint(j + 1));
                    setPoint(j + 1, temp);
                }
            }
        }
    }
}
