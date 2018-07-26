package crypto.notifier.data.service;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.*;
import crypto.notifier.data.service.model.SymbolAggTradesStatistics;
import crypto.notifier.data.service.model.AllSymbolAggTradesStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public AllSymbolAggTradesStatistics getMostAgressivelyBoughtBTCSymbols(int numberOfSymbolsToReturn){
        List<String> symbols = getAllBTCSymbols();
        AllSymbolAggTradesStatistics allSymbolAggTradesStatistics = new AllSymbolAggTradesStatistics();

        symbols.stream()
                .forEach(symbol -> {
                    List<AggTrade> aggTrades = client.getAggTrades(symbol);
                    allSymbolAggTradesStatistics.getSymbolStatistics()
                            .add(new SymbolAggTradesStatistics(aggTrades, symbol));
                });

        allSymbolAggTradesStatistics.calculateTopSymbolsWithMostAgressiveBuys(10);

        return allSymbolAggTradesStatistics;
    }

    public List<SymbolAggTradesStatistics> getVolumeWeightedMostAgressivelyBoughtBTCSymbols(int numberOfSymabolsToReturn){
        List<String> symbols = getAllBTCSymbols();
        AllSymbolAggTradesStatistics allSymbolAggTradesStatistics = new AllSymbolAggTradesStatistics();

        List<SymbolAggTradesStatistics> symbolAggTradesStatistics = symbols.stream()
                .map(symbol -> {
                    List<AggTrade> aggTrades = client.getAggTrades(symbol);
                    return new SymbolAggTradesStatistics(aggTrades, symbol);
                })
                .collect(Collectors.toList());
        allSymbolAggTradesStatistics.setSymbolStatistics(symbolAggTradesStatistics);

        List<SymbolAggTradesStatistics> symbolAggTradesStatisticsTopAndSorted =
                allSymbolAggTradesStatistics.calculateVolumeWeightedTopSymbolsWithMostAgressiveBuys(10);

        return symbolAggTradesStatisticsTopAndSorted;
    }

    public List<String> getAllBTCSymbols(){
        return client.getAllPrices().stream()
                .map(asset -> asset.getSymbol())
                .filter(s -> s.endsWith("BTC"))
                .collect(Collectors.toList());
    }
}
