package crypto.notifier.binance.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SymbolController {

    @GetMapping("/greeting")
    public String greeting(){
        return "Hello";
    }

}
