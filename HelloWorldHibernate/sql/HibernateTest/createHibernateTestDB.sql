CREATE SCHEMA IF NOT EXISTS HibernateTestDB;
USE HibernateTestDB;

CREATE TABLE Message (
  id INT(11) PRIMARY KEY,
  text VARCHAR(255));

CREATE TABLE Item (
  id INT(11) PRIMARY KEY,
  name VARCHAR(255),
  auctionEnd date);
  
