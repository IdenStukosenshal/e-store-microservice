/*связь магазин-товар*/
CREATE TABLE IF NOT EXISTS electro_shop (
shop_id BIGINT NOT NULL,
electro_item_id BIGINT NOT NULL,
count_ INT NOT NULL,
CONSTRAINT electro_shop_pk PRIMARY KEY(shop_id, electro_item_id)
);

/*магазин*/
CREATE TABLE IF NOT EXISTS shop (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(250) NOT NULL,
address TEXT NOT NULL,
CONSTRAINT shop_pk PRIMARY KEY(id)
);

/* электро товары (реестр)*/
CREATE TABLE IF NOT EXISTS electro_item (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
e_type_id BIGINT NOT NULL,
price BIGINT NOT NULL,
count_ INT NOT NULL,  /*'возможные проблемы из-за имени count'*/
archive BOOL NOT NULL, /* 'BOOL and BOOLEAN are synonyms of TINYINT(1), Zero is false, anything else is true'*/
description TEXT,
CONSTRAINT electro_item_pk PRIMARY KEY(id)
);

/*сотрудники (реестр)*/
CREATE TABLE IF NOT EXISTS employee (
id BIGINT NOT NULL AUTO_INCREMENT,
last_name VARCHAR(100) NOT NULL,
first_name VARCHAR(100) NOT NULL,
patronymyc VARCHAR(100) NOT NULL,
birth_date DATE NOT NULL,
position_id BIGINT NOT NULL,
shop_id BIGINT NOT NULL,
gender BOOL NOT NULL,
CONSTRAINT employee_pk PRIMARY KEY(id)
);

/*покупки (реестр)*/
CREATE TABLE IF NOT EXISTS purchase (
id INT NOT NULL AUTO_INCREMENT,
electro_id BIGINT NOT NULL,
employee_id BIGINT NOT NULL,
purchase_date DATETIME NOT NULL,
type_id BIGINT NOT NULL,
shop_id BIGINT NOT NULL,
CONSTRAINT purchase_pk PRIMARY KEY(id)
);

/*тип электроники*/
CREATE TABLE IF NOT EXISTS electro_type (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
CONSTRAINT electro_type_pk PRIMARY KEY(id)
);

/*связь тип электроники-сотрудники*/
CREATE TABLE IF NOT EXISTS electro_employee (
employee_id BIGINT NOT NULL,
electro_type_id BIGINT NOT NULL,
CONSTRAINT electro_employee_pk PRIMARY KEY(employee_id, electro_type_id)
);

/*должность*/
CREATE TABLE IF NOT EXISTS position_type (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
CONSTRAINT position_type_pk PRIMARY KEY(id)
);

/*тип покупки*/
CREATE TABLE IF NOT EXISTS purchase_type (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
CONSTRAINT purchase_type_pk PRIMARY KEY(id)
);

ALTER TABLE electro_shop
ADD CONSTRAINT electro_shop_shop_fk FOREIGN KEY (shop_id)
    REFERENCES shop (id),
ADD CONSTRAINT electro_shop_e_item_fk FOREIGN KEY (electro_item_id)
    REFERENCES electro_item (id);

ALTER TABLE electro_item
ADD CONSTRAINT e_item_e_type_fk FOREIGN KEY (e_type_id)
    REFERENCES electro_type (id);

ALTER TABLE employee
ADD CONSTRAINT employee_pos_type_fk FOREIGN KEY (position_id)
    REFERENCES position_type (id),
ADD CONSTRAINT employee_shop_fk FOREIGN KEY (shop_id)
    REFERENCES shop (id);

ALTER TABLE purchase
ADD CONSTRAINT purchase_e_item_fk FOREIGN KEY (electro_id)
    REFERENCES electro_item (id),
ADD CONSTRAINT purchase_employee_fk FOREIGN KEY (employee_id)
    REFERENCES employee (id),
ADD CONSTRAINT purchase_purchase_type_fk FOREIGN KEY (type_id)
    REFERENCES purchase_type (id),
ADD CONSTRAINT purchase_shop_fk FOREIGN KEY (shop_id)
    REFERENCES shop (id);

ALTER TABLE electro_employee
ADD CONSTRAINT e_empl_empl_fk FOREIGN KEY (employee_id)
    REFERENCES employee (id),
ADD CONSTRAINT e_empl_e_type_fk FOREIGN KEY (electro_type_id)
    REFERENCES electro_type (id);

