CREATE TABLE tb_wallet (
  wallet_id UUID NOT NULL,
  balance DECIMAL(18,2) NOT NULL,
  CONSTRAINT pk_tb_wallet PRIMARY KEY (wallet_id)
);