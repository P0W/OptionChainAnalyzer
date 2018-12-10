package OptionChainAnalyzer;

import java.awt.Color;
import java.awt.BasicStroke;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class OptionChainGraph extends ApplicationFrame {

    public OptionChainGraph( String symbol) {
        super("NSE Live Option Chain");
        JFreeChart xylineChart = ChartFactory.createXYLineChart("CALL V/S PUT Open Interest for " + symbol, "Strike Price", "Open Interest", createDataset(symbol),
                PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = xylineChart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(3.0f));
        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    private XYDataset createDataset( String symbol ) {
        final XYSeries callSeries = new XYSeries("CALL OPTION");
        final XYSeries putSeries = new XYSeries("PUT OPTION");

        for (OptionChain c : OptionChainParser.parse(symbol)) {

            callSeries.add(c.getStrikePrice(), c.getCallOI());
            putSeries.add(c.getStrikePrice(), c.getPutOI());
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(callSeries);
        dataset.addSeries(putSeries);

        return dataset;
    }

}