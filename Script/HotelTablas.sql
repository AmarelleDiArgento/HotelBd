CREATE DATABASE IF NOT EXISTS Hotel DEFAULT CHARACTER SET utf8 ;
USE Hotel;

CREATE TABLE IF NOT EXISTS Hotel.rol (
  id INT NOT NULL AUTO_INCREMENT primary key,
  nombre VARCHAR(45) NOT NULL)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;
  
CREATE TABLE IF NOT EXISTS Hotel.usuario (
  cedula VARCHAR(10) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  pass VARCHAR(255) NOT NULL,
  Rol_id INT NOT NULL,
  PRIMARY KEY (cedula, Rol_id),
  INDEX fk_Usuario_Rol1_idx (Rol_id ASC),
  CONSTRAINT fk_Usuario_Rol1 FOREIGN KEY (Rol_id) REFERENCES Hotel.rol (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Hotel.permiso (
  id INT NOT NULL AUTO_INCREMENT,
  modulo VARCHAR(45) NOT NULL,
  tabla VARCHAR(45) NOT NULL,
  url VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Hotel.asignaPer (
  Rol_id INT NOT NULL,
  permiso_id INT NOT NULL,
  leer boolean NOT NULL,
  nuevo boolean not null,
  modificar boolean not null,
  eliminar boolean not null,
  PRIMARY KEY (Rol_id, permiso_id),
    INDEX fk_permiso_has_Rol_Rol1_idx (Rol_id ASC),
    INDEX fk_permiso_has_Rol_permiso1_idx (permiso_id ASC),
  CONSTRAINT fk_permiso_has_Rol_permiso1 FOREIGN KEY (permiso_id) REFERENCES Hotel.permiso (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_permiso_has_Rol_Rol1 FOREIGN KEY (Rol_id) REFERENCES Hotel.rol (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Hotel.habita (
  numero CHAR(4) NOT NULL PRIMARY KEY,
  tarifa FLOAT(10,2) NOT NULL)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS Hotel.servicio (
  id INT(11) NOT NULL AUTO_INCREMENT primary key,
  nombre VARCHAR(50) NOT NULL,
  tarifa INT not null)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS Hotel.reserva (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  fecha_ingreso DATE NOT NULL,
  fecha_egreso DATE NOT NULL,
  usuario_cedula VARCHAR(10) NOT NULL,
  num_personas INT(3),
  PRIMARY KEY (codigo, usuario_cedula),
  INDEX fk_reserva_Usuario1_cedulax (usuario_cedula ASC),
  CONSTRAINT fk_reserva_Usuario1 FOREIGN KEY (usuario_cedula) REFERENCES Hotel.usuario (cedula)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS Hotel.asigna (
  numero_hab CHAR(4) NOT NULL,
  codigo_res INT(11) NOT NULL,
  num_ad INT(2) NOT NULL,
  num_ni INT(2) NOT NULL,
  INDEX fk_asigna_habita (numero_hab ASC),
  INDEX fk_asigna_reserva (codigo_res ASC),
  CONSTRAINT fk_asigna_habita FOREIGN KEY (numero_hab) REFERENCES Hotel.habita (numero),
  CONSTRAINT fk_asigna_reserva FOREIGN KEY (codigo_res) REFERENCES Hotel.reserva (codigo))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS Hotel.consumo (
  id_cons int NOT NULL AUTO_INCREMENT primary key,
  numero_hab CHAR(4) NOT NULL,
  codigo_res INT(11) NOT NULL,
  id_servicio INT(11) NOT NULL,
  fecha_hora DATETIME NOT NULL,
  INDEX fk_consumo__habita (numero_hab ASC),
  INDEX fk_consumo__reserva (codigo_res ASC),
  INDEX fk_consumo_servicio (id_servicio ASC),
  CONSTRAINT fk_consumo__habita FOREIGN KEY (numero_hab) REFERENCES Hotel.habita (numero),
  CONSTRAINT fk_consumo__reserva FOREIGN KEY (codigo_res) REFERENCES Hotel.reserva (codigo),
  CONSTRAINT fk_consumo_servicio FOREIGN KEY (id_servicio) REFERENCES Hotel.servicio (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS Hotel.reserva_cierre (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  fecha_cierre DATE NOT NULL,
  tipo_pago CHAR(3) NULL DEFAULT NULL,
  monto_total FLOAT(10,2) NOT NULL,
  PRIMARY KEY (codigo),
  CONSTRAINT fk_reserva_cierre__reserva FOREIGN KEY (codigo) REFERENCES Hotel.reserva (codigo))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;