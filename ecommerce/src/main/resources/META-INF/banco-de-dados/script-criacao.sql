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
    id integer NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE categoria (
    id integer NOT NULL AUTO_INCREMENT,
    nome varchar(100) NOT NULL,
    categoria_pai_id integer,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE cliente (
    id integer NOT NULL AUTO_INCREMENT,
    cpf varchar(14) NOT NULL,
    nome varchar(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE cliente_contato (
    cliente_id integer NOT NULL,
    descricao varchar(255),
    tipo varchar(255) NOT NULL,
    PRIMARY KEY (
        cliente_id,
        tipo
    )
) ENGINE = InnoDB;

CREATE TABLE cliente_detalhe (
    data_nascimento date,
    sexo varchar(30) NOT NULL,
    cliente_id integer NOT NULL,
    PRIMARY KEY (cliente_id)
) ENGINE = InnoDB;

CREATE TABLE estoque (
    id integer NOT NULL AUTO_INCREMENT,
    quantidade integer,
    produto_id integer NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE item_pedido (
    pedido_id integer NOT NULL,
    produto_id integer NOT NULL,
    preco_produto decimal(19, 2) NOT NULL,
    quantidade integer NOT NULL,
    PRIMARY KEY (
        pedido_id,
        produto_id
    )
) ENGINE = InnoDB;

CREATE TABLE nota_fiscal (
    pedido_id integer NOT NULL,
    data_emissao datetime(6) NOT NULL,
    XML longblob NOT NULL,
    PRIMARY KEY (pedido_id)
) ENGINE = InnoDB;

CREATE TABLE pagamento (
    tipo_pagamento varchar(31) NOT NULL,
    pedido_id integer NOT NULL,
    status varchar(30) NOT NULL,
    numero_cartao varchar(50),
    codigo_barras varchar(100),
    PRIMARY KEY (pedido_id)
) ENGINE = InnoDB;

CREATE TABLE pedido (
    id integer NOT NULL AUTO_INCREMENT,
    data_conclusao datetime(6),
    data_criacao datetime(6) NOT NULL,
    data_ultima_atualizacao datetime(6),
    bairro varchar(50),
    cep varchar(9),
    cidade varchar(50),
    complemento varchar(50),
    estado varchar(2),
    logradouro varchar(100),
    numero varchar(10),
    status varchar(30) NOT NULL,
    total decimal(19, 2) NOT NULL,
    cliente_id integer NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE produto (
    id integer NOT NULL AUTO_INCREMENT,
    data_criacao datetime(6) NOT NULL,
    data_ultima_atualizacao datetime(6),
    descricao longtext,
    foto longblob,
    nome varchar(100) NOT NULL,
    preco decimal(19, 2),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE produto_atributo (
    produto_id integer NOT NULL,
    nome varchar(100) NOT NULL,
    valor varchar(255)
) ENGINE = InnoDB;

CREATE TABLE produto_categoria (
    produto_id integer NOT NULL,
    categoria_id integer NOT NULL
) ENGINE = InnoDB;

CREATE TABLE produto_tag (
    produto_id integer NOT NULL,
    tag varchar(50) NOT NULL
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