create table AGG_TRADE (
    id INTEGER PRIMARY KEY,
    symbol_id INTEGER REFERENCES SYMBOL(id),
    agg_trade_id INTEGER not null,
   	quantity decimal(20,20),
   	trade_time timestamp,
   	is_buyer_maker boolean
);