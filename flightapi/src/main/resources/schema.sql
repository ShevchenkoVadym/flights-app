create table if not exists Arrival (
  id SERIAL PRIMARY KEY,
  createdAt timestamp not null,
  flightName varchar(50) not null,
  airCompanyName varchar(50) not null,
  airplaneName varchar(50) not null,
  terminal varchar(50) not null,
  arrivalFrom varchar(50) not null,
  showTime timestamp not null

);

create table if not exists Departure (
  id SERIAL PRIMARY KEY,
  createdAt timestamp not null,
  flightName varchar(50) not null,
  airCompanyName varchar(50) not null,
  airplaneName varchar(50) not null,
  terminal varchar(50) not null,
  arrivalFrom varchar(50) not null,
  showTime timestamp not null

);