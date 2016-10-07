DROP TABLE stock if exists;

create table stock  
(
  tickerSymbol VARCHAR(10),  
  name VARCHAR(50),
   CONSTRAINT pk_stock PRIMARY KEY (tickerSymbol)
);
