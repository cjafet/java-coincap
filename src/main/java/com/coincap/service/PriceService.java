package com.coincap.service;

import com.coincap.entity.Asset;
import com.coincap.entity.Price;
import com.coincap.repository.AssetsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PriceService {

    @Autowired
    private AssetsRepository assetsRepository;
    private static final Logger log = LoggerFactory.getLogger(PriceService.class);
    private Map<String,Price[]> latestPrices = new HashMap<>();

    public Map<String, Price[]> getAssetsPrice(int size, List<Asset> as) throws InterruptedException {

        int numberOfThreads = size;
        if(size==1) {
            numberOfThreads=1;
        } else{
            numberOfThreads=3;
        }
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            int counter = i;
            service.execute(() -> {
                log.info(LocalDateTime.now().toString());
                String id = as.get(counter).getId();
                String _sym = as.get(counter).getSymbol();
                Price[] p = assetsRepository.getPrice(id);
                latestPrices.put(_sym,p);
                latch.countDown();
            });
        }
        latch.await();

        return latestPrices;

    }

}
