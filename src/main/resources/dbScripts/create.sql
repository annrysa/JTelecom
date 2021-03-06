CREATE TABLE user
(
 user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 firstname VARCHAR(20) NOT NULL,
 lastname VARCHAR(20) NOT NULL,
 email VARCHAR(40) NOT NULL UNIQUE,
 phone VARCHAR(20) NOT NULL UNIQUE,
 balance BIGINT DEFAULT 0,
 password VARCHAR(100) NOT NULL,
 active BIGINT,
 loyalty BIGINT
);

CREATE TABLE role
(
 role_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 role VARCHAR(20) NOT NULL
);

CREATE TABLE user_role
(
 role_id BIGINT NOT NULL,
 user_id BIGINT NOT NULL
);


ALTER TABLE user_role ADD CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES role (role_id);
ALTER TABLE user_role ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES user (user_id);

INSERT INTO role (role) VALUES ('USER');
INSERT INTO user VALUES(1,'Ann','Rysa','annrysa@gmail.com','099999999',150,'$2a$10$VamRAHnSAjY7ZdRW6zCNzOI6yu4dqOOHCISnjVnDTDz0AX6foflse',1, 600);
INSERT INTO user VALUES(2,'Peter','Moss','peter@gmail.com','098888888',0,'$2a$10$zUiR50T9nt8Q/1MVFf0n6unhv6J8srLEpYJ1uzOYYJ6hYEm4H9Cpa',1, 1500);

INSERT INTO user_role
VALUES (1,1);

CREATE TABLE tariff
(
 tariff_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(60),
 description VARCHAR(150),
 price BIGINT,
 sms VARCHAR(100),
 internet VARCHAR(100),
 internet_unlimited BIGINT,
 calls_min VARCHAR(100),
 calls_min_abroad VARCHAR(100),
 calls_min_unlimited BIGINT
);

CREATE TABLE user_tariff
(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
user_id BIGINT,
tariff_id BIGINT
);

ALTER TABLE user_tariff ADD CONSTRAINT tariff_id_fk FOREIGN KEY (tariff_id) REFERENCES tariff (tariff_id);
ALTER TABLE user_tariff ADD CONSTRAINT user_tarif_id_fk FOREIGN KEY (user_id) REFERENCES user (user_id);

INSERT INTO tariff VALUES
(1, 'JTelecom SuperNet Start', '4 +2 GB Internet / 80 min in Ukraine', 90, 0, '4 GB + 2 GB/3 months 4G/3G Internet', 0, '80 min within Ukraine or to Poland', '30 min calls abroad', 0);
INSERT INTO tariff VALUES
(2, 'JTelecom SuperNet Pro', '10 GB Internet / 1500 min in Ukraine', 125, 100, '10GB 4G/3G Internet', 0, '120 min within Ukraine or to Poland', '30 min calls abroad', 0);
INSERT INTO tariff VALUES
(3, 'JTelecom SuperNet Unlim', 'Unlimited Internet / 1500 min in Ukraine', 185, 100, 'Unlimited Internet', 1, '200 min within Ukraine or to Poland', '30 min calls abroad', 0);
INSERT INTO tariff VALUES
(4, 'JTelecom SuperNet Lux', 'Unlimited Internet / 2000 min in Ukraine', 225, 200, 'Unlimited Internet', 1, 'Unlimited calls within Ukraine or to Poland', '30 min calls abroad', 1);

INSERT INTO user_tariff VALUES (1,1,1);
INSERT INTO user_tariff VALUES (2,2,4);

