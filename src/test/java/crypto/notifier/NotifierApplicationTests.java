package crypto.notifier;

import crypto.notifier.data.service.model.AllSymbolAggTradesStatistics;
import crypto.notifier.data.service.BinanceDataServiceImpl;
import crypto.notifier.data.service.model.SymbolAggTradesStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotifierApplicationTests {
	private static final String TICKER_NAME = "KEYBTC";
	private static final int NUMBER_OF_SYMBOLS_TO_RETURN = 10;
	@Autowired
	BinanceDataServiceImpl client;

	@Test
	public void getMostAgressivelyBoughtBTCSymbols_shouldReturnCorrectNumberOfResults() throws InterruptedException {
		//given

		//when
		AllSymbolAggTradesStatistics allSymbolAggTradesStatistics =
				client.getMostAgressivelyBoughtBTCSymbols(NUMBER_OF_SYMBOLS_TO_RETURN);

		//then
		assertEquals(allSymbolAggTradesStatistics.getSymbolStatistics().size(), NUMBER_OF_SYMBOLS_TO_RETURN);
	}

	@Test
	public void getVolumeWeightedMostAgressivelyBoughtBTCSymbols() throws InterruptedException {
		//given

		//when
		List<SymbolAggTradesStatistics> volumeWeightedMostAgressivelyBoughtBTCSymbols =
				client.getVolumeWeightedMostAgressivelyBoughtBTCSymbols(NUMBER_OF_SYMBOLS_TO_RETURN);

		//then
		assertEquals(volumeWeightedMostAgressivelyBoughtBTCSymbols.size(), NUMBER_OF_SYMBOLS_TO_RETURN);
	}

}
