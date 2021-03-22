CREATE TABLE actions(

                        id SERIAL PRIMARY KEY NOT NULL,
                        start_date date,
                        end_date date,
                        discount int

);

CREATE TABLE articles(

                         id SERIAL PRIMARY KEY NOT NULL,
                         price decimal(10,2),
                         id_part int,
                         id_action int,
                         CONSTRAINT articles_to_parts_fk
                             FOREIGN KEY(id_part)
                                 REFERENCES parts(id),
                         CONSTRAINT articles_to_actions_fk
                             FOREIGN KEY(id_action)
                                 REFERENCES actions(id)

);

INSERT INTO actions (start_date, end_date, discount)
VALUES ('2020-02-17', '2020-02-27', 50);

INSERT INTO actions (start_date, end_date, discount)
VALUES ('2020-02-28', '2020-04-01', 50);

INSERT INTO articles (price, id_part, id_action)
VALUES
(150.55, 1, 1),
(250.55, 2, 1),
(350.55, 3, 1),
(450.55, 4, 2),
(550.55, 5, 2),
(650.55, 6, 2);

CREATE TABLE users (
                      username VARCHAR(500) PRIMARY KEY NOT NULL,
                      password VARCHAR(500) NOT NULL,
                      enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
                        username VARCHAR(500) not null,
                        authority VARCHAR(500) not null,
                        constraint fk_authorities_users
                            foreign key(username)
                                references users(username)
);

INSERT INTO users (username, password, enabled) VALUES ('customer', '$2y$10$bVa3gYUMU1qRaKT3sZR/oepOWxwcviLikRilrGcradKvxUAsoDG1S', true);
INSERT INTO authorities (username, authority) VALUES ('customer', 'ROLE_CUSTOMER');

INSERT INTO users (username, password, enabled) VALUES ('sales', '$2y$10$oCjkkCMBAw4yIpVXm6ltwe1R/ACfz8tfEH5AEP45x9fIW8naAFEka', true);
INSERT INTO authorities (username, authority) VALUES ('sales', 'ROLE_SALES');

INSERT INTO users (username, password, enabled) VALUES ('warehouse', '$2y$10$MVAPdV83SVFwk0OC4aoqDeuM3FmgqORPc4qp9M1ELVMgv9tGeGkGe', true);
INSERT INTO authorities (username, authority) VALUES ('warehouse', 'ROLE_WAREHOUSE');