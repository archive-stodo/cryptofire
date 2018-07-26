package crypto.notifier.data.service.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AllSymbolAggTradesStatistics {
    List<SymbolAggTradesStatistics> symbolStatistics = new ArrayList<>();

    public AllSymbolAggTradesStatistics() {
    }

    public List<SymbolAggTradesStatistics> getSymbolStatistics() {
        return symbolStatistics;
    }

    public void setSymbolStatistics(List<SymbolAggTradesStatistics> symbolStatistics) {
        this.symbolStatistics = symbolStatistics;
    }

    public List<SymbolAggTradesStatistics> calculateTopSymbolsWithMostAgressiveBuys(int numberOfTopSymbols) {
        return symbolStatistics = symbolStatistics.stream()
                .sorted(Comparator.comparingLong(SymbolAggTradesStatistics::getNumberOfAggressiveBuys).reversed())
                .limit(numberOfTopSymbols)
                .collect(Collectors.toList());
    }

    public List<SymbolAggTradesStatistics> calculateVolumeWeightedTopSymbolsWithMostAgressiveBuys(int numberOfTopSymbols) {
        //TO DO: calculate quantity*price of agressice trades/ quantity*price of all trades
        List<SymbolAggTradesStatistics> symbolAggTradesStatistics = Optional.ofNullable(symbolStatistics)
                .orElseGet(ArrayList::new)
                .stream()
//                .map(SymbolAggTradesStatistics::getAggressiveBuysVolumePercentage)
                .sorted((o1, o2) -> o2.getAggressiveBuysVolumePercentage().compareTo(o1.aggressiveBuysVolumePercentage))
                .limit(numberOfTopSymbols)
                .collect(Collectors.toList());

        return symbolAggTradesStatistics;
    }
}
