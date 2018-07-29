package crypto.notifier.binance.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class AggTrade extends BaseEntity{
    private int symbolId;
    private int aggTradeId;
    private BigDecimal quantity;
    private LocalDateTime tradeTime;
    private boolean isBuyerMaker;

    public AggTrade() {
    }

    public AggTrade(int symbolId) {
        this.symbolId = symbolId;
    }

    public int getSymbolId() {
        return symbolId;
    }

    public void setSymbolId(int symbolId) {
        this.symbolId = symbolId;
    }

    public int getAggTradeId() {
        return aggTradeId;
    }

    public void setAggTradeId(int aggTradeId) {
        this.aggTradeId = aggTradeId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDateTime tradeTime) {
        this.tradeTime = tradeTime;
    }

    public boolean isBuyerMaker() {
        return isBuyerMaker;
    }

    public void setBuyerMaker(boolean buyerMaker) {
        isBuyerMaker = buyerMaker;
    }
}
