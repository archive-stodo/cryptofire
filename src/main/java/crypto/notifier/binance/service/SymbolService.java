package crypto.notifier.binance.service;

import crypto.notifier.binance.entity.Symbol;
import crypto.notifier.binance.repository.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SymbolService {

    @Autowired
    SymbolRepository symbolRepository;

    public void findAll(){
        Iterable<Symbol> all = symbolRepository.findAll();
    }

}
