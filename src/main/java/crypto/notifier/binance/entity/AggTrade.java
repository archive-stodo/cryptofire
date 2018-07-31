package crypto.notifier.binance.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import crypto.notifier.config.BigDecimalSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class AggTrade{
    @Id
    @SequenceGenerator(name = "agg_trade_id_seq",
            sequenceName = "agg_trade_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "agg_trade_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    private int symbolId;
    private int aggTradeId;

    @JsonSerialize(using = BigDecimalSerializer.class)
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
