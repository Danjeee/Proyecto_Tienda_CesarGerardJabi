drop database if exists Proyecto_Tienda_CesarGerardJavier;
create database Proyecto_Tienda_CesarGerardJavier;
use Proyecto_Tienda_CesarGerardJavier;
create table Metodo_Pago(
	codigo VARCHAR(4) PRIMARY KEY,
    descripcion VARCHAR(100)
);
create table Cliente(
	/*Comun*/
	dni VARCHAR(9) PRIMARY KEY,
    CONSTRAINT check_dni
		check(dni = "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]"),
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50),
    telefono INT(9) UNSIGNED,
    f_nacimiento DATE,
    direccion VARCHAR(100),
    e_mail VARCHAR(30),
    CONSTRAINT check_mail
		CHECK(e_mail like("%@%.%")),
	/*Propio*/
	tarjeta_fidelizacion VARCHAR(9),
    cuenta VARCHAR(50),
    num_pedidos INT UNSIGNED,
    dir_envio VARCHAR(50),
    metodo_pago VARCHAR(4),
    FOREIGN KEY (metodo_pago) REFERENCES Metodo_Pago(codigo)
);
create table Departamento(
	codigo VARCHAR(4) PRIMARY KEY,
    nombre VARCHAR(20)
);
create table Administrador(
	/*Comun*/
	dni VARCHAR(9) PRIMARY KEY,
    CONSTRAINT check_dni
		check(dni = "[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]"),
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50),
    telefono INT(9) UNSIGNED,
    f_nacimiento DATE,
    direccion VARCHAR(100),
    e_mail VARCHAR(30),
    CONSTRAINT check_mail
		CHECK(e_mail like("%@%.%")),
	/*Propio*/
	nivel_acceso VARCHAR(20),
    tiene_privilegios BOOLEAN,
    rol VARCHAR(20),
    departamento VARCHAR(4),
    FOREIGN KEY (departamento) REFERENCES Departamento(codigo)
);
create table Material(
	codigo VARCHAR(4) PRIMARY KEY,
    descripcion VARCHAR(50)
);
create table Articulo(
	codigo VARCHAR(4) PRIMARY KEY,
    nombre VARCHAR(50),
    precio FLOAT,
    marca VARCHAR(50),
    descripcion VARCHAR(100),
	material VARCHAR(4),
    FOREIGN KEY (material) REFERENCES Material(codigo)
);
create table Chaqueta(
	codigo VARCHAR(4) PRIMARY KEY,
    talla VARCHAR(5),
    color VARCHAR(15),
    tipo_cierre VARCHAR(10),
    impermeable BOOLEAN,
    FOREIGN KEY (codigo) REFERENCES Articulo(codigo)
);
create table Camisa(
	codigo VARCHAR(4) PRIMARY KEY,
    talla VARCHAR(5),
    color VARCHAR(15),
    tipo_cierre VARCHAR(10),
    tipo_manga VARCHAR(10),
    estampada VARCHAR(10),
    FOREIGN KEY (codigo) REFERENCES Articulo(codigo)
);
create table Pantalon(
	codigo VARCHAR(4) PRIMARY KEY,
    talla VARCHAR(5),
    color VARCHAR(15),
    tipo_cierre VARCHAR(10),
    tiene_bolsillos BOOLEAN,
    tipo_pantalon VARCHAR(10),
    FOREIGN KEY (codigo) REFERENCES Articulo(codigo)
);
create table Bolso(
	codigo VARCHAR(4) PRIMARY KEY,
    estilo VARCHAR(15),
    personalizado BOOLEAN,
    tipo_cierre VARCHAR(10),
    capacidad DOUBLE,
    FOREIGN KEY (codigo) REFERENCES Articulo(codigo)
);
create table Zapatos(
	codigo VARCHAR(4) PRIMARY KEY,
    estilo VARCHAR(15),
    personalizado BOOLEAN,
    tipo_suela VARCHAR(10),
    talla INT UNSIGNED,
    FOREIGN KEY (codigo) REFERENCES Articulo(codigo)
);
create table pedido(
	numero INT(18) ZEROFILL UNSIGNED PRIMARY KEY ,
    fecha DATE,
    dir_envio VARCHAR(50),
    estado VARCHAR(10),
    CONSTRAINT check_estado
		CHECK(estado in('Procesando', 'Enviado', 'En reparto', 'Entregado', 'En oficina')),
	cantidad INT UNSIGNED,
    articulo VARCHAR(4),
    FOREIGN KEY (articulo) REFERENCES Articulo(codigo),
    cliente VARCHAR(9),
    FOREIGN KEY (cliente) REFERENCES Cliente(dni)
);