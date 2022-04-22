CREATE KEYSPACE container_yard WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};
CREATE TABLE container(id int PRIMARY KEY, container_size int, container_type text, quantity int);
CREATE TABLE booking(booking_ref_id int PRIMARY KEY, container_size int, container_type text, origin text, destination text, quantity int, time_stamp timestamp);
INSERT INTO CONTAINER(id, container_size, container_type, quantity) VALUES(1,40,'DRY',10);
INSERT INTO CONTAINER(id, container_size, container_type, quantity) values(2,’20’,'DRY',5);
INSERT INTO CONTAINER(id, container_size, container_type, quantity) values(3,’40’,'REEFER',7);
INSERT INTO CONTAINER(id, container_size, container_type, quantity) values(4,’20’,'REEFER',9);
INSERT INTO CONTAINER(id, container_size, container_type, quantity) values(5,’40’,'DRY',22);
INSERT INTO CONTAINER(id, container_size, container_type, quantity) values(6,’20’,'REEFER',13);