package crypto.notifier.binance.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Symbol extends BaseEntity {
    private String symbolName;
    private BigDecimal currentAskPrice;
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
