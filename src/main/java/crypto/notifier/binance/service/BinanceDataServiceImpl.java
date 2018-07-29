package crypto.notifier.data.service;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.*;
import crypto.notifier.data.service.model.SymbolStats;
import crypto.notifier.data.service.model.AllSymbolStats;
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

    //return top 10
//    public LinkedHashMap<String, Long> getMostAgressivelyBoughtBTCSymbols(int numberOfSymbolsToReturn){
//        List<String> tickerNames = getAllBTCSymbols();
//        Map<String, Long> symbolsWithNumberOfAgressiveBuys = new TreeMap<>();
//        LinkedHashMap<String, Long> mostAggresivelyBoughtSymbols;
//
//        tickerNames.stream()
//                .forEach(tickerName -> {
//                    List<AggTrade> aggTrades = client.getAggTrades(tickerName);
//                    long numberOfAggresiveBuys = getNumberOfAggressiveBuys(aggTrades);
//                    symbolsWithNumberOfAgressiveBuys.put(tickerName, numberOfAggresiveBuys);
//                });
//
//        mostAggresivelyBoughtSymbols = symbolsWithNumberOfAgressiveBuys.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .limit(numberOfSymbolsToReturn)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (e1, e2) -> e2, LinkedHashMap::new));
//
//        return mostAggresivelyBoughtSymbols;
//    }

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
