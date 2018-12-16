-- noinspection SqlNoDataSourceInspectionForFile

DROP DATABASE IF EXISTS bb_db;
CREATE DATABASE bb_db;
USE bb_db;


-- Data model layout works.
-- Need to develop a separate script to pass in table data.

	CREATE TABLE Grain (
		ID INT PRIMARY KEY AUTO_INCREMENT,
		name VARCHAR(40),
		lovibond VARCHAR(2.2)
	);

	CREATE TABLE Hop (
		ID INT PRIMARY KEY AUTO_INCREMENT,
		HopName VARCHAR(10),
		AlphaAcid VARCHAR(2.2),
		Flavor VARCHAR(225)
	);

	CREATE TABLE Yeast (
		ID INT PRIMARY KEY AUTO_INCREMENT,
		YeastName VARCHAR(25),
		Style VARCHAR(12),
		Attenuation VARCHAR(4),
		Floculation VARCHAR(4)

	);

	CREATE TABLE Recipe (
		ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
		Recipe_Name VARCHAR(100),
		STYLE VARCHAR(12),
		IBU INT(3.2),
		ABV INT(2.1),
		SRM INT(2.1),
		Grain1_ID INT,
		Grain2_ID INT,
		Grain3_ID INT,
		Grain4_ID INT,
		Grain5_ID INT,
		Hop1_ID INT,
		Hop2_ID INT,
		Hop3_ID INT,
		Hop4_ID INT,
		Hop5_ID INT,
		Yeast_ID INT,

		FOREIGN KEY (Grain1_ID)
			REFERENCES Grain (ID),
		FOREIGN KEY (Grain2_ID)
			REFERENCES Grain (ID),
		FOREIGN KEY (Grain3_ID)
			REFERENCES Grain (ID),
		FOREIGN KEY (Grain4_ID)
			REFERENCES Grain (ID),
		FOREIGN KEY (Grain5_ID)
			REFERENCES Grain (ID),
		FOREIGN KEY (Hop1_ID)
			REFERENCES Hop (ID),
		FOREIGN KEY (Hop2_ID)
			REFERENCES Hop (ID),
		FOREIGN KEY (Hop3_ID)
			REFERENCES Hop (ID),
		FOREIGN KEY (Hop4_ID)
			REFERENCES Hop (ID),
			FOREIGN KEY (Hop5_ID)
			REFERENCES Hop (ID),
		FOREIGN KEY (Yeast_ID)
			REFERENCES Yeast (ID)
	);



CREATE USER bb_db_user@localhost IDENTIFIED BY 'sesame';
GRANT SELECT, INSERT, DELETE, UPDATE ON bb_db.* TO bb_db_user@localhost;