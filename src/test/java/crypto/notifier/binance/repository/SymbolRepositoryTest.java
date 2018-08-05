package crypto.notifier.binance.repository;

import crypto.notifier.binance.entity.Symbol;
import org.h2.tools.Server;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.SQLException;
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
        symbol.setSymbolName("symbolName");
        symbol.setCurrentAskPrice(BigDecimal.ONE);
        symbol.setCurrentBidPrice(BigDecimal.TEN);
        entityManager.persistAndFlush(symbol);

        //when
        List<Symbol> symbolsFound = symbolRepository.findAll();

        //then
        assertEquals(1, symbolsFound.size());
        assertEquals(symbolsFound.get(0).getCurrentBidPrice(), BigDecimal.TEN);
    }


}