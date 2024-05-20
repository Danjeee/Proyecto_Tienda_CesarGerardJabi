-- Borrar la base de datos si existe
DROP DATABASE IF EXISTS Tienda_Ropa;

-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS Tienda_Ropa;

--  Seleccionar la base de datos.
USE Tienda_Ropa;

-- Crear la tabla DEPARTAMENTO
CREATE TABLE DEPARTAMENTO (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL unique key
);
-- Crear la tabla DESCUENTOS
CREATE TABLE DESCUENTOS(
	descuento VARCHAR(50) PRIMARY KEY,
    cant INT UNSIGNED,
    freeShip BOOLEAN
);
-- Crear table cuentas_pago
CREATE TABLE CUENTAS_PAGO(
	id INT UNSIGNED auto_increment primary key,
    cuenta VARCHAR(20),
    fecha varchar(5),
    constraint fecha_check check(fecha like("[0-9][0-9]/[0-9][0-9]"))
);


-- Crear la tabla EMPLEADO
CREATE TABLE EMPLEADO (
    DNI CHAR(9) PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    apellidos VARCHAR(50) not null,
    telefono CHAR(9),
    f_nacimiento DATE,
    direccion VARCHAR(255),
    email VARCHAR(100) not null unique key,
    activo boolean default true,
    tiene_privilegios BOOLEAN,
    pass text not null,
    dpto INT not null,
    FOREIGN KEY (dpto) REFERENCES DEPARTAMENTO(codigo)
);

-- Crear la tabla METODO_PAGO
CREATE TABLE METODO_PAGO (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL unique key
);

-- Crear la tabla CLIENTE
CREATE TABLE CLIENTE (
    DNI CHAR(9) PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    apellidos VARCHAR(50) not null,
    telefono CHAR(9),
    f_nacimiento DATE,
    direccion VARCHAR(255),
    email VARCHAR(100) NOT NULL unique key,
    pass text not null,
    saldo_cuenta FLOAT NOT NULL,
    num_pedidos INT, -- campo calculado
    dir_envio VARCHAR(255) NOT NULL,
    tarjeta_fidelizacion BOOLEAN,
    activo boolean default true,
    m_pago INT NOT NULL,
    FOREIGN KEY (m_pago) REFERENCES METODO_PAGO(codigo)
);

-- Crear tabla metodos_pago_cliente
Create table METODOS_PAGO_CLIENTE(
	id int unsigned auto_increment primary key,
	DNI_cliente CHAR(9) NOT NULL,
    id_cuenta_pago INT UNSIGNED,
    FOREIGN KEY (DNI_cliente) REFERENCES CLIENTE(DNI),
    FOREIGN KEY (id_cuenta_pago) REFERENCES CUENTAS_PAGO(id)
);

-- Crear la tabla PEDIDO
CREATE TABLE PEDIDO (
    numero INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    dir_envio VARCHAR(255) NOT NULL,
    estado ENUM("En proceso","Completado", "Cancelado") NOT NULL,
    DNI_cliente CHAR(9) NOT NULL,
    FOREIGN KEY (DNI_cliente) REFERENCES CLIENTE(DNI)
);

-- Crear la tabla MATERIAL
CREATE TABLE MATERIAL (
    codigo INT AUTO_INCREMENT PRIMARY KEY,
    denominacion VARCHAR(50) NOT NULL unique key
);

-- Crear la tabla ARTICULO
CREATE TABLE ARTICULO (
    cod_art int AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    marca VARCHAR(255),
    descripcion TEXT,
    activo boolean default true,
    imagen VARCHAR(25), -- Se almacenará el nombre del archivo
    material INT,
    FOREIGN KEY (material) REFERENCES MATERIAL(codigo)
);

-- Crear la tabla ROPA
CREATE TABLE ROPA (
    cod_art int PRIMARY KEY,
    talla VARCHAR(10),
    color VARCHAR(50),
    tipo_cierre VARCHAR(50),
    impermeable BOOLEAN,
    tipo_manga VARCHAR(50),
    estampada BOOLEAN,
    tipo_pantalon VARCHAR(50),
    tien_bolsillos BOOLEAN,
    tipo_ropa ENUM ("Chaqueta", "Camisa", "Pantalon") NOT NULL,
    FOREIGN KEY (cod_art) REFERENCES ARTICULO(cod_art)
);

-- Crear la tabla ACCESORIO
CREATE TABLE ACCESORIO (
    cod_art int PRIMARY KEY,
    estilo VARCHAR(50),
    personalizado BOOLEAN,
    tipo_cierre VARCHAR(50),
    capacidad INT,
    tipo_suela VARCHAR(255),
    talla INT,
    tipo_accesorio ENUM ("Zapatos", "Bolso") NOT NULL,
    FOREIGN KEY (cod_art) REFERENCES ARTICULO(cod_art)
);

-- Crear la tabla LINEA_PEDIDO
CREATE TABLE LINEA_PEDIDO (
    cod_art int,
    num_pedido INT,
    cantidad INT not null,
    PRIMARY KEY (cod_art, num_pedido),
    FOREIGN KEY (cod_art) REFERENCES ARTICULO(cod_art),
    FOREIGN KEY (num_pedido) REFERENCES PEDIDO (numero)
);