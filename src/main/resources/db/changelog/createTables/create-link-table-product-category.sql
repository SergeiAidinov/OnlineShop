--liquibase formatted sql
--changeset sergei:a45f96e9-6856-4859-9c4b-6eeaedba682f

CREATE TABLE IF NOT EXISTS link_product_category(
id BIGINT AUTO_INCREMENT, 
product_id BIGINT,
category_id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (category_id) REFERENCES category (category_id) ON UPDATE RESTRICT ON DELETE  CASCADE,
FOREIGN KEY (product_id) REFERENCES product (product_id) ON UPDATE RESTRICT ON DELETE  CASCADE,
UNIQUE KEY `link` (product_id, category_id)
);

--rollback DROP TABLE link_product_category;