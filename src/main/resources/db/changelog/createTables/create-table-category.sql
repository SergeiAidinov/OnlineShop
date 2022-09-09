--liquibase formatted sql
--changeset sergei:3fa68a7a-87a0-4010-a8f3-5320c5e03c54

CREATE TABLE IF NOT EXISTS category(
category_id BIGINT AUTO_INCREMENT, 
category_name VARCHAR(45) UNIQUE NOT NULL,
PRIMARY KEY (category_id)
);

--rollback DROP TABLE category;