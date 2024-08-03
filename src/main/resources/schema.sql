CREATE TABLE t_notification ( Id BIGINT AUTO_INCREMENT PRIMARY KEY,
 description VARCHAR(255) , email VARCHAR(255), name varchar(255), number BIGINT, time_received TIMESTAMP,time_sent TIMESTAMP, client VARCHAR(255))



CREATE TABLE t_user_app ( user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
 active bit(1) , email VARCHAR(255), name varchar(255),password varchar(255), payment TIMESTAMP,username varchar(255), phone VARCHAR(255))
