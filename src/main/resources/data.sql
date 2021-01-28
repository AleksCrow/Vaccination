INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, BIRTH_DATE) VALUES
('user@gmail.com', 'User_First', 'User_Last', '{noop}1234', '1990-12-31'),
('admin@gmail.com', 'Admin_First', 'Admin_Last', '{noop}admin', '1985-06-06'),
('user2@gmail.com', 'User2_First', 'User2_Last', '{noop}1234', '2021-01-01'),
('user3@gmail.com', 'User3_First', 'User3_Last', '{noop}1234', '1920-06-06');

INSERT INTO USER_ROLE (ROLE, USER_ID) VALUES
('USER', 1),
('ADMIN', 2),
('USER', 2),
('USER', 3),
('USER', 4);

INSERT INTO VACCINES (DISEASE_NAME, VACCINE_NAME, AGE) VALUES
('Гепатит В', 'Hepatitis B', 1),
('Туберкулез', 'Tuberculosis', 4),
('Гепатит В', 'Hepatitis B', 60),
('Дифтерия', 'Diphtheria', 60),
('Коклюш', 'Pertussis', 60),
('Столбняк', 'Tetanus', 60),
('Полиомелит', 'Poliomyelitis', 60),
('Дифтерия', 'Diphtheria', 120),
('Коклюш', 'Pertussis', 120),
('Столбняк', 'Tetanus', 120),
('Полиомелит', 'Poliomyelitis', 120),
('Гемофильная инфекция', 'Haemophilus', 365),
('Корь', 'Morbilli', 365),
('Краснуха', 'Rubella', 365),
('Дифтерия', 'Diphtheria', 540),
('Столбняк', 'Tetanus', 540),
('Полиомелит', 'Poliomyelitis', 540);

INSERT INTO VACCINATIONS (VACCINATION_DATE, PLANNED_VACCINATION_DATE, USER_ID, VACCINE_ID) VALUES
('1991-01-01', null, 1, 1),
('1991-01-03', null, 1, 2),
('1991-06-03', null, 1, 6),
('1991-09-03', null, 1, 7),
('1992-01-03', null, 1, 9),
('1985-06-07', null, 2, 1),
('1985-06-10', null, 2, 2);