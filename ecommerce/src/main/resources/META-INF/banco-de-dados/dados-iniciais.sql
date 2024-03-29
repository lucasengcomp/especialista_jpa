insert into produto (id, versao, nome, preco, data_criacao, ativo, descricao) values (1, 0, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'SIM', 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (id, versao, nome, preco, data_criacao, ativo, descricao) values (3, 0, 'Camera GoPro', 1500.0, date_sub(sysdate(), interval 1 day), 'SIM', 'Uma excelente câmera para gravar seus sports!');
insert into produto (id, versao, nome, preco, data_criacao, ativo, descricao) values (99,0,  'Camera GoPro HERO', 1499.0, date_sub(sysdate(), interval 1 day), 'NAO', 'Uma excelente câmera para gravar suas pescarias');

insert into cliente (id, versao, nome, cpf) values (1, 0, 'Lucas Galvao', '11132112322');
insert into cliente (id, versao, nome, cpf) values (2, 0, 'Freddie Mercury', '22232112321');
insert into cliente (id, versao, nome, cpf) values (5, 0, 'Brian May', '33332112320');
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (1, 'MASCULINO', date_sub(sysdate(), interval 27 year));
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (2, 'MASCULINO', date_sub(sysdate(), interval 30 year));

insert into pedido (id, versao, cliente_id, data_criacao, total, status) values (1, 0 1, date_sub(sysdate(), interval 32 day), 2398.0, 'AGUARDANDO');
insert into pedido (id, versao, cliente_id, data_criacao, total, status) values (2, 0 1, date_sub(sysdate(), interval 5 day), 499.0, 'AGUARDANDO');
insert into pedido (id, versao, cliente_id, data_criacao, total, status) values (3, 0 1, date_sub(sysdate(), interval 4 day), 3500.0, 'PAGO');
insert into pedido (id, versao, cliente_id, data_criacao, total, status) values (4, 0 2, date_sub(sysdate(), interval 2 day), 499.0, 'PAGO');
insert into pedido (id, versao, cliente_id, data_criacao, total, status) values (5, 0 1, date_sub(sysdate(), interval 2 day), 799.0, 'PAGO');
insert into pedido (id, versao, cliente_id, data_criacao, total, status) values (6, 0 2, sysdate(), 799.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, versao, preco_produto, quantidade) values (1, 1, 0, 499, 2);
insert into item_pedido (pedido_id, produto_id, versao, preco_produto, quantidade) values (1, 3, 0, 1400, 1);
insert into item_pedido (pedido_id, produto_id, versao, preco_produto, quantidade) values (2, 1, 0, 499, 1);
insert into item_pedido (pedido_id, produto_id, versao, preco_produto, quantidade) values (3, 4, 0, 3500, 1);
insert into item_pedido (pedido_id, produto_id, versao, preco_produto, quantidade) values (4, 1, 0, 499, 1);
insert into item_pedido (pedido_id, produto_id, versao, preco_produto, quantidade) values (5, 1, 0, 799, 1);
insert into item_pedido (pedido_id, produto_id, versao, preco_produto, quantidade) values (6, 1, 0, 799, 1);

insert into pagamento (pedido_id, versao, status, tipo_pagamento, numero_cartao, codigo_barras) values (1, 0, 'RECEBIDO', 'cartao', '0123', null);
insert into pagamento (pedido_id, versao, status, tipo_pagamento, numero_cartao, codigo_barras) values (2, 0, 'PROCESSANDO', 'cartao', '4567', null);
insert into pagamento (pedido_id, versao, status, tipo_pagamento, numero_cartao, codigo_barras, data_vencimento) values (3, 0, 'RECEBIDO', 'boleto', null, '8910', date_sub(sysdate(), interval 2 day));
insert into pagamento (pedido_id, versao, status, tipo_pagamento, numero_cartao, codigo_barras) values (4, 0, 'PROCESSANDO', 'cartao', '1112', null);
insert into pagamento (pedido_id, versao, status, tipo_pagamento, numero_cartao, codigo_barras, data_vencimento) values (6, 0, 'PROCESSANDO', 'boleto', null, '456', date_add(sysdate(), interval 2 day));

insert into nota_fiscal (pedido_id, xml, data_emissao) values (2, '<xml />', sysdate());

insert into categoria (id, nome, versao) values (1, 'Eletrodomésticos', 0);
insert into categoria (id, nome, versao) values (2, 'Livros', 0);
insert into categoria (id, nome, versao) values (3, 'Esportes', 0);
insert into categoria (id, nome, versao) values (4, 'Futebol', 0);
insert into categoria (id, nome, versao) values (5, 'Natação', 0);
insert into categoria (id, nome, versao) values (6, 'Notebooks', 0);
insert into categoria (id, nome, versao) values (7, 'Smartphones', 0);
insert into categoria (id, nome, versao) values (8, 'Câmeras', 0);

insert into produto_categoria (produto_id, categoria_id, versao) values (1, 2, 0);
insert into produto_categoria (produto_id, categoria_id, versao) values (3, 8, 0);
insert into produto_categoria (produto_id, categoria_id, versao) values (4, 8, 0);

insert into produto_loja (id, versao, nome, preco, data_criacao, descricao) values (101, 0, 'Kindle', 799.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto_loja (id, versao, nome, preco, data_criacao, descricao) values (103, 0, 'Câmera GoPro Hero 7', 1500.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');
insert into produto_loja (id, versao, nome, preco, data_criacao, descricao) values (104, 0, 'Câmera Canon 80D', 3500.0, sysdate(), 'O melhor ajuste de foco.');
insert into produto_loja (id, versao, nome, preco, data_criacao, descricao) values (105, 0, 'Microfone de Lapela', 50.0, sysdate(), 'Produto massa');

insert into ecm_produto (versao, prd_id, prd_nome, prd_preco, prd_data_criacao, prd_descricao) values (0, 201, 'Kindle', 799.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into ecm_produto (versao, prd_id, prd_nome, prd_preco, prd_data_criacao, prd_descricao) values (0, 203, 'Câmera GoPro Hero 7', 1500.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');
insert into ecm_produto (versao, prd_id, prd_nome, prd_preco, prd_data_criacao, prd_descricao) values (0, 204, 'Câmera Canon 80D', 3500.0, sysdate(), 'O melhor ajuste de foco.');
insert into ecm_produto (versao, prd_id, prd_nome, prd_preco, prd_data_criacao, prd_descricao) values (0, 205, 'Microfone de Lapela', 50.0, sysdate(), 'Produto massa');

insert into erp_produto (versao, id, nome, preco, descricao) values (0 301, 'Kindle', 799.0, 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into erp_produto (versao, id, nome, preco, descricao) values (0 303, 'Câmera GoPro Hero 7', 1500.0, 'Desempenho 2x melhor.');
insert into erp_produto (versao, id, nome, preco, descricao) values (0 304, 'Câmera Canon 80D', 3500.0, 'O melhor ajuste de foco.');
insert into erp_produto (versao, id, nome, preco, descricao) values (0 305, 'Microfone de Lapela', 50.0, 'Produto massa');

insert into ecm_categoria (versao, cat_id, cat_nome) values (0, 201, 'Eletrodomésticos');
insert into ecm_categoria (versao, cat_id, cat_nome) values (0, 202, 'Livros');
insert into ecm_categoria (versao, cat_id, cat_nome) values (0, 203, 'Esportes');
insert into ecm_categoria (versao, cat_id, cat_nome) values (0, 204, 'Futebol');
insert into ecm_categoria (versao, cat_id, cat_nome) values (0, 205, 'Natação');
insert into ecm_categoria (versao, cat_id, cat_nome) values (0, 206, 'Notebooks');
insert into ecm_categoria (versao, cat_id, cat_nome) values (0, 207, 'Smartphones');
insert into ecm_categoria (versao, cat_id, cat_nome) values (0, 208, 'Câmeras');