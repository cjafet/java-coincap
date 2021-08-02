package com.coincap.service;

import com.coincap.entity.AssetResponse;
import com.coincap.repository.AssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AssetService {

    @Autowired
    private AssetsRepository assetsRepository;
    @Autowired
    RestTemplate restTemplate;

    public Map<String, String> getPricesFromCsvFile() {
        return assetsRepository.getPricesFromCsvFile();
    }

    public AssetResponse getAssets() {
        return assetsRepository.getAssets();
    }

    public List<String> getSymbolsFromCsvFile() {
        return assetsRepository.getSymbolsFromCsvFile();
    }
}
