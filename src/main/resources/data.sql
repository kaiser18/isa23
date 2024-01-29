insert into addresses(id, city, country, number, street) values (100, 'Novi Sad', 'Serbia', '1', 'Zmaj Jovina');
insert into addresses(id, city, country, number, street) values (200, 'Novi Sad', 'Serbia', '1', 'Dunavska');
insert into addresses(id, city, country, number, street) values (300, 'Novi Sad', 'Serbia', '1', 'Strazilovska');
insert into addresses(id, city, country, number, street) values (400, 'Novi Sad', 'Serbia', '1', 'Puskinova');
insert into addresses(id, city, country, number, street) values (500, 'Novi Sad', 'Serbia', '1', 'Mise Dimitrijevica');
insert into addresses(id, city, country, number, street) values (600, 'Novi Sad', 'Serbia', '1', 'Zeleznicka');
insert into addresses(id, city, country, number, street) values (700, 'Novi Sad', 'Serbia', '1', 'Njegoseva');

insert into users(role, id, email, name, password, phone_number, surname, address_id) values ('Customer', 100, 'kaiserbreee@hotmail.com', 'Pera', '$2a$10$xhXx1uOCrlGvXxgxCL88TeYYIw1VGZv0NQ6KCgHF9JhEAB3im3Il.', '12345', 'Peric', 100);
insert into users(role, id, email, name, password, phone_number, surname, address_id) values ('CompanyAdmin', 200, 'purpendicular8@gmail.com', 'Milan', '$2a$10$xhXx1uOCrlGvXxgxCL88TeYYIw1VGZv0NQ6KCgHF9JhEAB3im3Il.', '12345', 'Milanovic', 200);
insert into users(role, id, email, name, password, phone_number, surname, address_id) values ('CompanyAdmin', 300, 'mihailoivic@protonmail.com', 'Milos', '$2a$10$xhXx1uOCrlGvXxgxCL88TeYYIw1VGZv0NQ6KCgHF9JhEAB3im3Il.', '12345', 'Milosevic', 300);
insert into users(role, id, email, name, password, phone_number, surname, address_id) values ('CompanyAdmin', 400, 'mihailo.ivic@uns.ac.rs', 'Jovan', '$2a$10$xhXx1uOCrlGvXxgxCL88TeYYIw1VGZv0NQ6KCgHF9JhEAB3im3Il.', '12345', 'Jovanovic', 400);

insert into companies(id, average_grade, description, name, address_id) values (100, 4.0, 'First company', 'MedEquip', 500);
insert into companies(id, average_grade, description, name, address_id) values (200, 4.6, 'Second company', 'MedCompany', 600);
insert into companies(id, average_grade, description, name, address_id) values (300, 3.5, 'Third company', 'EquipMed', 700);

insert into customers(enabled, profession, profession_info, verification_code, id) values (true, 'Profesija', 'Informacija', null, 100);

insert into companyadmins(id, company_id) values (200, 100);
insert into companyadmins(id, company_id) values (300, 200);
insert into companyadmins(id, company_id) values (400, 300);

insert into authority(id, name) values (1, 'ROLE_SYSADMIN');
insert into authority(id, name) values (2, 'ROLE_COMPANYADMIN');
insert into authority(id, name) values (3, 'ROLE_CUSTOMER');

insert into user_authority(user_id, authority_id) values (100, 3);
insert into user_authority(user_id, authority_id) values (200, 2);
insert into user_authority(user_id, authority_id) values (300, 2);
insert into user_authority(user_id, authority_id) values (400, 2);

insert into equipment(id, name) values (100, 'Mask');
insert into equipment(id, name) values (200, 'Gown');
insert into equipment(id, name) values (300, 'Syringe');
insert into equipment(id, name) values (400, 'Gloves');
insert into equipment(id, name) values (500, 'Catheter');

insert into storage(id, quantity, company_id, equipment_id) values (100, 200 , 100, 100);
insert into storage(id, quantity, company_id, equipment_id) values (200, 150 , 100, 200);
insert into storage(id, quantity, company_id, equipment_id) values (300, 220 , 100, 300);
insert into storage(id, quantity, company_id, equipment_id) values (400, 0 , 100, 400);

insert into storage(id, quantity, company_id, equipment_id) values (500, 50 , 200, 100);
insert into storage(id, quantity, company_id, equipment_id) values (600, 0 , 200, 200);
insert into storage(id, quantity, company_id, equipment_id) values (700, 250 , 200, 300);
insert into storage(id, quantity, company_id, equipment_id) values (800, 300 , 200, 400);
insert into storage(id, quantity, company_id, equipment_id) values (900, 100 , 200, 500);

insert into storage(id, quantity, company_id, equipment_id) values (1000, 300 , 300, 200);
insert into storage(id, quantity, company_id, equipment_id) values (1100, 25 , 300, 300);
insert into storage(id, quantity, company_id, equipment_id) values (1200, 0 , 300, 400);
insert into storage(id, quantity, company_id, equipment_id) values (1300, 0 , 300, 500);