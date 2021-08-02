package com.coincap.repository;

import com.coincap.entity.Asset;
import com.coincap.entity.AssetResponse;
import com.coincap.entity.Price;
import com.coincap.entity.PriceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AssetsRepository {

    @Autowired
    RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(AssetsRepository.class);
    private List<String> assets = new ArrayList<>();
    private Map<String, String> mapToPrice = new HashMap<>();

    public List<String> getSymbolsFromCsvFile() {
        ClassLoader classLoader = getClass().getClassLoader();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/wallet.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
//                log.info(line);
                String[] values = line.split(",");
//                log.info(values.toString());
                assets.add(values[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assets.remove(0);
        return assets;
    }

    public Map<String, String> getPricesFromCsvFile() {
        ClassLoader classLoader = getClass().getClassLoader();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/wallet.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                mapToPrice.put(values[0], values[2]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mapToPrice.remove("symbol");
        return mapToPrice;
    }

    public AssetResponse getAssets() {
        AssetResponse assets = restTemplate.getForObject("https://api.coincap.io/v2/assets", AssetResponse.class);
//        log.info(assets.toString());
        return assets;
    }

    public Price[] getPrice(String id) {
        PriceResponse price = restTemplate.getForObject("https://api.coincap.io/v2/assets/"+ id +
                "/history?interval=d1&start=1617753600000&end=1617753601000", PriceResponse.class);
//        log.info(String.valueOf(price));
        return price.getData();
    }

}
