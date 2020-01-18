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

INSERT INTO registrations (id, approved, activated)
VALUES (1, true, true);

INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('admin_center@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Admin Center first', 'Admin last', 'Admin address', 'Admin city', 'Admin country', '0631231231', 5, 1);


INSERT INTO registrations (id, approved, activated)
VALUES (2, true, true);

INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('admin_clinic@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Admin Clinic first', 'Admin last', 'Admin address', 'Admin city', 'Admin country', '0631231231', 4, 2);


INSERT INTO registrations (id, approved, activated)
VALUES (3, true, true);

INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('nurse@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Nurse first', 'Nurse last', 'Nurse address', 'Nurse city', 'Nurse country', '0631231231', 3, 3);


INSERT INTO registrations (id, approved, activated)
VALUES (4, true, true);

INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('doctor@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Doctor first', 'Doctor last', 'Doctor address', 'Doctor city', 'Doctor country', '0631231231', 2, 4);


INSERT INTO registrations (id, approved, activated)
VALUES (5, true, true);

INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('patient@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Patient first', 'Patient last', 'Patient address', 'Patient city', 'Patient country', '0631231231', 1, 5);



INSERT INTO clinics (name, address, description) 
VALUES ('Clinic 1', 'Address 1', 'This is clinic 1 description.');

INSERT INTO clinics (name, address, description) 
VALUES ('Clinic 2', 'Address 2', 'This is clinic 2 description.');



INSERT INTO rooms (number, name, clinic_id) 
VALUES ('ROOM_1', 'Room 1', 1);

INSERT INTO rooms (number, name, clinic_id) 
VALUES ('ROOM_2', 'Room 2', 1);


--INSERT INTO users_roles (user_id, role_id)
--VALUES ( , );

--INSERT INTO users_roles (user_id, role_id)
--VALUES (1 , 5);

;