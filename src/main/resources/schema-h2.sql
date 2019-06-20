CREATE TABLE VOTACAO(ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                  nomeRestaurante VARCHAR(50) NOT NULL,
                  dataVotacao DATE NOT NULL DEFAULT SYSDATE,
                  nomeProfissional VARCHAR(50) NOT NULL,
                  voto INT,
                  descricao VARCHAR(250)
                  );