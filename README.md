# cryptofire

Aims of the project:
  Use cryptocurrency exchanges APIs to calculate/detect whatever can be usefull for traders:

  -> hedging opportunities between exchanges
  
  -> detecting candlestick patterns like hammer/pinbar
  
  -> detecting abnormal volumes
  
  -> calculating various indicators
  
 
 A user should also be able to define certain conditions on occurrence of which he should notified by mail.
 
 (ex1. current daily volume more than x times greater than average volume from the last y days)
 
 (ex2. hammer on a daily chart)
 
 
 Finally, combining these rules to create more sophistcated ones will give even greater flexibility
 
 -> Notify me when ex1 = true amd ex2 = true
 
 ----------------------------------------------------------------------------------------------------------
 What is done:
 
 Fetching binance BTC trading pairs.
 
 Binance API provides the last 500 trades on each instrument (quntity, price, isBuyerMaker)
 
 
 isBuyerMaker is a boolean -> 
 
 if it is true, it means that a seller decided to sell immediately - AGRESSIVE SELL
 
 if it is false, it means that a buyer decided to buy immediately - AGRESSIVE BUY
 
 
 Agressive Buys Value Traded (ABVT) = quantity*price    ->   is summed for aggresive buys.
 
 All Trades Value Traded (ATVT) = quantity*price        ->   is summed for all 500 trades.


Percentage of Aggresive Buys Value Traded (ABVT%) = ( ABVT/ATVT ) * 100

The higher the ABVT% the more agressively an instrument is bought.


BinanceDataServiceImpl.getVolumeWeightedMostAgressivelyBoughtBTCSymbols(int numberOfSymabolsToReturn)

   returns most agressively bought BTC symbols
