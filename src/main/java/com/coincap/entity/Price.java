package com.coincap.entity;

import java.util.Objects;

public class Price {
    private String priceUsd;
    private String time;
    private String date;

    public Price() {
    }

    public Price(String priceUsd, String time, String date) {
        this.priceUsd = priceUsd;
        this.time = time;
        this.date = date;
    }

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Price{" +
                "priceUsd='" + priceUsd + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(priceUsd, price.priceUsd) && Objects.equals(time, price.time) && Objects.equals(date, price.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(priceUsd, time, date);
    }
}