CREATE TABLE service_internet
(
 service_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(60),
 description VARCHAR(600),
 price BIGINT,
 activation_cost BIGINT,
 validity VARCHAR(20),
 internet VARCHAR(100),
 internet_sharing BIGINT
 );
 
 CREATE TABLE service_roaming
 (
 service_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(60),
 description VARCHAR(600),
 price BIGINT,
 activation_cost BIGINT,
 validity VARCHAR(20),
 sms VARCHAR(100) DEFAULT NULL,
 internet VARCHAR(100),
 calls_min_abroad_out VARCHAR(100) DEFAULT NULL,
 calls_min_abroad_in VARCHAR(100) DEFAULT NULL
 ); 
 
 CREATE TABLE service_calls
 (
 service_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(60),
 description VARCHAR(600),
 price BIGINT,
 validity VARCHAR(20),
 activation_cost BIGINT,
 calls_min_abroad VARCHAR(100)
 );
 
 INSERT INTO service_calls
 VALUES
 (1,'30 minutes to Ukraine and world','Profitable calls to 62 world\'s countries and in Ukraine',20,'1 month',20,'30 min');

 INSERT INTO service_calls
 VALUES
 (2,'Poland connected','Profitable calls to Poland',25,'1 month',25,'100 min');
 
 INSERT INTO service_calls
 VALUES
 (3,'Moldova online','Package of minutes for available calls to Moldova',45,'28 days',45,'20 min');
 
 INSERT INTO service_roaming
 VALUES
 (1,'Roaming WeekEnd','Profitable roaming on 3 days trips',80,0,'3 days','10 SMS to all networks','100 MB','30 min','30 min'); 
 
 INSERT INTO service_roaming
 VALUES
 (2,'Roam like home','Available roaming on 7 days trips',0,150,'7 days','50 SMS to all networks','200 MB','70 min','30 min' );

 INSERT INTO service_roaming
 VALUES
 (3,'HalfGbyte on the road','Profitable Internet for Your vacation',0,150,'7 days','-','500 MB','-','-' );
 
 INSERT INTO service_internet 
 VALUES
 (1,'+12 GB','All clients of Jtelecom SuperNet Start, SuperNet Pro, SuperNet Unlim, SuperNet Lux can activate and use even more GB of 4G/3G Internet from the moment of activation',100,100,'3 month','4 GB/month',0);
 
 INSERT INTO service_internet 
 VALUES
 (2,'Data sharing for day','Use your smartphones as modem within 24 hours!',10,10,'1 day','According to tariff',1);
 
 INSERT INTO service_internet 
 VALUES
 (3,'Data sharing for month','Use your smartphones as modem the whole month!',50,50,'1 month','According to tariff',1);
 
 CREATE TABLE user_service_internet
 (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id BIGINT,
 service_id BIGINT
 );
 
 ALTER TABLE user_service_internet ADD CONSTRAINT service_int_id_fk FOREIGN KEY (service_id) REFERENCES service_internet (service_id);
 ALTER TABLE user_service_internet ADD CONSTRAINT user_service_int_id_fk FOREIGN KEY (user_id) REFERENCES user (user_id);
 
 CREATE TABLE user_service_roaming
 (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id BIGINT,
 service_id BIGINT
 );
 
 ALTER TABLE user_service_roaming ADD CONSTRAINT service_roam_id_fk FOREIGN KEY (service_id) REFERENCES service_roaming (service_id);
 ALTER TABLE user_service_roaming ADD CONSTRAINT user_service_roam_id_fk FOREIGN KEY (user_id) REFERENCES user (user_id);
 
 CREATE TABLE user_service_calls
 (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id BIGINT,
 service_id BIGINT
 );
 
 ALTER TABLE user_service_calls ADD CONSTRAINT service_call_id_fk FOREIGN KEY (service_id) REFERENCES service_calls (service_id);
 ALTER TABLE user_service_calls ADD CONSTRAINT user_service_call_id_fk FOREIGN KEY (user_id) REFERENCES user (user_id);

 INSERT INTO user_service_internet
 VALUES
 (1,1,1); 
 
 INSERT INTO user_service_roaming
 VALUES
 (1,1,1); 
 
 INSERT INTO user_service_calls
 VALUES
 (1,1,1);

 CREATE TABLE order_history
 (
 order_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 user_id BIGINT,
 name VARCHAR(200),
 date_time VARCHAR(200)
 );
 
 ALTER TABLE order_history ADD CONSTRAINT user_order_id_fk FOREIGN KEY (user_id) REFERENCES user (user_id);
 
 INSERT INTO order_history
 VALUES
 (1,1,'Activated tariff \'JTelecom SuperNet Start\'', '24 Nov 2019 19:17'); 
 
 INSERT INTO order_history
 VALUES
 (2,1,'Activated service \'Roaming WeekEnd\'', '24 Nov 2019 19:17'); 
 
 INSERT INTO order_history
 VALUES
 (3,1,'Activated service \'+12 GB\'', '24 Nov 2019 19:17'); 
 
 INSERT INTO order_history
 VALUES
 (4,1,'Activated service \'30 minutes to Ukraine and world\'', '24 Nov 2019 19:17');
 
 CREATE TABLE home_internet
 (
 home_internet_id BIGINT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(60),
 description VARCHAR(150),
 price BIGINT,
 internet VARCHAR(100),
 internet_unlimited BIGINT
 );
 
 CREATE TABLE user_home_internet
 (
 id BIGINT PRIMARY KEY AUTO_INCREMENT,
 home_internet_id BIGINT,
 user_id BIGINT,
 appointment VARCHAR(100),
 is_active BIGINT DEFAULT 0
 );
 
 ALTER TABLE user_home_internet ADD CONSTRAINT home_internet_id_fk FOREIGN KEY (home_internet_id) REFERENCES home_internet (home_internet_id);
 ALTER TABLE user_home_internet ADD CONSTRAINT user_home_internet_id_fk FOREIGN KEY (user_id) REFERENCES user (user_id);

