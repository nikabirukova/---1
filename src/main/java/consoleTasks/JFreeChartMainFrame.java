package consoleTasks;

import consoleTasks.analyticalMethod.FFunction;
import consoleTasks.tableMethod.FileInterpolator;
import consoleTasks.tableMethod.withArrayList.ListInterpolation;
import consoleTasks.tableMethod.withTreeMap.TreeMapInterpolation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class JFreeChartMainFrame extends JFrame{
    private JFreeChart chart;
    private JTextField textFieldStart;
    private JTextField textFieldEnd;
    private JTextField textFieldStep;
    private JTextField textFieldParameter;
    private JTextField textFieldFunction;
    private XYSeries functionSeries;
    private XYSeries derivativeFunctionSeries;

    /**
     * Create the frame.
     */
    public JFreeChartMainFrame() {
        // set window properties
        setResizable(true);
        setTitle("JFree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 800);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 15, 5, 15));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        createUI(contentPane);
        createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        contentPane.add(chartPanel, BorderLayout.CENTER);
    }

    private void createUI(JPanel contentPane) {
        // Bottom grid for 2 panel elements with buttons and parameters
        JPanel panel = new JPanel(new GridLayout(2, 1));
        contentPane.add(panel, BorderLayout.SOUTH);

        // set down panel with start, end and step
        JPanel bottomPanelParameters = new JPanel();
        panel.add(bottomPanelParameters);

        bottomPanelParameters.add(new JLabel("Start: "));
        textFieldStart = new JTextField("-9.0");
        textFieldStart.setColumns(4);
        bottomPanelParameters.add(textFieldStart);

        bottomPanelParameters.add(new JLabel("End: "));
        textFieldEnd = new JTextField("9.0");
        textFieldEnd.setColumns(4);
        bottomPanelParameters.add(textFieldEnd);

        bottomPanelParameters.add(new JLabel("Step: "));
        textFieldStep = new JTextField("0.01");
        textFieldStep.setColumns(4);
        bottomPanelParameters.add(textFieldStep);

        // set down panel with PLOT, EXIT and INTERPOLATE buttons
        JPanel bottomButtonsPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) bottomButtonsPanel.getLayout();
        flowLayout.setHgap(15);
        panel.add(bottomButtonsPanel);

        JButton buttonPlot = new JButton("REFRESH CHART");
        buttonPlot.addActionListener(event -> {
            double start = Double.parseDouble(textFieldStart.getText());
            double end = Double.parseDouble(textFieldEnd.getText());
            double step = Double.parseDouble(textFieldStep.getText());
            double a = Double.parseDouble(textFieldParameter.getText());

            refreshSeries(start, end, step, a);
            chart.setTitle("f(x) = " + textFieldFunction.getText());
        });
        bottomButtonsPanel.add(buttonPlot);

        JButton buttonInterpolate = new JButton("INTERPOLATE");
        buttonInterpolate.addActionListener(event -> {
            TreeMapInterpolation fileInter = new TreeMapInterpolation();
            try {
                FileInterpolator.readFromFile(fileInter,"./src/main/java/Files/sinXInterpolation.txt");
                double start = Double.parseDouble(textFieldStart.getText());
                double end = Double.parseDouble(textFieldEnd.getText());
                double step = Double.parseDouble(textFieldStep.getText());
                functionSeries.clear();
                derivativeFunctionSeries.clear();
                for (double x = start; x < end; x += step) {
                    functionSeries.add(x, fileInter.evalf(x));
                }
                chart.setTitle("f(x) = sin(x)");
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        bottomButtonsPanel.add(buttonInterpolate);

        JButton buttonExit = new JButton("EXIT");
        buttonExit.addActionListener(event -> System.exit(0));
        bottomButtonsPanel.add(buttonExit);

        // set panel with a parameter for function
        JPanel topPanel = new JPanel();
        contentPane.add(topPanel, BorderLayout.NORTH);

        topPanel.add(new JLabel("f(x) = "));
        textFieldFunction = new JTextField("sin(a * x) / x");
        textFieldFunction.setColumns(40);
        topPanel.add(textFieldFunction);

        topPanel.add(new JLabel("a:"));
        textFieldParameter = new JTextField("1.0");
        textFieldParameter.setColumns(4);
        topPanel.add(textFieldParameter);
    }

    private void createChart() {
        functionSeries = new XYSeries("Function");
        derivativeFunctionSeries = new XYSeries("Derivative");

        double start = -9.0;
        double end = 9.0;
        double step = 0.01;
        double a = Double.parseDouble(textFieldParameter.getText());

        refreshSeries(start, end, step, a);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(functionSeries);
        dataset.addSeries(derivativeFunctionSeries);

        chart = ChartFactory.createXYLineChart(
                "f(x) = sin(a * x) / x", // chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL, true, // include legend
                true, // tooltips
                false // urls
        );

        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
    }

    private void refreshSeries(double start, double end, double step, double a) {
        FFunction function = new FFunction(textFieldFunction.getText(), a);
        functionSeries.clear();
        derivativeFunctionSeries.clear();
        for (double x = start; x < end; x += step) {
            functionSeries.add(x, function.evalf(x));
            derivativeFunctionSeries.add(x, function.evalfDerivative(x));
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFreeChartMainFrame frame = new JFreeChartMainFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
