CREATE TABLE tb_user (
  user_id UUID NOT NULL,
   name VARCHAR(255),
   email VARCHAR(255),
   password VARCHAR(255),
   wallet_id UUID,
   CONSTRAINT pk_tb_user PRIMARY KEY (user_id)
);

ALTER TABLE tb_user ADD CONSTRAINT uc_tb_user_wallet UNIQUE (wallet_id);

ALTER TABLE tb_user ADD CONSTRAINT FK_TB_USER_ON_WALLET FOREIGN KEY (wallet_id) REFERENCES tb_wallet (wallet_id);