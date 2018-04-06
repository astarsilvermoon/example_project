CREATE TABLE IF NOT EXISTS account(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	is_identified BOOLEAN,
	login VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	name VARCHAR(50),
	activation_code VARCHAR(100) NOT NULL,
	is_activated BOOLEAN NOT NULL,
	version INTEGER NOT NULL
	
);

CREATE TABLE IF NOT EXISTS user (
    id INTEGER  PRIMARY KEY AUTO_INCREMENT,
    first_name    VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
	middle_name VARCHAR(50),
    position        VARCHAR(100)  NOT NULL,
	phone VARCHAR(25),
	office_id INTEGER NOT NULL,
	is_identified BOOLEAN NOT NULL,
	version INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS organization (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	full_name VARCHAR(250) NOT NULL,
	inn VARCHAR(12) NOT NULL,
	kpp VARCHAR(9),
	address VARCHAR(250) NOT NULL,
	phone VARCHAR(25),
	is_active BOOLEAN,
	version INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS office(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(150) NOT NULL,
	phone VARCHAR(25),
	is_active BOOLEAN,
	address VARCHAR(250) NOT NULL,
	org_id INTEGER NOT NULL,
	version INTEGER NOT NULL
);

CREATE INDEX IX_user_office_id ON user (office_id);
ALTER TABLE user ADD FOREIGN KEY (office_id) REFERENCES office(id) ON DELETE CASCADE;

CREATE INDEX IX_org_office_id ON office (org_id);
ALTER TABLE office ADD FOREIGN KEY (org_id) REFERENCES organization(id);


CREATE TABLE IF NOT EXISTS doc_type(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	code VARCHAR(20) NOT NULL,
	version INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS country_code(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	code VARCHAR(20) NOT NULL,
	version INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS user_docs(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	user_id INTEGER NOT NULL,
	doc_type_id INTEGER NOT NULL,
	doc_number VARCHAR(30) NOT NULL,
	doc_date DATE NOT NULL,
	country_code_id INTEGER NOT NULL,
	version INTEGER NOT NULL
	
);

CREATE INDEX IX_user_id ON user_docs (user_id);
ALTER TABLE user_docs ADD FOREIGN KEY (user_id) REFERENCES user(id);

CREATE INDEX IX_doc_type_id ON user_docs (doc_type_id);
ALTER TABLE user_docs ADD FOREIGN KEY (doc_type_id) REFERENCES doc_type(id);

CREATE INDEX IX_country_code_id ON user_docs (country_code_id);
ALTER TABLE user_docs ADD FOREIGN KEY (country_code_id) REFERENCES country_code(id);

