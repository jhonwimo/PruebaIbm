BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `tbl_tarjetas` (
	`idtarjeta`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`numerotarjeta`	int ( 20 ) NOT NULL,
	`ccv`	int ( 4 ) NOT NULL,
	`tipotarjeta`	varchar ( 50 ) NOT NULL,
	`cedula`	int ( 20 ),
	FOREIGN KEY(`cedula`) REFERENCES `tbl_cliente`(`cedula`),
	CONSTRAINT `constraint_name` UNIQUE(`numerotarjeta`)
);
INSERT INTO `tbl_tarjetas` VALUES (1,1234,345,'master',123456);
CREATE TABLE IF NOT EXISTS `tbl_consumos` (
	`idconsumo`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`fecha`	date NOT NULL,
	`descripcion`	varchar ( 50 ) NOT NULL,
	`monto`	double ( 50 , 2 ) NOT NULL,
	`idtarjeta`	int ( 6 ),
	FOREIGN KEY(`idtarjeta`) REFERENCES `tbl_tarjetas`(`idtarjeta`)
);
INSERT INTO `tbl_consumos` VALUES (1,'ff','fff',333.0,1);
CREATE TABLE IF NOT EXISTS `tbl_cliente` (
	`idcliente`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`nombre`	varchar ( 50 ),
	`cedula`	int ( 20 ),
	`direccion`	varchar ( 50 ),
	`telefono`	int ( 20 ) NOT NULL,
	CONSTRAINT `constraint_name` UNIQUE(`cedula`)
);
INSERT INTO `tbl_cliente` VALUES (1,'jhon',123456,'cali',345678998);
INSERT INTO `tbl_cliente` VALUES (2,'duvan',NULL,'cali',1111);
INSERT INTO `tbl_cliente` VALUES (3,'duvan',1111,'cali',2222);
CREATE TABLE IF NOT EXISTS `tbl_asesores` (
	`idasesor`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`nombre`	varchar ( 50 ) NOT NULL,
	`especialidad`	varchar ( 50 ) NOT NULL
);
INSERT INTO `tbl_asesores` VALUES (1,'fredy','vender');
CREATE TABLE IF NOT EXISTS `contact` (
	`id`	integer NOT NULL,
	`email`	varchar ( 255 ),
	`name`	varchar ( 255 ),
	PRIMARY KEY(`id`)
);
INSERT INTO `contact` VALUES (3,'my_email@email.com','My Name');
INSERT INTO `contact` VALUES (24,'your_email@email.com','Your Name');
COMMIT;
