INSERT INTO users(email, password, first_name, last_name)
VALUES ('bob@mail.com', '$2a$10$CtLjhhrX8/0Mzo56epKKneiOnMg8KY3LqjBgm.JHDMz5ci25Goi1S', 'Bob', 'Marly');

INSERT INTO users(email, password, first_name, last_name)
VALUES ('test@mail.com', '$2a$10$CtLjhhrX8/0Mzo56epKKneiOnMg8KY3LqjBgm.JHDMz5ci25Goi1S', 'Jack', 'Sparrow');

INSERT INTO clients(company, area, address) VALUES ('Sombra', 'IT', 'Lviv');
INSERT INTO clients(company, area, address) VALUES ('Sigma ', 'IT', 'Kharkiv');

INSERT INTO contacts(name, phone, email, client_id)
VALUES ('Joe', '(039)329-8293', 'joe@mail.com', 1);

INSERT INTO contacts(name, phone, email, client_id)
VALUES ('Silvia', '(938)992-1210', 'sillvi@mail.com', 2);

INSERT INTO tasks(description, status, due_date, contact_id)
VALUES('TASK1', 'OPEN', '2024-07-26', 2);

