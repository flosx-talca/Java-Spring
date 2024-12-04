create table clientes(
id bigint not null auto_increment,
nombre varchar(100) not null,
email varchar(100) not null unique,
calle varchar(100) not null,
distrito varchar(100) not null,
complemento varchar(100) not null,
numero varchar(20),
ciudad varchar(100) not null,
activo tinyint,

primary key (id)
);