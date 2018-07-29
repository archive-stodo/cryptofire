package crypto.notifier.binance.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AllSymbolStats {
    List<SymbolStats> symbolStatistics = new ArrayList<>();

    public AllSymbolStats() {
    }

    public List<SymbolStats> getSymbolStatistics() {
        return symbolStatistics;
    }

    public void setSymbolStatistics(List<SymbolStats> symbolStatistics) {
        this.symbolStatistics = symbolStatistics;
    }

    public List<SymbolStats> calculateTopSymbolsWithMostAgressiveBuys(int numberOfTopSymbols) {
        return symbolStatistics = symbolStatistics.stream()
                .sorted(Comparator.comparingLong(SymbolStats::getNumberOfAggressiveBuys).reversed())
                .limit(numberOfTopSymbols)
                .collect(Collectors.toList());
    }

    public List<SymbolStats> calculateVolumeWeightedTopSymbolsWithMostAgressiveBuys(int numberOfTopSymbols) {
        //TO DO: calculate quantity*price of agressice trades/ quantity*price of all trades
        List<SymbolStats> symbolAggTradesStatistics = Optional.ofNullable(symbolStatistics)
                .orElseGet(ArrayList::new)
                .stream()
                .sorted((o1, o2) -> o2.getAggressiveBuysVolumePercentage().compareTo(o1.aggressiveBuysVolumePercentage))
                .limit(numberOfTopSymbols)
                .collect(Collectors.toList());

        return symbolAggTradesStatistics;
    }
}
