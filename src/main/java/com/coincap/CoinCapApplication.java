package com.coincap;

import com.coincap.entity.Asset;
import com.coincap.entity.AssetResponse;
import com.coincap.entity.Price;
import com.coincap.entity.WalletInfo;
import com.coincap.service.AssetService;
import com.coincap.service.PriceService;
import com.coincap.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


@SpringBootApplication
public class CoinCapApplication {

    @Autowired
    private PriceService priceService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private WalletService walletService;

    private static final Logger log = LoggerFactory.getLogger(CoinCapApplication.class);
    private static DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        SpringApplication.run(CoinCapApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            AssetResponse assets = assetService.getAssets();
            List<Asset> ass = Arrays.asList(assets.getData());

            List<String> sym = assetService.getSymbolsFromCsvFile();
            List<Asset> as = ass.stream()
                            .filter(el -> sym.toString().contains(el.getSymbol()))
                            .collect(Collectors.toList());
            log.info(as.toString());


            Map<String,Price[]> latestPrices = priceService.getAssetsPrice(as.size(),as);
            Map<String, String> _prices = assetService.getPricesFromCsvFile();
            List<WalletInfo> wallets = walletService.createWalletInfo(sym, latestPrices, _prices);

            Float result = wallets.stream().map(WalletInfo::getLatestPrice).reduce(0f,Float::sum);
            log.info(result.toString());

            wallets.sort(Collections.reverseOrder());
            log.info(wallets.toString());

            df.setRoundingMode(RoundingMode.UP);

            System.out.printf("total=%s,best_asset=%s,best_performance=%s,worst_asset=%s,worst_performance=%s",df.format(formatDouble(result)),wallets.get(0).getSymbol(),df.format(formatDouble(wallets.get(0).getPerformance())),wallets.get(wallets.size()-1).getSymbol(),df.format(formatDouble(wallets.get(wallets.size()-1).getPerformance())));

        };

    }

    private Double formatDouble(Float value) {
        return Double.parseDouble(value.toString());
    }


}
