# Bibliotecatest

#Desarrollo creado con la base de datos de MySQL
#Se agrega info para crear la base de datos:

CREATE DATABASE biblioteca;
USE biblioteca;
CREATE TABLE libros (autor VARCHAR(50)  PRIMARY KEY, titulo VARCHAR(20), anopublicacion VARCHAR(20), numedicion VARCHAR(20),  resumen VARCHAR(100));
