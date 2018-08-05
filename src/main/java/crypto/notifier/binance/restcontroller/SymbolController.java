package crypto.notifier.binance.restcontroller;

import crypto.notifier.binance.entity.Symbol;
import crypto.notifier.binance.service.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("notifier")
public class SymbolController{

    @Autowired
    SymbolService symbolService;

    @GetMapping("/symbols")
    public List<Symbol> getAllSymbols(){
        List<Symbol> symbols = symbolService.updateAllBTCSymbolsInDb();
        return symbols;
    }

}