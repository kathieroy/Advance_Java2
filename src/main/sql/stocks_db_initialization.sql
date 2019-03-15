/** create the stocks database */
DROP TABLE IF EXISTS stockInterests CASCADE;
DROP TABLE IF EXISTS quotes CASCADE;
DROP TABLE IF EXISTS person CASCADE;


CREATE TABLE quotes(
   id INT NOT NULL AUTO_INCREMENT,
   symbol VARCHAR(4) NOT NULL,
   time DATETIME NOT NULL,
   price DECIMAL(13,2) NOT NULL,
   PRIMARY KEY ( id )
);
CREATE TABLE person
(
   id        INT         NOT NULL AUTO_INCREMENT,
   first_Name VARCHAR(30) NOT NULL,
   last_Name  VARCHAR(30) NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE stockInterests
(
   ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   person_id INT NOT NULL,
   symbol VARCHAR(4) NOT NULL,
   FOREIGN KEY (person_id) REFERENCES person (id)
);


INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2014-08-19 00:01:01','85.00');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2014-09-03 00:02:01','86.35');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-01-01 00:03:01','107.00');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-01-01 00:04:01','117.04');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-01-02 00:04:01','127.05');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-01-02 00:05:01','137.55');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-02-01 00:06:01','120.66');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-02-01 00:07:01','122.77');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-02-01 00:08:01','123.88');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-03-11 00:09:01','127.99');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-03-11 00:10:01','133.45');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-03-11 00:11:01','148.35');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-04-30 00:12:01','104.35');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-01-01 00:10:01','18.49');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-01-01 00:20:01','28.50');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-01-01 00:30:01','38.55');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-01-01 00:35:01','48.66');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-01-01 00:40:01','58.76');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-01-01 00:45:01','108.88');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-02-01 00:00:01','118.99');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-02-01 00:11:01','128.33');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-03-01 00:22:01','138.44');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-04-01 00:33:01','148.55');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-04-01 00:44:01','158.66');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-04-01 00:45:01','210.77');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-04-01 00:46:01','218.88');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-04-01 00:47:01','215.99');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-05-01 00:10:01','248.12');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2018-05-01 00:11:01','268.20');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 01:00:01','63.25');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 02:00:01','63.26');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 03:00:01','63.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 04:00:01','88.28');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 04:31:01','98.28');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 04:35:01','99.29');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 04:49:01','163.11');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 05:00:01','253.31');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 06:00:01','303.41');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 07:00:01','313.51');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 08:00:01','333.61');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-04 12:00:01','323.71');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-04 12:40:01','363.81');
INSERT INTO person (first_name,last_Name) VALUES ('Kathie','Roy');
INSERT INTO person (first_name,last_Name) VALUES ('Thomas','Jefferson');
INSERT INTO person (first_name,last_Name) VALUES ('Hank','Williams');
INSERT INTO stockInterests (person_id,symbol) VALUES (1,'APPL');
INSERT INTO stockInterests (person_id,symbol) VALUES (1,'AMZN');
INSERT INTO stockInterests (person_id,symbol) VALUES (1,'GOOG');
INSERT INTO stockInterests (person_id,symbol) VALUES (2,'APPL');
INSERT INTO stockInterests (person_id,symbol) VALUES (3,'GOOG');