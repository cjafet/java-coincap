package com.coincap.service;

import com.coincap.entity.Price;
import com.coincap.entity.WalletInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WalletService {

    public List<WalletInfo> createWalletInfo(List<String> sym, Map<String,Price[]> latestPrices, Map<String, String> _prices) {
        List<WalletInfo> wallets = new ArrayList<>();
        sym.forEach(el -> {
            Price[] p = latestPrices.get(el);
            Float price = Float.parseFloat(_prices.get(el));
            Float latestPrice = Float.parseFloat(p[0].getPriceUsd());
            Float performance = latestPrice/price;
            WalletInfo walletInfo = new WalletInfo(el,price,latestPrice,performance);
            wallets.add(walletInfo);
        });

        return wallets;
    }

}
