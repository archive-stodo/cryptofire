create table SYMBOL (
    id SERIAL PRIMARY KEY,
    symbol_name varchar(100) not null,
    current_ask_price DECIMAL(30,15),
    current_bid_price DECIMAL(30,15)
);