package com.coincap.entity;

import java.util.Arrays;

public class PriceResponse {

    private Price[] data;

    public PriceResponse() {
    }

    public PriceResponse(Price[] data) {
        this.data = data;
    }

    public Price[] getData() {
        return data;
    }

    public void setData(Price[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PriceResponse{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
