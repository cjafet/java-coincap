package com.coincap.entity;

import java.util.Arrays;

public class AssetResponse {

    private Asset[] data;

    public AssetResponse() {
    }

    public AssetResponse(Asset[] data) {
        this.data = data;
    }

    public Asset[] getData() {
        return data;
    }

    public void setData(Asset[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AssetResponse{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
