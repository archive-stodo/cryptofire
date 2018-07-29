package crypto.notifier.binance.service;

import crypto.notifier.binance.entity.Symbol;
import crypto.notifier.binance.repository.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymbolService {

    @Autowired
    SymbolRepository symbolRepository;

    public List<Symbol> findAll(){
        List<Symbol> all = symbolRepository.findAll();
        return all;
    }

}
