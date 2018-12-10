package OptionChainAnalyzer;

class OptionChain {
    private float openInterestCall;
    private float strikePrice;
    private float openInterestPut;

    public void setPutOI(String oi) {
        this.openInterestPut = Float.valueOf(oi.replace(",", ""));
    }

    public void setCallOI(String oi) {
        this.openInterestCall = Float.valueOf(oi.replace(",", ""));
    }

    public void setStrikePrice(String price) {
        this.strikePrice = Float.valueOf(price.replace(",", ""));
    }

    public float getPutOI() {
        return this.openInterestPut; 
    }

    public float getCallOI() {
        return this.openInterestCall; 
    }

    public float getStrikePrice() {
        return this.strikePrice; 
    }

    public String toString() {
        return "\n\t Strike Price : " + this.strikePrice + "\n\t CALL OI :" + this.openInterestCall + "\n\t PUT OI :"
                + this.openInterestPut;
    }
}