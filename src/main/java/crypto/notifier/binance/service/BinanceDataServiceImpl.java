package crypto.notifier.binance.service;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.*;
import crypto.notifier.binance.model.AllSymbolStats;
import crypto.notifier.binance.model.SymbolStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BinanceDataServiceImpl implements  BinanceDataService{

    @Autowired
    BinanceApiRestClient client;

    @Autowired
    BinanceApiAsyncRestClient asyncClient;

    public AllSymbolStats getMostAgressivelyBoughtBTCSymbols(int numberOfSymbolsToReturn){
        List<String> symbols = getAllBTCSymbols();
        AllSymbolStats allSymbolStats = new AllSymbolStats();

        symbols.stream()
                .forEach(symbol -> {
                    List<AggTrade> aggTrades = client.getAggTrades(symbol);
                    allSymbolStats.getSymbolStatistics()
                            .add(new SymbolStats(aggTrades, symbol));
                });

        allSymbolStats.calculateTopSymbolsWithMostAgressiveBuys(10);

        return allSymbolStats;
    }

    public List<SymbolStats> getVolumeWeightedMostAgressivelyBoughtBTCSymbols(int numberOfSymabolsToReturn){
        List<String> symbols = getAllBTCSymbols();
        AllSymbolStats allSymbolStats = new AllSymbolStats();

        List<SymbolStats> symbolAggTradesStatistics = symbols.stream()
                .map(symbol -> {
                    List<AggTrade> aggTrades = client.getAggTrades(symbol);
                    return new SymbolStats(aggTrades, symbol);
                })
                .collect(Collectors.toList());
        allSymbolStats.setSymbolStatistics(symbolAggTradesStatistics);

        List<SymbolStats> symbolStatsTopAndSorted =
                allSymbolStats.calculateVolumeWeightedTopSymbolsWithMostAgressiveBuys(10);

        return symbolStatsTopAndSorted;
    }

    public List<String> getAllBTCSymbols(){
        return client.getAllPrices().stream()
                .map(asset -> asset.getSymbol())
                .filter(s -> s.endsWith("BTC"))
                .collect(Collectors.toList());
    }
}
