package consoleTasks.tableMethod.withArrayList;

import consoleTasks.tableMethod.Interpolator;
import consoleTasks.tableMethod.Point2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListInterpolation extends Interpolator {

    private List<Point2D> data;

    public ListInterpolation(List<Point2D> data) {
        this.data = data;
    }

    public ListInterpolation() {
        data = new ArrayList<>();
    }

    public ListInterpolation(Point2D[] data) {
        this();
        this.data.addAll(Arrays.asList(data));
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
        return data.get(index);
    }

    @Override
    public void setPoint(int index, Point2D pt) {
        data.set(index, pt);
    }

    @Override
    public void removeLastPoint() {
        data.remove(data.size()-1);
    }

    @Override
    public void sort() {
        Collections.sort(data);
    }
}