INSERT INTO home_internet
VALUES
(1, 'Base RED S', 'Unlimited Internet, 100 Mb/s', 120, '100 Mb/s', 1);

INSERT INTO home_internet
VALUES
(2, 'RED M Plus', 'Unlimited Internet, 300 Mb/s', 180, '300 Mb/s', 1);

INSERT INTO home_internet
VALUES
(3, 'RED Lux', 'Unlimited Internet, 500 Mb/s', 120, '500 Mb/s', 1);

INSERT INTO user_home_internet
VALUES
(1,3,1,'19 November 2019 12:50',0);

 INSERT INTO order_history
 VALUES
 (5,1,'Activated home Internet \'Base RED S\'', '24 Nov 2019 19:17');

CREATE TABLE loyalty
(
loyalty_id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(60),
description VARCHAR(150),
amount BIGINT
);

CREATE TABLE user_loyalty
(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
loyalty_id BIGINT,
user_id BIGINT,
loyalty_code VARCHAR(100),
due_date_active VARCHAR(100),
is_active BIGINT
);

 ALTER TABLE user_loyalty ADD CONSTRAINT loyalty_id_fk FOREIGN KEY (loyalty_id) REFERENCES loyalty (loyalty_id);
 ALTER TABLE user_loyalty ADD CONSTRAINT user_loyalty_id_fk FOREIGN KEY (user_id) REFERENCES user (user_id);
 
 INSERT INTO loyalty
 VALUES
 (1, '100 UAH for pizza', 'To get discount tell the waiter received promocode placing in order', 500); 
 
 INSERT INTO loyalty
 VALUES
 (2, '200 UAH for clothes', 'Buy clothes, shoes, accessoires with 200 UAH discount', 1000); 
 
 INSERT INTO loyalty
 VALUES
 (3, '5% on www.karabos.com', 'Buy tickets to the concert with 5% discount on www.karabos.com', 600);
 
 INSERT INTO user_loyalty
 VALUES
 (1,1,1, '2815263670567', '23 Nov 2019 21:20',1); 
 INSERT INTO user_loyalty
 VALUES
 (2,1,1, '2815263670567', '31 Dec 2019 19:20',1);
 
 INSERT INTO order_history
 VALUES
 (6,1,'Activated bonus \'100 UAH for pizza\'', '24 Nov 2019 19:17');