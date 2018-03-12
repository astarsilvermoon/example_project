INSERT INTO user (id, first_name, last_name, middle_name, position, phone, is_identified, office_id, login, password) VALUES (1, 'Иванов', 'Иван', 'Иванович', 'Генеральный директор', '88002223331', true, 1, 'ivan_gen', 'qwerty');
INSERT INTO user (id, first_name, last_name, middle_name, position, phone, is_identified, office_id, login, password) VALUES (2, 'Петров', 'Иван', 'Петрович', 'Менеджер', '203040', true, 1, 'ivan_manager', '123456');
INSERT INTO user (id, first_name, last_name, middle_name, position, phone, is_identified, office_id, login, password) VALUES (3, 'Сидорова', 'Нина', 'Петровна', 'Бухгалтер', '303050', true, 2, 'nina_buh', '505050');


INSERT INTO organization(id, name, full_name, inn, kpp, address, phone, is_active) VALUES (1, 'Ромашка', 'Общество с ограниченной общественностью Ромашка', '581234567891', '123456789', 'ул.Кирова,17', '502050', true);
INSERT INTO organization(id, name, full_name, inn, kpp, address, phone, is_active) VALUES (2, 'Страхстрой', 'Общество с ограниченной общественностью Страхстрой', '583331235552', '123456789', 'ул.Ленина', '1111', true);
INSERT INTO organization(id, name, full_name, inn, kpp, address, phone, is_active) VALUES (3, 'Агент', 'Общество с ограниченной общественностью Агент', '582222567891', '123333333', 'ул.Полка', '1235555', true);


INSERT INTO office (id, name, phone, is_active, address, org_id) VALUES (1, 'Офис1', '1234444', true, 'ул.Минская', 1);
INSERT INTO office (id, name, phone, is_active, address, org_id) VALUES (2, 'Офис2', '1235555', true, 'ул.Совхозная', 1);
INSERT INTO office (id, name, phone, is_active, address, org_id) VALUES (3, 'Страхстрой-офис', '201010', true, 'ул.Лядова', 2);


INSERT INTO doc_type (id, name, code) VALUES (1, 'Паспорт гражданина Российской Федерации','21');
INSERT INTO doc_type (id, name, code) VALUES (2, 'Военный билет','07');
INSERT INTO doc_type (id, name, code) VALUES (3, 'Иные документы','91');


INSERT INTO country_code (id, name, code) VALUES (1, 'РОССИЯ','643');
INSERT INTO country_code (id, name, code) VALUES (2, 'ИНДИЯ','356');
INSERT INTO country_code (id, name, code) VALUES (3, 'БЕЛАРУСЬ','112');
INSERT INTO country_code (id, name, code) VALUES (4, 'АВСТРАЛИЯ','036');
INSERT INTO country_code (id, name, code) VALUES (5, 'КАЗАХСТАН','398');



INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date) VALUES (1, 1, 1, 1, '1234 123456', '20-02-1990');
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date) VALUES (2, 1, 2, 1, '123456', '20-03-2008');
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date) VALUES (3, 2, 1, 3, '123412123456', '21-02-1993');
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date) VALUES (4, 2, 3, 3, '1234 123456', '20-02-1990');
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date) VALUES (5, 3, 1, 1, '1234 6', '13-05-2015');