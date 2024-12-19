create table consultas(
id bigint not null auto_increment,
medico_id bigint not null,
cliente_id bigint not null,
fecha datetime not null,

primary key (id),
constraint fk_consultas_medico_id foreign key (medico_id) references medicos(id),
constraint fk_consultas_cliente_id foreign key(cliente_id) references clientes(id)


);