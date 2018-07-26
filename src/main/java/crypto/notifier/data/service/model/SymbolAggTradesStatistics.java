package crypto.notifier.data.service.model;

import com.binance.api.client.domain.market.AggTrade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SymbolAggTradesStatistics {
    List<AggTrade> aggTrades = new ArrayList<>();
    long numberOfAggressiveBuys = 0;
    BigDecimal aggressiveBuysVolume;
    BigDecimal totalVolume;
    BigDecimal aggressiveBuysVolumePercentage;

    String symbol;

    public SymbolAggTradesStatistics() {
    }

    public SymbolAggTradesStatistics(List<AggTrade> aggTrades, String symbol) {
        super();
        this.aggTrades = aggTrades;
        this.symbol = symbol;
        calculateNumberOfAggressiveBuys();

        calculateAggresiveBuysVolume();
        calculateTotalVolume();
        calculateAggresiveBuysVolumePercentage();
    }

    private void calculateNumberOfAggressiveBuys() {
        numberOfAggressiveBuys = aggTrades.stream()
            .filter(aggTrade -> !aggTrade.isBuyerMaker())
            .count();
    }

    private void calculateAggresiveBuysVolume() {
        aggressiveBuysVolume = aggTrades.stream()
                .filter(aggTrade -> !aggTrade.isBuyerMaker())
                .map(aggTrade -> {
                    BigDecimal price = new BigDecimal(aggTrade.getPrice());
                    BigDecimal quantity = new BigDecimal(aggTrade.getQuantity());
                    return price.multiply(quantity);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void calculateTotalVolume() {
        totalVolume = aggTrades.stream()
                .map(aggTrade -> {
                    BigDecimal price = new BigDecimal(aggTrade.getPrice());
                    BigDecimal quantity = new BigDecimal(aggTrade.getQuantity());
                    return price.multiply(quantity);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void calculateAggresiveBuysVolumePercentage(){
        aggressiveBuysVolumePercentage = aggressiveBuysVolume.divide(totalVolume, 6).multiply(BigDecimal.valueOf(100));
    }

    public List<AggTrade> getAggTrades() {
        return aggTrades;
    }

    public void setAggTrades(List<AggTrade> aggTrades) {
        this.aggTrades = aggTrades;
    }

    public long getNumberOfAggressiveBuys() {
        return numberOfAggressiveBuys;
    }

    public void setNumberOfAggressiveBuys(long numberOfAggressiveBuys) {
        this.numberOfAggressiveBuys = numberOfAggressiveBuys;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getAggressiveBuysVolume() {
        return aggressiveBuysVolume;
    }

    public void setAggressiveBuysVolume(BigDecimal aggressiveBuysVolume) {
        this.aggressiveBuysVolume = aggressiveBuysVolume;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public BigDecimal getAggressiveBuysVolumePercentage() {
        return aggressiveBuysVolumePercentage;
    }

    public void setAggressiveBuysVolumePercentage(BigDecimal aggressiveBuysVolumePercentage) {
        this.aggressiveBuysVolumePercentage = aggressiveBuysVolumePercentage;
    }
}
