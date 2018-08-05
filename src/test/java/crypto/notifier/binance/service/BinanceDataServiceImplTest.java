package crypto.notifier.binance.service;

import crypto.notifier.binance.model.AllSymbolStats;
import crypto.notifier.binance.model.SymbolStats;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BinanceDataServiceImplTest {
    @Autowired
    BinanceDataServiceImpl client;

    @Autowired
    SymbolService symbolService;

    private static final int NUMBER_OF_SYMBOLS_TO_RETURN = 10;

    @Test
    public void getMostAgressivelyBoughtBTCSymbols_shouldReturnCorrectNumberOfResults() throws InterruptedException {
        //when
        AllSymbolStats allSymbolStats =
                client.getMostAgressivelyBoughtBTCSymbols(NUMBER_OF_SYMBOLS_TO_RETURN);

        //then
        assertEquals(allSymbolStats.getSymbolStatistics().size(), NUMBER_OF_SYMBOLS_TO_RETURN);
    }

    @Test
    public void getVolumeWeightedMostAgressivelyBoughtBTCSymbols() throws InterruptedException {
        //when
        List<SymbolStats> volumeWeightedMostAgressivelyBoughtBTCSymbols =
                client.getVolumeWeightedMostAgressivelyBoughtBTCSymbols(NUMBER_OF_SYMBOLS_TO_RETURN);

        //then
        assertEquals(volumeWeightedMostAgressivelyBoughtBTCSymbols.size(), NUMBER_OF_SYMBOLS_TO_RETURN);
    }

}