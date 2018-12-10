package OptionChainAnalyzer;

import java.util.List;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

class OptionChainParser {
    private static final int CALL_OI_TAG = 1;
    private static final int PUT_OI_TAG = 21;
    private static final int STRIKE_PRICE_TAG = 11;
    private static final int MAX_COLS = 11;

    public static List<OptionChain> parse(String symbol) {
        List<OptionChain> optionChain = new ArrayList<OptionChain>();

        try {
            Document doc = Jsoup.connect(
                    "https://www.nseindia.com/live_market/dynaContent/live_watch/option_chain/optionKeys.jsp?symbol="
                            + symbol)
                    .get();

            Elements els = doc.select("table#octable > tbody");

            for (Element row : els.select("tr")) {

                Elements tds = row.select("td");

                if (tds.size() >= MAX_COLS) {

                    String oiCall = tds.get(CALL_OI_TAG).text();
                    String oiPut = tds.get(PUT_OI_TAG).text();
                    String strikePrice = tds.get(STRIKE_PRICE_TAG).text();

                    if (!oiCall.equals("-") && !oiPut.equals("-")) {
                        OptionChain c = new OptionChain();

                        c.setCallOI(oiCall);
                        c.setPutOI(oiPut);
                        c.setStrikePrice(strikePrice);

                        optionChain.add(c);
                    }
                }

            }

        } catch (Exception e) {

            System.out.println("Exception " + e.toString());
        }

        return optionChain;

    }

}