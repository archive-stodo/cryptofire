create table SYMBOL (
    id INTEGER PRIMARY KEY,
    symbol_name varchar(100) not null,
    current_ask_price money,
    current_bid_price money
);