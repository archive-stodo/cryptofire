package crypto.notifier.binance.service;

import com.binance.api.client.BinanceApiRestClient;
import crypto.notifier.binance.entity.Symbol;
import crypto.notifier.binance.repository.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SymbolService {

    @Autowired
    SymbolRepository symbolRepository;

    @Autowired
    BinanceApiRestClient client;

    public List<Symbol> findAll(){
        List<Symbol> all = symbolRepository.findAll();
        return all;
    }

    public List<String> getAllBTCSymbols(){
        return client.getAllPrices().stream()
                .map(asset -> asset.getSymbol())
                .filter(s -> s.endsWith("BTC"))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Symbol> updateAllBTCSymbolsInDb(){
        List<String> allBTCSymbols = getAllBTCSymbols();
        List<Symbol> symbols = allBTCSymbols.stream()
                .map(symbolName -> {
                    Symbol symbol = new Symbol();
                    symbol.setSymbolName(symbolName);
                    return symbol;
                })
                .collect(Collectors.toList());

        symbolRepository.deleteAll();
        symbolRepository.saveAll(symbols);
        symbolRepository.flush();

        return symbols;
    }


}
