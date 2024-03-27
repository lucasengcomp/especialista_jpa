DROP TABLE IF EXISTS testando;

ALTER TABLE IF EXISTS categoria DROP FOREIGN KEY fk_categoria_categoriapai;
ALTER TABLE IF EXISTS cliente_contato DROP FOREIGN KEY fk_cliente_contato_cliente;
ALTER TABLE IF EXISTS cliente_detalhe DROP FOREIGN KEY fk_cliente_detalhe_cliente;
ALTER TABLE IF EXISTS estoque DROP FOREIGN KEY fk_estoque_produto;
ALTER TABLE IF EXISTS item_pedido DROP FOREIGN KEY fk_item_pedido_pedido;
ALTER TABLE IF EXISTS item_pedido DROP FOREIGN KEY fk_item_pedido_produto;
ALTER TABLE IF EXISTS nota_fiscal DROP FOREIGN KEY fk_nota_fiscal_pedido;
ALTER TABLE IF EXISTS pagamento DROP FOREIGN KEY fk_pagamento_pedido;
ALTER TABLE IF EXISTS pedido DROP FOREIGN KEY fk_pedido_cliente;
ALTER TABLE IF EXISTS produto_atributo DROP FOREIGN KEY fk_produto_atributo_produto;
ALTER TABLE IF EXISTS produto_categoria DROP FOREIGN KEY fk_produto_categoria_categoria;
ALTER TABLE IF EXISTS produto_categoria DROP FOREIGN KEY fk_produto_categoria_produto;
ALTER TABLE IF EXISTS produto_tag DROP FOREIGN KEY fk_produto_tag_produto;
DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS cliente_contato;
DROP TABLE IF EXISTS cliente_detalhe;
DROP TABLE IF EXISTS estoque;
DROP TABLE IF EXISTS item_pedido;
DROP TABLE IF EXISTS nota_fiscal;
DROP TABLE IF EXISTS pagamento;
DROP TABLE IF EXISTS pedido;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS produto_atributo;
DROP TABLE IF EXISTS produto_categoria;
DROP TABLE IF EXISTS produto_tag;
DROP TABLE IF EXISTS testando;
DROP TABLE IF EXISTS produto_loja;
DROP TABLE IF EXISTS ecm_produto;
DROP TABLE IF EXISTS erp_produto;

DROP FUNCTION IF EXISTS acima_media_faturamento;

DROP function IF EXISTS acima_media_faturamento;