package crypto.notifier.binance.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import crypto.notifier.config.BigDecimalSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Symbol {

    @Id
    @SequenceGenerator(name = "symbol_id_seq",
                       sequenceName = "symbol_id_seq",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "symbol_id_seq")
    @Column(name = "id", updatable = false)
    private int id;

    private String symbolName;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal currentAskPrice;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal currentBidPrice;

    @OneToMany(mappedBy = "symbol",
            cascade = { CascadeType.PERSIST,
                        CascadeType.DETACH,
                        CascadeType.MERGE,
                        CascadeType.REFRESH})
    private List<AggTrade> aggTrades;

    public Symbol() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public BigDecimal getCurrentAskPrice() {
        return currentAskPrice;
    }

    public void setCurrentAskPrice(BigDecimal currentAskPrice) {
        this.currentAskPrice = currentAskPrice;
    }

    public BigDecimal getCurrentBidPrice() {
        return currentBidPrice;
    }

    public void setCurrentBidPrice(BigDecimal currentBidPrice) {
        this.currentBidPrice = currentBidPrice;
    }
}
