package crypto.notifier.binance.repository;

import crypto.notifier.binance.entity.Symbol;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SymbolRepositoryTest {

    @Autowired
    SymbolRepository symbolRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllTest(){
        //given
        Symbol symbol = new Symbol();
        symbol.setId(1);
        symbol.setSymbolName("symbolName");
        symbol.setCurrentAskPrice(BigDecimal.ONE);
        symbol.setCurrentBidPrice(BigDecimal.TEN);
        symbolRepository.save(symbol);
        symbolRepository.flush();

        List<Symbol> symbolsFound = symbolRepository.findAll();

        assertEquals(1, symbolsFound.size());
        assertEquals(symbolsFound.get(0).getCurrentBidPrice(), BigDecimal.TEN);
    }


}