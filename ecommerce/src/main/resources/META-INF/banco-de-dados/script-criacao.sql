DROP TABLE IF EXISTS testando;

ALTER TABLE
    categoria DROP FOREIGN KEY fk_categoria_categoriapai;

ALTER TABLE
    cliente_contato DROP FOREIGN KEY fk_cliente_contato_cliente;

ALTER TABLE
    cliente_detalhe DROP FOREIGN KEY fk_cliente_detalhe_cliente;

ALTER TABLE
    estoque DROP FOREIGN KEY fk_estoque_produto;

ALTER TABLE
    item_pedido DROP FOREIGN KEY fk_item_pedido_pedido;

ALTER TABLE
    item_pedido DROP FOREIGN KEY fk_item_pedido_produto;

ALTER TABLE
    nota_fiscal DROP FOREIGN KEY fk_nota_fiscal_pedido;

ALTER TABLE
    pagamento DROP FOREIGN KEY fk_pagamento_pedido;

ALTER TABLE
    pedido DROP FOREIGN KEY fk_pedido_cliente;

ALTER TABLE
    produto_atributo DROP FOREIGN KEY fk_produto_atributo_produto;

ALTER TABLE
    produto_categoria DROP FOREIGN KEY fk_produto_categoria_categoria;

ALTER TABLE
    produto_categoria DROP FOREIGN KEY fk_produto_categoria_produto;

ALTER TABLE
    produto_tag DROP FOREIGN KEY fk_produto_tag_produto;

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

