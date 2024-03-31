package consoleTasks.tableMethod;

import consoleTasks.tableMethod.withArrayList.ListInterpolation;

import java.io.*;
import java.util.StringTokenizer;

public class FileInterpolator {

    public static void readFromFile(Interpolator interpolator, String pathToFile) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(pathToFile));
        interpolator.clear();
        bf.readLine(); // for skip first line
        String s;
        while ((s = bf.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s);
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            interpolator.addPoint(new Point2D(x, y));
        }
        bf.close();
    }

    public static void writeToFile(Interpolator interpolator, String pathToFIle) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(pathToFIle));
        pw.printf("%9s%20s\n", "x", "y");
        for (int i = 0; i < interpolator.numPoints(); i++) {
            pw.println(interpolator.getPoint(i).getX() + "\t" + interpolator.getPoint(i).getY());
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        ListInterpolation fun = new ListInterpolation();
        int num;
        double x;
        java.util.Scanner in = new java.util.Scanner(System.in);

        do {
            System.out.print("Количество точек: ");
            num = in.nextInt();
        } while (num <= 0);

        for (int i = 0; i < num; i++) {
            x = Math.random() * 30.0 - 15.0; // from 1 to 5
            fun.addPoint(new Point2D(x, Math.sin(x)));
        }

        fun.sort();

        x = 0.5*(fun.getPoint(0).getX() + fun.getPoint(fun.numPoints()- 1).getX());
        System.out.println("Значение интеролляции fun(" + x + ") = " + fun.evalf(x));
        System.out.println("Точное значение sin(" + x + ") = " + Math.sin(x));
        System.out.println("Абсолютная ошибка = " + Math.abs(fun.evalf(x)-Math.sin(x)));

        FileInterpolator.writeToFile(fun ,"./src/main/java/Files/sinXInterpolation.txt");
    }
}
