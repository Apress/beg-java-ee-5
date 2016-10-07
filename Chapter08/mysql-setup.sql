# SQL Commands to setup table for MySQL
# Modify these command as needed for different database
create table stock (
  tickerSymbol varchar(10),
  analyst varchar(30),
  rating varchar(30)
) TYPE=INNODB;

create table target (
  tickersymbol varchar(10),
  pricetarget decimal (10,2)
) TYPE=INNODB;

# SQL Commands to setup tables for Example 6 and 7
# Modify these command as needed for different database
create table reserve (
  roomid varchar(5),
  res_date date,
  res_flag tinyint(1),
  res_name varchar(30)
) TYPE=INNODB;

insert into reserve (roomid, res_date, res_flag)
  values ('PIKE', '2005-04-15', 0);
