package OptionChainAnalyzer;

import org.jfree.ui.RefineryUtilities;

public final class App {
    private App() {
    }

    public static void main(String[] args) {

        System.out.println("Parsing NSE Option Chain");

        OptionChainGraph chart = new OptionChainGraph("TCS");

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

    }
}
