create table usuario(
 id serial, 
 nome varchar(80),
 login varchar(20),
 senha varchar(20),
 primary key(id)
);

create table sala(
 identificacao varchar(20),
 capacidade int,
 apelido varchar(100),
 tipo int,
 primary key(identificacao)
);

create table evento(
 id serial,
 nome varchar,
 descricao varchar,
 dtInicio Date,
 dtFim Date,
 nome_responsavel varchar(80),
 numero_repeticoes int,
 status int,
 id_sala varchar(20), 
 qtde_participantes int,
 primary key(id),
 foreign key(id_sala) references sala(identificacao)
)