INSERT INTO user (id, first_name, last_name, middle_name, position, phone, is_identified, office_id, login, password, version) VALUES (1, 'Иванов', 'Иван', 'Иванович', 'Генеральный директор', '88002223331', true, 1, 'ivan_gen', 'qwerty', 1);
INSERT INTO user (id, first_name, last_name, middle_name, position, phone, is_identified, office_id, login, password, version) VALUES (2, 'Петров', 'Иван', 'Петрович', 'Менеджер', '203040', true, 1, 'ivan_manager', '123456', 1);
INSERT INTO user (id, first_name, last_name, middle_name, position, phone, is_identified, office_id, login, password, version) VALUES (3, 'Сидорова', 'Нина', 'Петровна', 'Бухгалтер', '303050', true, 2, 'nina_buh', '505050', 1);


INSERT INTO organization(id, name, full_name, inn, kpp, address, phone, is_active, version) VALUES (1, 'Ромашка', 'Общество с ограниченной общественностью Ромашка', '581234567891', '123456789', 'ул.Кирова,17', '502050', true, 1);
INSERT INTO organization(id, name, full_name, inn, kpp, address, phone, is_active, version) VALUES (2, 'Страхстрой', 'Общество с ограниченной общественностью Страхстрой', '583331235552', '123456789', 'ул.Ленина', '1111', true, 1);
INSERT INTO organization(id, name, full_name, inn, kpp, address, phone, is_active, version) VALUES (3, 'Агент', 'Общество с ограниченной общественностью Агент', '582222567891', '123333333', 'ул.Полка', '1235555', true, 1);


INSERT INTO office (id, name, phone, is_active, address, org_id, version) VALUES (1, 'Офис1', '1234444', true, 'ул.Минская', 1, 1);
INSERT INTO office (id, name, phone, is_active, address, org_id, version) VALUES (2, 'Офис2', '1235555', true, 'ул.Совхозная', 1, 1);
INSERT INTO office (id, name, phone, is_active, address, org_id, version) VALUES (3, 'Страхстрой-офис', '201010', true, 'ул.Лядова', 2, 1);


INSERT INTO doc_type (id, name, code, version) VALUES (1, 'Паспорт гражданина Российской Федерации','21',1);
INSERT INTO doc_type (id, name, code, version) VALUES (2, 'Военный билет','07',1);
INSERT INTO doc_type (id, name, code, version) VALUES (3, 'Иные документы','91',1);


INSERT INTO country_code (id, name, code, version) VALUES (1, 'РОССИЯ','643',1);
INSERT INTO country_code (id, name, code, version) VALUES (2, 'ИНДИЯ','356',1);
INSERT INTO country_code (id, name, code, version) VALUES (3, 'БЕЛАРУСЬ','112',1);
INSERT INTO country_code (id, name, code, version) VALUES (4, 'АВСТРАЛИЯ','036',1);
INSERT INTO country_code (id, name, code, version) VALUES (5, 'КАЗАХСТАН','398',1);



INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date, version) VALUES (1, 1, 1, 1, '1234 123456', '1990-02-20', 1);
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date, version) VALUES (2, 1, 2, 1, '123456', '2008-03-20', 1);
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date, version) VALUES (3, 2, 1, 3, '123412123456', '1993-02-21', 1);
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date, version) VALUES (4, 2, 3, 3, '1234 123456', '1990-01-25', 1);
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date, version) VALUES (5, 3, 1, 1, '1234 6', '2015-05-03', 1);