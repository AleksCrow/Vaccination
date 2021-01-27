INSERT INTO USERS (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, BIRTH_DATE) VALUES
('user@gmail.com', 'User_First', 'User_Last', '{noop}password', '1990-12-31'),
('admin@gmail.com', 'Admin_First', 'Admin_Last', '{noop}admin', '1985-06-06');

INSERT INTO USER_ROLE (ROLE, USER_ID) VALUES
('USER', 1),
('ADMIN', 2),
('USER', 2);

INSERT INTO VACCINE (DISEASE_NAME, VACCINE_NAME, AGE) VALUES
('Гепатит В', 'Hepatitis B', 86400),
('Туберкульоз', 'Tuberculosis', 345600),
('Гепатит В', 'Hepatitis B', 2592000000),
('Дифтерія', 'Diphtheria', 2592000000),
('Кашлюк', 'Pertussis', 2592000000),
('Правець', 'Tetanus', 2592000000),
('Поліомієліт', 'Poliomyelitis', 2592000000),
('Гемофільна інфекція', 'Haemophilus', 2592000000);