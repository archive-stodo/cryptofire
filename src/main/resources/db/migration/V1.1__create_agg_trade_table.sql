create table AGG_TRADE (
    id SERIAL PRIMARY KEY,
    symbol_id INTEGER REFERENCES SYMBOL(id),
    agg_trade_id INTEGER not null,
   	quantity decimal(30,15),
   	trade_time timestamp,
   	is_buyer_maker boolean
);