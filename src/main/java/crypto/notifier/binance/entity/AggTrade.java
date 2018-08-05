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
            generator = "aggTrade_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "symbol_id")
    private Symbol symbol;

    private int aggTradeId;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal quantity;

    private LocalDateTime tradeTime;
    private boolean isBuyerMaker;

    public AggTrade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
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
