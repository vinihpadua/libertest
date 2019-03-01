SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema libertestdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `libertestdb` DEFAULT CHARACTER SET utf8 ;
USE `libertestdb` ;

-- -----------------------------------------------------
-- Table `libertestdb`.`receitas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libertestdb`.`receitas` (
  NOME VARCHAR(255) NOT NULL PRIMARY KEY,
  MODO_DE_PREPARO VARCHAR(255) NOT NULL)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `libertestdb`.`ingredientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libertestdb`.`ingredientes` (
  ID INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NOME_RECEITA VARCHAR(255) NOT NULL,
  INGREDIENTE VARCHAR(255) NOT NULL,
  CONSTRAINT FK_ING_RECEITA FOREIGN KEY (NOME_RECEITA) REFERENCES `libertestdb`.`receitas` (NOME))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `libertestdb`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libertestdb`.`categorias` (
  ID INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NOME_RECEITA VARCHAR(255) NOT NULL,
  CATEGORIA VARCHAR(255) NOT NULL,
  CONSTRAINT FK_CAT_RECEITA FOREIGN KEY (NOME_RECEITA) REFERENCES `libertestdb`.`receitas` (NOME))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `libertestdb`.`metadados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `libertestdb`.`metadados` (
  ID INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  NOME_RECEITA VARCHAR(255) NOT NULL,
  TEMPO_PREP INT(10),
  REND_PORCAO INT(10),
  OBSERVACOES VARCHAR(255) NOT NULL,
  CONSTRAINT FK_META_RECEITA FOREIGN KEY (NOME_RECEITA) REFERENCES `libertestdb`.`receitas` (NOME))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
