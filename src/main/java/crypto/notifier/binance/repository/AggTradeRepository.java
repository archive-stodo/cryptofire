package crypto.notifier.binance.repository;

import crypto.notifier.binance.entity.AggTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggTradeRepository extends JpaRepository<AggTrade, Integer> {

}
