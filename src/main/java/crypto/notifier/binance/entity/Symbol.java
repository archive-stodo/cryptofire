package crypto.notifier.binance.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import crypto.notifier.config.BigDecimalSerializer;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Symbol extends BaseEntity {
    private String symbolName;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal currentAskPrice;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal currentBidPrice;

    public Symbol() {
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }
}
