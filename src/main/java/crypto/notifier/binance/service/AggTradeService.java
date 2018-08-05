package crypto.notifier.binance.service;

import crypto.notifier.binance.entity.AggTrade;
import crypto.notifier.binance.repository.AggTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AggTradeService {

    @Autowired
    AggTradeRepository aggTradeRepository;

    public List<crypto.notifier.binance.entity.AggTrade> findAll(){
        List<AggTrade> all = aggTradeRepository.findAll();
        return all;
    }

}
