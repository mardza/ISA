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



INSERT INTO clinics (name, address, description) 
VALUES ('Clinic 1', 'Address 1', 'This is clinic 1 description.');

INSERT INTO clinics (name, address, description) 
VALUES ('Clinic 2', 'Address 2', 'This is clinic 2 description.');

INSERT INTO clinics (name, address, description) 
VALUES ('Clinic 3', 'Address 3', 'This is clinic 3 description.');



--INSERT INTO users (email, password, firstName, lastName, address, city, country, phone, insuranceNumber)
--VALUES ('', '', '', '', '', '', '', '', '');

INSERT INTO registrations (id, approved, activated)
VALUES (1, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('admin_center@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Admin Center first', 'Admin last', 'Admin address', 'Admin city', 'Admin country', '0631231231', 5, 1);


INSERT INTO registrations (id, approved, activated)
VALUES (2, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id, clinic_id)
VALUES ('admin_clinic1@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Admin Clinic 1 first', 'Admin last', 'Admin address', 'Admin city', 'Admin country', '0631231231', 4, 2, 1);

INSERT INTO registrations (id, approved, activated)
VALUES (3, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id, clinic_id)
VALUES ('admin_clinic2@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Admin Clinic 2 first', 'Admin last', 'Admin address', 'Admin city', 'Admin country', '0631231231', 4, 3, 2);



INSERT INTO registrations (id, approved, activated)
VALUES (4, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('nurse@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Nurse first', 'Nurse last', 'Nurse address', 'Nurse city', 'Nurse country', '0631231231', 3, 4);


INSERT INTO registrations (id, approved, activated)
VALUES (5, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('doctor1@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Doctor1 first', 'Doctor1 last', 'Doctor address', 'Doctor city', 'Doctor country', '0631231231', 2, 5);

INSERT INTO registrations (id, approved, activated)
VALUES (6, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('doctor2@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Doctor2 first', 'Doctor2 last', 'Doctor address', 'Doctor city', 'Doctor country', '0631231231', 2, 6);


INSERT INTO registrations (id, approved, activated)
VALUES (7, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id)
VALUES ('patient@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Patient first', 'Patient last', 'Patient address', 'Patient city', 'Patient country', '0631231231', 1, 7);



INSERT INTO rooms (number, name, clinic_id) 
VALUES ('ROOM_1', 'Room 1', 1);

INSERT INTO rooms (number, name, clinic_id) 
VALUES ('ROOM_2', 'Room 2', 1);

INSERT INTO rooms (number, name, clinic_id) 
VALUES ('ROOM_3', 'Room 3', 2);



INSERT INTO appointmenttypes (clinic_id, name, price, discount) 
VALUES (1, 'Basic exam', 1000, 0);

INSERT INTO appointmenttypes (clinic_id, name, price, discount) 
VALUES (1, 'Dermatology exam', 3000, 5);

INSERT INTO appointmenttypes (clinic_id, name, price, discount) 
VALUES (1, 'Cardiology exam', 3500, 10);


INSERT INTO appointmenttypes (clinic_id, name, price, discount) 
VALUES (2, 'Cardiology exam', 3500, 0);

INSERT INTO appointmenttypes (clinic_id, name, price, discount) 
VALUES (2, 'Physical  exam', 3000, 50);



-- if patient_id == null -> predefinisan pregled
INSERT INTO appointments (time, duration, type_id, clinic_id, room_id, doctor_id, patient_id) 
VALUES ('2020-1-30 09:30:00', 1800000, 1, 1, 1, 5, 7);

INSERT INTO appointments (time, duration, type_id, clinic_id, room_id, doctor_id, patient_id) 
VALUES ('2020-1-30 10:00:00', 3600000, 1, 1, 1, 5, NULL);

INSERT INTO appointments (time, duration, type_id, clinic_id, room_id, doctor_id, patient_id) 
VALUES ('2020-1-30 12:00:00', 1800000, 2, 1, 1, 5, NULL);

INSERT INTO appointments (time, duration, type_id, clinic_id, room_id, doctor_id, patient_id) 
VALUES ('2020-1-30 09:30:00', 1800000, 3, 1, 2, 6, NULL);


--INSERT INTO users_roles (user_id, role_id)
--VALUES ( , );

--INSERT INTO users_roles (user_id, role_id)
--VALUES (1 , 5);

;