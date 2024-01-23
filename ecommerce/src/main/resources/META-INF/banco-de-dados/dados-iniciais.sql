insert into produto (id, nome, preco, data_criacao, descricao) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (id, nome, preco, data_criacao, descricao) values (3, 'Camera GoPro', 1499.0, date_sub(sysdate(), interval 1 day), 'Uma excelente câmera para gravar seus sports!');
insert into produto (id, nome, preco, data_criacao, descricao) values (99, 'Camera GoPro HERO', 1499.0, date_sub(sysdate(), interval 1 day), 'Uma excelente câmera para gravar suas pescarias');

insert into cliente (id, nome, cpf) values (1, 'Lucas Galvao', '11132112322');
insert into cliente (id, nome, cpf) values (2, 'Freddie Mercury', '22232112321');
insert into cliente (id, nome, cpf) values (5, 'Brian May', '33332112320');

insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (1, 'MASCULINO', date_sub(sysdate(), interval 27 year));
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (2, 'MASCULINO', date_sub(sysdate(), interval 30 year));

insert into pedido (id, cliente_id, data_criacao, total, status) values (1, 1, sysdate(), 998.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (2, 1, sysdate(), 499.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499.0, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499.0, 1);

insert into pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) values (2, 'PROCESSANDO', 'cartao', '123', null);

insert into categoria (id, nome) values (1, 'Eletrônicos');
