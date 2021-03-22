CREATE TABLE brands (

	id SERIAL NOT NULL PRIMARY KEY,
	brand_name VARCHAR(100) NOT NULL CHECK (brand_name <> '')

);

CREATE TABLE cars (

    id SERIAL NOT NULL PRIMARY KEY,
	model_name VARCHAR(400) NOT NULL CHECK (model_name <> ''),
    id_brand int,
	CONSTRAINT car_foreign_key
	    FOREIGN KEY (id_brand)
	        REFERENCES brands(id)
);

CREATE TABLE parts (

    id SERIAL NOT NULL PRIMARY KEY,
    part_name VARCHAR(400) NOT NULL CHECK (part_name <> ''),
	date_manufactured date,
    id_car int,
	CONSTRAINT part_foreign_key
	    FOREIGN KEY (id_car)
	        REFERENCES cars(id)
);

-- BRAND --
INSERT INTO brands (brand_name)
VALUES ('Peugeot'),
       ('Porsche'),
       ('Audi'),
       ('BMW');


-- -------------------------------------------------------

-- CAR --

-- Peugeot --
INSERT INTO cars (model_name, id_brand)
VALUES ('Rifter', 1),
       ('Partner', 1),
       ('Pick Up', 1),
       ('Pars', 1);

-- Porsche --
INSERT INTO cars (model_name, id_brand)
VALUES ('Taycan', 2),
       ('911 Turbo', 2),
       ('Carrera', 2),
       ('Cayenne', 2);

-- Audi --
INSERT INTO cars (model_name, id_brand)
VALUES ('Quattro', 3),
       ('Rosemeyer', 3),
       ('Spyder', 3),
       ('A5', 3);

-- BMW --
INSERT INTO cars (model_name, id_brand)
VALUES ('X2', 4),
       ('X3', 4),
       ('X4', 4),
       ('X5', 4);

-- -------------------------------------------------------

-- PART

INSERT INTO parts (part_name, date_manufactured, id_car)
VALUES ('Bumper', '2010-08-01', 1),
       ('Rocker', '2011-07-02', 1),
       ('Spoiler', '2005-06-03', 1),
       ('Rims', '2000-05-04', 2),
       ('Door latch', '2009-04-01', 3),
       ('Glass', '2008-03-01', 4),
       ('Sunroof', '2007-02-01', 5),
       ('Window motor', '2005-01-01', 6),
       ('Window regulator', '2006-12-01', 7),
       ('Window seal', '2004-11-01', 8),
       ('Antenna assembly', '2005-10-01', 9),
       ('Speaker', '2003-09-01', 10),
       ('Subwoofer', '2011-08-01', 11),
       ('Backup camera', '2012-07-01', 12),
       ('Dashcam', '2013-06-01', 13),
       ('Alternator', '2014-05-01', 14),
       ('Battery', '2015-04-01', 15),
       ('Voltage regulator', '2016-03-01', 16),
       ('Hydrometer', '2017-02-01', 1),
       ('Tire pressure gauge', '2018-01-01', 1),
       ('Sparking cable', '2018-12-01', 1),
       ('Distributor', '2019-11-01', 1),
       ('Ignition coil', '2020-10-02', 2),
       ('Spark plug', '2019-09-11', 3),
       ('Fog light', '2018-08-21', 4),
       ('Airbag sensors', '2017-07-11', 5),
       ('Fuel level sensor', '2016-06-01', 6),
       ('MAP sensor', '2015-05-01', 7),
       ('Mass airflow sensor', '2014-04-01', 8),
       ('Oil level sensor', '2013-03-01', 9),
       ('Oil pressure sensor', '2012-02-01', 10),
       ('Ammeter', '2011-01-01', 11);