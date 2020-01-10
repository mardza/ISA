INSERT INTO roles (name)
VALUES ('ROLE_PATIENT');

INSERT INTO roles (name)
VALUES ('ROLE_DOCTOR');

INSERT INTO roles (name)
VALUES ('ROLE_NURSE');

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN_CLINIC');

INSERT INTO roles (name)
VALUES ('ROLE_ADMIN_CENTER');



--INSERT INTO users (email, password, firstName, lastName, address, city, country, phone, insuranceNumber)
--VALUES ('', '', '', '', '', '', '', '', '');

INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id)
VALUES ('admin@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Admin first', 'Admin last', 'Admin address', 'Admin city', 'Admin country', '0631231231', 5);



--INSERT INTO users_roles (user_id, role_id)
--VALUES ( , );

--INSERT INTO users_roles (user_id, role_id)
--VALUES (1 , 5);

;