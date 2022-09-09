--liquibase formatted sql
--changeset sergei:3fa68a7a-87a0-4010-a8f3-5320c5e03c54

CREATE TABLE product(
product_id BIGINT AUTO_INCREMENT, 
product_name VARCHAR(45) UNIQUE NOT NULL,
price SMALLINT NOT NULL,
PRIMARY KEY (product_id)
);

--rollback DROP TABLE product;