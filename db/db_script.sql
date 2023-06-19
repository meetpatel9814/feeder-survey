CREATE TABLE IF NOT EXISTS feeder(
   id SERIAL PRIMARY KEY,
   name VARCHAR(200) NULL
);

CREATE TABLE IF NOT EXISTS substation(
   id SERIAL  PRIMARY KEY,
   name VARCHAR(200) NULL
  );
INSERT INTO feeder (name) VALUES ( 'F1');
INSERT INTO feeder (name) VALUES ( 'F2');
INSERT INTO feeder (name) VALUES ( 'F3');
INSERT INTO feeder (name) VALUES ( 'F4');
INSERT INTO substation (name) VALUES ('Vijapur'), ('Anadpura'), ('Falu');

INSERT INTO feeder_substation_mapping VALUES (1,1), (1,2), (2,1),(2,3),(3,1),(4,2),(4,3);
CREATE TABLE IF NOT EXISTS feeder_substation_mapping (
	feeder_id integer NOT NULL,
	substation_id integer NOT NULL,
	CONSTRAINT "feeder_substation_mapping_fk_feeder_id" FOREIGN KEY (feeder_id) REFERENCES feeder(id),
	CONSTRAINT "feeder_substation_mapping_fk_substation_id" FOREIGN KEY (substation_id) REFERENCES substation(id)
);

CREATE TABLE IF NOT EXISTS feeder_detail(
   id SERIAL PRIMARY KEY,
   feeder_id integer NULL,
   substation_id integer NULL,
   transformer_location_name VARCHAR(200) NULL,
   latitude VARCHAR(200) NULL,
   longitude VARCHAR(200) NULL,
   existing_tc_capacity VARCHAR(20) NULL,
   propose_tc_capacity VARCHAR(20) NULL,
   propose_RMU_type VARCHAR(200) NULL,
   remarks TEXT NULL,
   CONSTRAINT "feeder_detail_fk_feeder_id" FOREIGN KEY (feeder_id) REFERENCES feeder(id),
   CONSTRAINT "feeder_detail_fk_substation_id" FOREIGN KEY (substation_id) REFERENCES substation(id)
);

CREATE TABLE IF NOT EXISTS fidder_image_details(
	id SERIAL PRIMARY KEY,
	fidder_id BIGINT NOT NULL,
	image bytea NULL,
	
	CONSTRAINT "fidder_image_details_fk_fidder_id" FOREIGN KEY (fidder_id) REFERENCES fidder(id)
)
CREATE TABLE IF NOT EXISTS user_detail(
   id SERIAL  PRIMARY KEY,
   user_name VARCHAR(200) NOT NULL,
   pwd VARCHAR(200) NOT NULL
  );
CREATE TABLE IF NOT EXISTS role_detail(
   id SERIAL  PRIMARY KEY,
   name VARCHAR(200) NOT NULL
  );
CREATE TABLE IF NOT EXISTS user_role (
	user_id integer NOT NULL,
	role_id integer NOT NULL,
	CONSTRAINT "user_role_fk_user_id" FOREIGN KEY (user_id) REFERENCES user_detail(id),
	CONSTRAINT "user_role_fk_role_id" FOREIGN KEY (role_id) REFERENCES role_detail(id)
)
INSERT INTO user_detail (user_name, pwd) VALUES ('jasmin', '$2a$08$whXSWP7KZCzLjN5ZWR5rseoHMc8KP4K/.V3hIbokm/NKZ0Hizgb/q');
INSERT INTO role_detail (name) VALUES ('ADMIN');
INSERT INTO user_role values(1,1);
