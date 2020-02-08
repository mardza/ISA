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



INSERT INTO clinics (name, address, city, country, description, rating_average, rating_weight) 
VALUES ('Clinic 1', 'Address 1', 'Novi Sad', 'Serbia', 'This is clinic 1 description.', 4.94, 21);

INSERT INTO clinics (name, address, city, country, description, rating_average, rating_weight) 
VALUES ('Clinic 2', 'Address 2', 'Novi Sad', 'Serbia', 'This is clinic 2 description.', 3.34, 12);

INSERT INTO clinics (name, address, city, country, description, rating_average, rating_weight) 
VALUES ('Clinic 3', 'Address 3', 'Podgorica', 'Montenegro', 'This is clinic 3 description.', 1.54, 4);


-- Duration must end with 9 (if duration is 1 second, then it should be 999ms) for doctor available period calculations to work
INSERT INTO appointmenttypes (name, duration) 
VALUES ('Basic exam', 1799999);

INSERT INTO appointmenttypes (name, duration) 
VALUES ('Dermatology exam', 3599999);

INSERT INTO appointmenttypes (name, duration) 
VALUES ('Cardiology exam', 1799999);

INSERT INTO appointmenttypes (name, duration) 
VALUES ('Physical  exam', 1799999);




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
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id, clinic_id)
VALUES ('nurse@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Nurse first', 'Nurse last', 'Nurse address', 'Nurse city', 'Nurse country', '0631231231', 3, 4, 1);


INSERT INTO registrations (id, approved, activated)
VALUES (5, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id, clinic_id, appointmenttype_id, work_start, work_end, rating_average, rating_weight)
VALUES ('doctor1@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Doctor1 first', 'Doctor1 last', 'Doctor address', 'Doctor city', 'Doctor country', '0631231231', 2, 5, 1, 1, 8, 16, 4.35, 10);

INSERT INTO registrations (id, approved, activated)
VALUES (6, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id, clinic_id, appointmenttype_id, work_start, work_end, rating_average, rating_weight)
VALUES ('doctor2@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Doctor2 first', 'Doctor2 last', 'Doctor address', 'Doctor city', 'Doctor country', '0631231231', 2, 6, 1, 2, 8, 16, 3.67, 17);

INSERT INTO registrations (id, approved, activated)
VALUES (7, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id, clinic_id, appointmenttype_id, work_start, work_end, rating_average, rating_weight)
VALUES ('doctor3@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Doctor3 first', 'Doctor3 last', 'Doctor address', 'Doctor city', 'Doctor country', '0631231231', 2, 7, 2, 2, 8, 16, 2.13, 4);



INSERT INTO registrations (id, approved, activated)
VALUES (8, true, true);
INSERT INTO users (email, password, first_name, last_name, address, city, country, phone, role_id, registration_id, insurance_number)
VALUES ('patient@g.com', '$2a$10$GGi3fzEAMIAgQE/lsADrueJsbTGbCYezOB/dAuXtMSUfkduNgTxX6', 'Patient first', 'Patient last', 'Patient address', 'Patient city', 'Patient country', '0631231231', 1, 8, '123456789');



INSERT INTO rooms (number, name, clinic_id) 
VALUES ('ROOM_1', 'Room 1', 1);

INSERT INTO rooms (number, name, clinic_id) 
VALUES ('ROOM_2', 'Room 2', 1);

INSERT INTO rooms (number, name, clinic_id) 
VALUES ('ROOM_3', 'Room 3', 2);




INSERT INTO prices (clinic_id, appointmenttype_id, price, discount) 
VALUES (1, 1, 1000, 0);

INSERT INTO prices (clinic_id, appointmenttype_id, price, discount) 
VALUES (1, 2, 3000, 5);

INSERT INTO prices (clinic_id, appointmenttype_id, price, discount) 
VALUES (1, 3, 3500, 10);


INSERT INTO prices (clinic_id, appointmenttype_id, price, discount) 
VALUES (2, 2, 2900, 0);

INSERT INTO prices (clinic_id, appointmenttype_id, price, discount) 
VALUES (2, 3, 3500, 0);

INSERT INTO prices (clinic_id, appointmenttype_id, price, discount) 
VALUES (2, 4, 3000, 50);



INSERT INTO appointments (time, type_id, clinic_id, price_id, room_id, doctor_id, patient_id, approved, predefined, requested, patient_approved) 
VALUES ('2020-2-10 09:30:00', 1, 1, 1, 1, 5, 8, true, true, true, true);

INSERT INTO appointments (time, type_id, clinic_id, price_id, room_id, doctor_id, patient_id, approved, predefined, requested, patient_approved) 
VALUES ('2020-2-10 08:00:00', 1, 1, 1, 1, 5, NULL, false, true, false, false);

INSERT INTO appointments (time, type_id, clinic_id, price_id, room_id, doctor_id, patient_id, approved, predefined, requested, patient_approved) 
VALUES ('2020-2-10 12:00:00', 1, 1, 1, 1, 5, NULL, false, true, false, false);

INSERT INTO appointments (time, type_id, clinic_id, price_id, room_id, doctor_id, patient_id, approved, predefined, requested, patient_approved) 
VALUES ('2020-3-25 09:30:00', 2, 1, 2, 2, 6, NULL, false, true, false, false);

INSERT INTO appointments (time, type_id, clinic_id, price_id, room_id, doctor_id, patient_id, approved, predefined, requested, patient_approved) 
VALUES ('2020-3-25 12:30:00', 2, 2, 4, 3, 7, NULL, false, true, false, false);


--INSERT INTO users_roles (user_id, role_id)
--VALUES ( , );

--INSERT INTO users_roles (user_id, role_id)
--VALUES (1 , 5);

;