INSERT INTO account(id, is_identified, login, password, name,  activation_code, is_activated, version) VALUES (1, true, 'general@gmail.com', 'NUU4ODQ4OThEQTI4MDQ3MTUxRDBFNTZGOERDNjI5Mjc3MzYwM0QwRDZBQUJCREQ2MkExMUVGNzIxRDE1NDJEOA==','Иван', '8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92', true, 1);
/*password=password, activation_code=123456*/
INSERT INTO account(id, is_identified, login, password, name, activation_code,is_activated, version) VALUES (2, true, 'silver@mail.ru', 'NzhDREU2NEMzRTQ3RjJDQkZEOURBNzIxRjU0QUFDREUzMzc3OTkxNjY4M0M3OURFODY5NjI4OThGRUVGQUMyMQ==','Сергей', '5694D08A2E53FFCAE0C3103E5AD6F6076ABD960EB1F8A56577040BC1028F702B', true, 1);
/*password=silver, activation_code=code*/
INSERT INTO account(id, is_identified, login, password, name, activation_code,is_activated, version) VALUES (3, true, 'mail@yandex.ru', 'MDBEOEQzRjExNzM5RDJGMzUzNzA5OTk4MkI0Njc0QzI5RkM1OUE4RkRBMzUwRkNBMTM3OTYxM0FEQkIwOTExOQ==', 'Марина', 'B7FE96979B3015D34BF391CC61FA554507E1D7B5F1B274C7B360A87E621097E6', true, 1);
/*password=mail, activation_code=code123*/





INSERT INTO organization(id, name, full_name, inn, kpp, address, phone, is_active, version) VALUES (1, 'Ромашка', 'Общество с ограниченной общественностью Ромашка', '581234567891', '123456789', 'ул.Кирова,17', '502050', true, 1);
INSERT INTO organization(id, name, full_name, inn, kpp, address, phone, is_active, version) VALUES (2, 'Страхстрой', 'Общество с ограниченной общественностью Страхстрой', '583331235552', '123456789', 'ул.Ленина', '1111', true, 1);
INSERT INTO organization(id, name, full_name, inn, kpp, address, phone, is_active, version) VALUES (3, 'Агент', 'Общество с ограниченной общественностью Агент', '582222567891', '123333333', 'ул.Полка', '1235555', true, 1);


INSERT INTO office (id, name, phone, is_active, address, org_id, version) VALUES (1, 'Офис1', '1234444', true, 'ул.Минская', 1, 1);
INSERT INTO office (id, name, phone, is_active, address, org_id, version) VALUES (2, 'Офис2', '1235555', true, 'ул.Совхозная', 1, 1);
INSERT INTO office (id, name, phone, is_active, address, org_id, version) VALUES (3, 'Страхстрой-офис', '201010', true, 'ул.Лядова', 2, 1);


INSERT INTO user (id, first_name, last_name, middle_name, position, phone, office_id, is_identified, version) VALUES (1, 'Иванов', 'Иван', 'Иванович', 'Генеральный директор', '88002223331',  1, true, 1);
INSERT INTO user (id, first_name, last_name, middle_name, position, phone, office_id, is_identified, version) VALUES (2, 'Петров', 'Иван', 'Петрович', 'Менеджер', '203040', 1, true, 1);
INSERT INTO user (id, first_name, last_name, middle_name, position, phone,  office_id, is_identified, version) VALUES (3, 'Сидорова', 'Нина', 'Петровна', 'Бухгалтер', '303050',  2, true,  1);


INSERT INTO doc_type (id, name, code, version) VALUES (1, 'Паспорт гражданина Российской Федерации','21',1);
INSERT INTO doc_type (id, name, code, version) VALUES (2, 'Военный билет','07',1);
INSERT INTO doc_type (id, name, code, version) VALUES (3, 'Иные документы','91',1);


INSERT INTO country_code (id, name, code, version) VALUES (1, 'РОССИЯ','643',1);
INSERT INTO country_code (id, name, code, version) VALUES (2, 'ИНДИЯ','356',1);
INSERT INTO country_code (id, name, code, version) VALUES (3, 'БЕЛАРУСЬ','112',1);
INSERT INTO country_code (id, name, code, version) VALUES (4, 'АВСТРАЛИЯ','036',1);
INSERT INTO country_code (id, name, code, version) VALUES (5, 'КАЗАХСТАН','398',1);



INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date, version) VALUES (1, 1, 1, 1, '1234 123456', '1990-02-20', 1);
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date, version) VALUES (2, 2, 1, 3, '123412123456', '1993-02-21', 1);
INSERT INTO user_docs (id, user_id, doc_type_id, country_code_id, doc_number, doc_date, version) VALUES (3, 3, 1, 1, '1234 6', '2015-05-03', 1);