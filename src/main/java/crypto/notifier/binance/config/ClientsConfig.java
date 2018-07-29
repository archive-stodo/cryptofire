package crypto.notifier.data.config;

import com.binance.api.client.BinanceApiAsyncRestClient;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientsConfig {

    @Bean
    public BinanceApiClientFactory getBinanceApiClientFactory(){
        return BinanceApiClientFactory.newInstance();
    }

    @Bean
    public BinanceApiRestClient getBinanceApiRestClient(){
        BinanceApiClientFactory factory = getBinanceApiClientFactory();
        return factory.newRestClient();
    }

    @Bean
    public BinanceApiAsyncRestClient getBinanceApiAsyncRestClient(){
        BinanceApiClientFactory factory = getBinanceApiClientFactory();
        return factory.newAsyncRestClient();
    }

}
