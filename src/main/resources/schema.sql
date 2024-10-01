/*'1-ый уровень'*/

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

/*тип электроники*/
CREATE TABLE IF NOT EXISTS electro_type (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
CONSTRAINT electro_type_pk PRIMARY KEY(id)
);

/*магазин*/
CREATE TABLE IF NOT EXISTS shop (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(250) NOT NULL,
address TEXT NOT NULL,
CONSTRAINT shop_pk PRIMARY KEY(id)
);



/*'2-ой уровень'*/

/* электро товары (реестр)*/
CREATE TABLE IF NOT EXISTS electro_item (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(150) NOT NULL,
e_type_id BIGINT NOT NULL,
price BIGINT NOT NULL,
count_ INT NOT NULL,
archive BOOL NOT NULL, /* 'BOOL and BOOLEAN are synonyms of TINYINT(1), Zero is false, anything else is true'*/
description TEXT,
CONSTRAINT electro_item_pk PRIMARY KEY(id),

CONSTRAINT e_item_e_type_fk FOREIGN KEY (e_type_id)
    REFERENCES electro_type (id) ON UPDATE CASCADE
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
CONSTRAINT employee_pk PRIMARY KEY(id),

CONSTRAINT employee_pos_type_fk FOREIGN KEY (position_id)
    REFERENCES position_type (id) ON UPDATE CASCADE,
CONSTRAINT employee_shop_fk FOREIGN KEY (shop_id)
    REFERENCES shop (id) ON UPDATE CASCADE
);


/*'3-ий уровень'*/

/*связь магазин-товар*/
CREATE TABLE IF NOT EXISTS electro_shop (
shop_id BIGINT NOT NULL,
electro_item_id BIGINT NOT NULL,
count_ INT NOT NULL,
CONSTRAINT electro_shop_pk PRIMARY KEY(shop_id, electro_item_id),

CONSTRAINT electro_shop_shop_fk FOREIGN KEY (shop_id)
    REFERENCES shop (id) ON UPDATE CASCADE,
CONSTRAINT electro_shop_e_item_fk FOREIGN KEY (electro_item_id)
    REFERENCES electro_item (id) ON UPDATE CASCADE
);


/*связь тип электроники-сотрудники*/
CREATE TABLE IF NOT EXISTS electro_employee (
employee_id BIGINT NOT NULL,
electro_type_id BIGINT NOT NULL,
CONSTRAINT electro_employee_pk PRIMARY KEY(employee_id, electro_type_id),

CONSTRAINT e_empl_empl_fk FOREIGN KEY (employee_id)
    REFERENCES employee (id) ON UPDATE CASCADE,
CONSTRAINT e_empl_e_type_fk FOREIGN KEY (electro_type_id)
    REFERENCES electro_type (id) ON UPDATE CASCADE
);


/*покупки (реестр)*/
CREATE TABLE IF NOT EXISTS purchase (
id INT NOT NULL AUTO_INCREMENT,
electro_id BIGINT NOT NULL,
employee_id BIGINT NOT NULL,
purchase_date DATETIME NOT NULL,
type_id BIGINT NOT NULL,
shop_id BIGINT NOT NULL,
CONSTRAINT purchase_pk PRIMARY KEY(id),

CONSTRAINT purchase_e_item_fk FOREIGN KEY (electro_id)
    REFERENCES electro_item (id) ON UPDATE CASCADE,
CONSTRAINT purchase_employee_fk FOREIGN KEY (employee_id)
    REFERENCES employee (id) ON UPDATE CASCADE,
CONSTRAINT purchase_purchase_type_fk FOREIGN KEY (type_id)
    REFERENCES purchase_type (id) ON UPDATE CASCADE,
CONSTRAINT purchase_shop_fk FOREIGN KEY (shop_id)
    REFERENCES shop (id) ON UPDATE CASCADE
);