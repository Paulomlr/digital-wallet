CREATE TABLE tb_transfer (
  transfer_id UUID NOT NULL,
   transfer_date TIMESTAMP WITHOUT TIME ZONE,
   amount DECIMAL,
   origin_wallet UUID,
   destination_wallet UUID,
   CONSTRAINT pk_tb_transfer PRIMARY KEY (transfer_id)
);

ALTER TABLE tb_transfer ADD CONSTRAINT FK_TB_TRANSFER_ON_DESTINATION_WALLET FOREIGN KEY (destination_wallet) REFERENCES tb_wallet (wallet_id);

ALTER TABLE tb_transfer ADD CONSTRAINT FK_TB_TRANSFER_ON_ORIGIN_WALLET FOREIGN KEY (origin_wallet) REFERENCES tb_wallet (wallet_id);