package OptionChainAnalyzer;

import org.jfree.ui.RefineryUtilities;

public final class App {

    private static final String symbol = "TCS";

    public static void main(String[] args) {

        System.out.println("Parsing NSE Option Chain");

        OptionChainGraph chart = new OptionChainGraph(symbol);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

        Thread thread = new Thread(() -> {
            while (true) {

                String lastTradedPrice = LastTradedPriceParser.getLastTradedPrice(symbol);

                System.out.println("Last Traded Price " + lastTradedPrice);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }
}
