package com.steamedegg.model;



/**
 * @author Juntao Peng
 */
public class Price {
    private long date;
    private String price;

    public Price() {
        super();
    }

    public Price(long date, String price) {
        this.date = date;
        this.price = price;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
