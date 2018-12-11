package OptionChainAnalyzer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

class LastTradedPriceParser {

    public static String getLastTradedPrice(String symbol) {

        float lastTradedPrice = -1.0f;
        String price = null;
        String priceChange = null;

        try {
            Document doc = Jsoup.connect(
                    // "https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuote.jsp?symbol="
                    "https://in.finance.yahoo.com/quote/" + symbol + ".NS").get();

            Elements els = doc.select("div[class=D(ib) Mend(20px)] > span");
            price = els.get(0).text().replace(",", "");
            priceChange = els.get(1).text().replace(",", "");

            lastTradedPrice =  Float.valueOf(price);

        } catch (Exception e) {

            System.out.println(e.toString());
        }

        return price + " " + priceChange ;
    }
}