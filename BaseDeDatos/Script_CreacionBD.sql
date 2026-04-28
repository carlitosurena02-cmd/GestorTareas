CREATE TABLE Rol (
	IdRol serial PRIMARY KEY NOT NULL,
	Permisos varchar(50),
	Descripcion text
);

CREATE TABLE Estado(
	IdEstado serial PRIMARY KEY NOT NULL,
	Descripcion varchar(50)
);

CREATE TABLE Prioridad(
	IdPrioridad serial PRIMARY KEY NOT NULL,
	Descripcion varchar(50)
);

CREATE TABLE Etiqueta(
	IdEtiqueta serial PRIMARY KEY NOT NULL,
	Descripcion varchar(50)
);

CREATE TABLE Usuario(
	IdUsuario serial PRIMARY KEY NOT NULL,
	Nombre varchar(50),
	ApellidoP varchar(50),
	ApellidoM varchar(50),
	Email varchar(100),
	Password_ varchar(255),
	DOB date,
	FechaRegistro date,
	Rol int NOT NULL REFERENCES Rol(IdRol),
	Estado int NOT NULL REFERENCES Estado(IdEstado)
);

CREATE TABLE Proyecto(
	IdProyecto serial NOT NULL PRIMARY KEY,
	Nombre varchar(100),
	Descripcion varchar(100),
	FechaInicio date,
	FechaFin date,
	Estado int NOT NULL REFERENCES Estado(IdEstado)
);

CREATE TABLE Tarea(
	IdTarea serial NOT NULL PRIMARY KEY,
	Nombre varchar(50),
	Descripcion varchar(50),
	FechaInicio date,
	FechaLim date,
	Estado int NOT NULL REFERENCES Estado(IdEstado),
	Prioridad int NOT NULL REFERENCES Prioridad(IdPrioridad),
	Etiqueta int NOT NULL REFERENCES Etiqueta(IdEtiqueta),
	Proyecto int NOT NULL REFERENCES Proyecto(IdProyecto)
);

CREATE TABLE Comentario(
	IdComentario serial NOT NULL PRIMARY KEY,
	Contenido text,
	Usuario int NOT NULL REFERENCES Usuario(IdUsuario),
	Tarea int NOT NULL REFERENCES Tarea(IdTarea),
	Estado int NOT NULL REFERENCES Estado(IdEstado)
);

CREATE TABLE UsuarioTarea(
	Usuario int NOT NULL REFERENCES Usuario(IdUsuario),
	Tarea int NOT NULL REFERENCES Tarea(IdTarea),
	FechaAsignacion date,
	PRIMARY KEY (Usuario, Tarea)
);

CREATE TABLE UsuarioProyecto(
	Usuario int NOT NULL REFERENCES Usuario(IdUsuario),
	Proyecto int NOT NULL REFERENCES Proyecto(IdProyecto),
	RolProyecto int NOT NULL REFERENCES Rol(IdRol),
	Estado int NOT NULL REFERENCES Estado(IdEstado),
	FechaUnion date,
	PRIMARY KEY (Usuario, Proyecto)
);





