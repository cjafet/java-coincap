package com.coincap.entity;

import java.util.Objects;

public class WalletInfo implements Comparable<WalletInfo> {
    private String symbol;
    private Float price;
    private Float latestPrice;
    private Float performance;

    public WalletInfo(String symbol, Float price, Float latestPrice, Float performance) {
        this.symbol = symbol;
        this.price = price;
        this.latestPrice = latestPrice;
        this.performance = performance;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getLatestPrice() {
        return latestPrice;
    }

    public void setLatestPrice(Float latestPrice) {
        this.latestPrice = latestPrice;
    }

    public Float getPerformance() {
        return performance;
    }

    public void setPerformance(Float performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return "WalletInfo{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", latestPrice=" + latestPrice +
                ", performance=" + performance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletInfo that = (WalletInfo) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(price, that.price) && Objects.equals(latestPrice, that.latestPrice) && Objects.equals(performance, that.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, price, latestPrice, performance);
    }

    @Override
    public int compareTo(WalletInfo o) {
        return this.getPerformance().compareTo(o.getPerformance());
    }
}
