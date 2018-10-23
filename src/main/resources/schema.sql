DROP TABLE IF EXISTS ADVERTISER;

CREATE TABLE ADVERTISER
(
   id bigint not null auto_increment,
   firstName varchar(255) not null,
   lastName varchar(255) not null,
   creditLimit double not null,
   primary key(id)
);
