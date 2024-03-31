package consoleTasks.tableMethod;

public abstract class Point {

    private double[] coords = null;

    public Point(int num) {
        this.coords = new double[num];
    }

    public void setCoord(int num, double x) {
        coords[num-1] = x;
    }

    public double getCoord(int num) {
        return coords[num-1];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("(");
        for (double x : coords) {
            res.append(x).append(", ");
        }
        return res.substring(0, res.length()-2) + ")";
    }
}
