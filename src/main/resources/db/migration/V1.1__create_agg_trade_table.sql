create table AGG_TRADE (
    id int not null,
    agg_trade_id int not null,
   	quantity decimal(20,12),
   	trade_time timestamp,
   	is_buyer_maker boolean
);