CREATE TABLE testando (
    id INTEGER NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE categoria (
    id INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    categoria_pai_id integer,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE cliente (
    id INTEGER NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(14) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE cliente_contato (
    cliente_id INTEGER NOT NULL,
    descricao VARCHAR(255),
    tipo VARCHAR(255) NOT NULL,
    PRIMARY KEY (
        cliente_id,
        tipo
    )
) ENGINE = InnoDB;

CREATE TABLE cliente_detalhe (
    data_nascimento date,
    sexo VARCHAR(30) NOT NULL,
    cliente_id INTEGER NOT NULL,
    PRIMARY KEY (cliente_id)
) ENGINE = InnoDB;

CREATE TABLE estoque (
    id INTEGER NOT NULL AUTO_INCREMENT,
    quantidade integer,
    produto_id INTEGER NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE item_pedido (
    pedido_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    preco_produto DECIMAL(19, 2) NOT NULL,
    quantidade INTEGER NOT NULL,
    PRIMARY KEY (
        pedido_id,
        produto_id
    )
) ENGINE = InnoDB;

CREATE TABLE nota_fiscal (
    pedido_id INTEGER NOT NULL,
    data_emissao datetime(6) NOT NULL,
    XML longblob NOT NULL,
    PRIMARY KEY (pedido_id)
) ENGINE = InnoDB;

CREATE TABLE pagamento (
    tipo_pagamento VARCHAR(31) NOT NULL,
    pedido_id INTEGER NOT NULL,
    status VARCHAR(30) NOT NULL,
    numero_cartao VARCHAR(50),
    codigo_barras VARCHAR(100),
    PRIMARY KEY (pedido_id)
) ENGINE = InnoDB;

CREATE TABLE pedido (
    id INTEGER NOT NULL AUTO_INCREMENT,
    data_conclusao datetime(6),
    data_criacao datetime(6) NOT NULL,
    data_ultima_atualizacao datetime(6),
    bairro VARCHAR(50),
    cep VARCHAR(9),
    cidade VARCHAR(50),
    complemento VARCHAR(50),
    estado VARCHAR(2),
    logradouro VARCHAR(100),
    numero VARCHAR(10),
    status VARCHAR(30) NOT NULL,
    total DECIMAL(19, 2) NOT NULL,
    cliente_id INTEGER NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE produto (
    id INTEGER NOT NULL AUTO_INCREMENT,
    data_criacao datetime(6) NOT NULL,
    data_ultima_atualizacao datetime(6),
    descricao longtext,
    foto longblob,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(19, 2),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE produto_atributo (
    produto_id INTEGER NOT NULL,
    nome VARCHAR(100) NOT NULL,
    valor VARCHAR(255)
) ENGINE = InnoDB;

CREATE TABLE produto_categoria (
    produto_id INTEGER NOT NULL,
    categoria_id INTEGER NOT NULL
) ENGINE = InnoDB;

CREATE TABLE produto_tag (
    produto_id INTEGER NOT NULL,
    tag VARCHAR(50) NOT NULL
) ENGINE = InnoDB;

ALTER TABLE
    categoria
ADD
    CONSTRAINT unq_nome UNIQUE (nome) CREATE INDEX idx_nome ON cliente (nome);

ALTER TABLE
    cliente
ADD
    CONSTRAINT unq_cpf UNIQUE (cpf);

ALTER TABLE
    estoque
ADD
    CONSTRAINT UK_g72w2sa50w9a647f0eyhogus5 UNIQUE (produto_id) CREATE INDEX idx_nome ON produto (nome);

ALTER TABLE
    produto
ADD
    CONSTRAINT unq_nome UNIQUE (nome);

ALTER TABLE
    categoria
ADD
    CONSTRAINT fk_categoria_categoriapai FOREIGN KEY (categoria_pai_id) REFERENCES categoria (id);

ALTER TABLE
    cliente_contato
ADD
    CONSTRAINT fk_cliente_contato_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE
    cliente_detalhe
ADD
    CONSTRAINT fk_cliente_detalhe_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE
    estoque
ADD
    CONSTRAINT fk_estoque_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE
    item_pedido
ADD
    CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE
    item_pedido
ADD
    CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE
    nota_fiscal
ADD
    CONSTRAINT fk_nota_fiscal_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE
    pagamento
ADD
    CONSTRAINT fk_pagamento_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE
    pedido
ADD
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE
    produto_atributo
ADD
    CONSTRAINT fk_produto_atributo_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE
    produto_categoria
ADD
    CONSTRAINT fk_produto_categoria_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (id);

ALTER TABLE
    produto_categoria
ADD
    CONSTRAINT fk_produto_categoria_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE
    produto_tag
ADD
    CONSTRAINT fk_produto_tag_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

CREATE function acima_media_faturamento(valor DOUBLE) returns boolean reads SQL data return valor > (SELECT AVG(total) FROM pedido);

CREATE TABLE produto_loja (id INTEGER NOT NULL auto_increment, nome VARCHAR(100), descricao longtext, preco DECIMAL(19, 2), data_criacao datetime(6), data_ultima_atualizacao datetime(6), foto longblob, PRIMARY KEY (id)) engine=InnoDB;

CREATE TABLE ecm_produto (prd_id INTEGER NOT NULL auto_increment, prd_nome VARCHAR(100), prd_descricao longtext, prd_preco DECIMAL(19, 2), prd_data_criacao datetime(6), prd_data_ultima_atualizacao datetime(6), prd_foto longblob, PRIMARY KEY (prd_id)) engine=InnoDB;

CREATE TABLE erp_produto (id INTEGER NOT NULL auto_increment, nome VARCHAR(100), descricao longtext, preco DECIMAL(19, 2), PRIMARY KEY (id)) engine=InnoDB;

CREATE TABLE ecm_categoria (cat_id INTEGER NOT NULL auto_increment, cat_nome VARCHAR(100), cat_categoria_pai_id INTEGER, PRIMARY KEY (cat_id)) engine=InnoDB;

CREATE function acima_media_faturamento(valor DOUBLE) returns boolean reads SQL data return valor > (SELECT AVG(total) FROM pedido);

CREATE PROCEDURE buscar_nome_produto(IN produto_id INT, OUT produto_nome VARCHAR(255))
BEGIN SELECT nome INTO produto_nome FROM produto WHERE id = produto_id;
END
DELIMITER ;

CREATE PROCEDURE compraram_acima_media(IN ano INTEGER)
BEGIN
    SELECT cli.*
    FROM cliente cli
    JOIN pedido ped ON ped.cliente_id = cli.id
    WHERE ped.status = 'PAGO'
    AND YEAR(ped.data_criacao) = ano
    GROUP BY ped.cliente_id
    HAVING SUM(ped.total) >= (SELECT AVG(total_por_cliente.sum_total)
                              FROM (SELECT SUM(ped2.total) sum_total
                                    FROM pedido ped2
                                    WHERE ped.status = 'PAGO'
                                    AND YEAR(ped2.data_criacao) = ano
                                    GROUP BY ped2.cliente_id) AS total_por_cliente);
                                    END
DELIMITER ;

CREATE PROCEDURE ajustar_preco_produto(IN produto_id INT, IN percentual_ajuste DOUBLE, OUT preco_ajustado DOUBLE)
BEGIN
    DECLARE produto_preco DOUBLE;
    SELECT preco INTO produto_preco FROM produto WHERE id = produto_id;
    SET preco_ajustado = produto_preco + (produto_preco * percentual_ajuste);
    UPDATE produto SET preco = preco_ajustado WHERE id = produto_id;
END
DELIMITER ;
