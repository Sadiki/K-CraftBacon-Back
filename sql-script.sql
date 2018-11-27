/*******************************************************************************
   Create database
********************************************************************************/
drop table credit_card_info;
drop table customers;
drop table inventory;
drop table order_items;
drop table orders;
drop table special_orders;




/*******************************************************************************
   Create Tables
********************************************************************************/

CREATE TABLE CUSTOMERS
(
    cust_id                 NUMBER           NOT NULL,
    first_name              VARCHAR2(20)     NOT NULL,
    last_name               VARCHAR2(20)     NOT NULL,
    username                VARCHAR2(20)     NOT NULL UNIQUE,
    password                VARCHAR2(20)     NOT NULL,
    email                   VARCHAR2(20)     NOT NULL UNIQUE,
    phone_number            VARCHAR2(20)     NOT NULL,
    street_address          VARCHAR2(20),
    city                    VARCHAR2(20),
    state                   VARCHAR2(20),
    zip                     NUMBER,
    newsletter_option       NUMBER,
    
     CONSTRAINT PK_Customers PRIMARY KEY(Cust_ID)
);

CREATE TABLE CREDIT_CARD_INFO
(
    card_number             VARCHAR2(40),
    card_full_name          VARCHAR2(40),
    card_security_code      NUMBER,
    card_expiration         VARCHAR(10),
    cust_id             NUMBER,

     CONSTRAINT PK_card  PRIMARY KEY(card_number)     
);
    
CREATE TABLE ORDER_ITEMS
(
    id                      NUMBER,
    cust_id                 NUMBER,
    quantity                NUMBER,
    item_id                 NUMBER,
    status                  NUMBER,
    order_id                NUMBER,
    special_order_id        NUMBER,

    CONSTRAINT PK_saved_cart PRIMARY KEY(id)
);

CREATE TABLE ORDERS
(
    order_id                NUMBER,
    cust_id                 NUMBER,
    order_status_id         NUMBER,
    created_date            TIMESTAMP,
    order_update            TIMESTAMP,
    shipping_status         NUMBER,
    delivery_method_id      NUMBER,
    shipping_price          DOUBLE PRECISION,
    order_price             DOUBLE PRECISION,

    CONSTRAINT PK_orders PRIMARY KEY(order_id)
);

CREATE TABLE SPECIAL_ORDERS
(
    special_order_id        NUMBER,
    meat_type               VARCHAR2(100),
    smoke_flavor            VARCHAR2(100),               
    seasonings              VARCHAR2(100), 
    measured_weight         DOUBLE PRECISION,
    special_order_price     DOUBLE PRECISION,

    CONSTRAINT PK_special_orders PRIMARY KEY (special_order_id)
);

alter table special_orders
drop column measured_weight;

alter table special_orders
add measured_weight double precision;

CREATE TABLE INVENTORY 
(
    item_id                     NUMBER,                     
    item_name                   VARCHAR2(30),
    item_description            VARCHAR2(500),
    item_price                  DOUBLE PRECISION,
    on_hand_quantity            NUMBER,
    item_picture                BLOB,

    CONSTRAINT PK_item_id PRIMARY KEY(item_id)
);

/*******************************************************************************
   Create Foreign Keys
********************************************************************************/

ALTER TABLE CREDIT_CARD_INFO ADD CONSTRAINT FK_Cust_Id
    FOREIGN KEY(cust_id) REFERENCES CUSTOMERS(cust_id);

ALTER TABLE ORDER_ITEMS ADD CONSTRAINT FK_CUSTOMER_ITEMS
    FOREIGN KEY(cust_id) REFERENCES CUSTOMERS (cust_id);

ALTER TABLE ORDERS ADD CONSTRAINT FK_ORDER_ITEMS
    FOREIGN KEY(cust_id) REFERENCES CUSTOMERS(cust_id);

ALTER TABLE ORDER_ITEMS ADD CONSTRAINT FK_ITEMS_ORDER
    FOREIGN KEY(order_id) REFERENCES ORDERS(order_id);

ALTER TABLE ORDER_ITEMS ADD CONSTRAINT FK_SPECIAL_ORDERS
    FOREIGN KEY(special_order_id) REFERENCES special_orders(special_order_id);

ALTER TABLE ORDER_ITEMS ADD CONSTRAINT FK_items_inventory
    FOREIGN KEY(item_id) REFERENCES INVENTORY(item_id);
/*******************************************************************************
   Populate Tables
********************************************************************************/

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (1, 'Applewood Smoked Bacon', 'Cured bacon smoked with Applewood', 9.99, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/applewood+bacon.jpg");

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (2, 'Cherrywood Smoked Bacon', 'Cured bacon smoked with Cherrywood', 9.99, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/cherrywood+bacon.jpg");

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (3, 'Speck Italian Bacon', 'Italian bacon made from deboned pork legs. Cured with spices such as garlic, nutmeg, and juniper. When it’s cooked it looks a lot like regular bacon, but the spices give it it’s own unique flavor.', 14.99, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/seitan+bacon.jpg");

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (4, 'Coconut Bacon', 'It’s bacon for vegans! This healthy alternative is made from coconut flakes which are coated with maple syrup, tamari, and liquid smoke. When baked instead of fried, makes a taste unexpectedly similar to traditional bacon!', 13.99, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/coconut+bacon.jpg");

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (5, 'Seiten', 'Commonly known as wheat meat, the protein found within wheat is used to make this high protein gluten filled mock meat that any vegetarian lover will enjoy!', 14.99, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/seitan+bacon.jpg");

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (6, 'Lamb Bacon', 'Bacon isn’t always just pork! You can make bacon out of anything! This lamb bacon is salted, smoked and sliced to perfection.', 12.99, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/lamb+bacon.jpg");

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (7, 'Beef Bacon', 'Try a leaner alternative to regular bacon. Beef bacon is a leaner alternative to traditional pork bacon but could pass for regular bacon!', 9.99, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/beef+bacon.jpg");

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (8, 'Elk Bacon', 'Fashioned from the beautiful wilderness of Colorado, elk bacon is a haughtier alternative to traditional pork bacon. Cured in a variety of spices and smoked to perfection using Coloradoan Hickory wood.', 19.99, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/elk+bacon.jpg");

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (9, 'Jalepeno Bacon', 'Hickory smoked bacon seasoned with jalepeno.', 10.99, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/jalepeno+bacon.webp");

INSERT INTO inventory (item_id, item_name, item_description, item_price, item_picture)
    VALUES (10, 'Sugar Chicken Bacon', 'Sugar chicken bacon', 8.65, "https://s3.amazonaws.com/k-craft-bacon/bacon-pics/sugar+bacon.jpg");

INSERT INTO customers 
VALUES (1, 'Admin', 'User', 'Admin', 'password', 'admin@bacon.com', '1234 Block', 'Tampa', 'FL', 1,  '1234567890', '12345');

commit;

