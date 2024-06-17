DROP DATABASE IF EXISTS recintoZapata;
CREATE DATABASE IF NOT EXISTS recintoZapata;

USE recintoZapata;

CREATE TABLE Persona(
    id VARCHAR(255) PRIMARY KEY,
    nombre VARCHAR(50),
    paterno VARCHAR(50),
    materno VARCHAR(50),
    telefono VARCHAR(15),
    email VARCHAR(50)
);

CREATE TABLE Inventario(
    id VARCHAR(255) PRIMARY KEY,
    nombre VARCHAR(50),
    linkFoto VARCHAR(255),
    totalCantidad INT,
    cantidadActual INT,
    tipo VARCHAR(50),
    precioUnitario FLOAT
);

CREATE TABLE Direccion(
    id VARCHAR(255) PRIMARY KEY,
    calle VARCHAR(50),
    numero VARCHAR(10),
    colonia VARCHAR(50),
    cp VARCHAR(5),
    ciudad VARCHAR(100),
    id_persona VARCHAR(255),
    FOREIGN KEY (id_persona) REFERENCES Persona(id) ON DELETE CASCADE
);

CREATE TABLE Usuario(
    id VARCHAR(255) PRIMARY KEY,
    rol VARCHAR(50),
    usuario VARCHAR(50),
    contrasena VARCHAR(50),
    salt VARCHAR(255),
    id_persona VARCHAR(255),
    FOREIGN KEY (id_persona) REFERENCES Persona(id) ON DELETE CASCADE
);

CREATE TABLE Evento(
    id VARCHAR(255) PRIMARY KEY,
    tipo VARCHAR(50),
    cantidadPersona VARCHAR(50),
    color VARCHAR(50),
    precio FLOAT,
    horaInicio TIME,
    fecha DATE,
    numContrato VARCHAR(50),
    estado VARCHAR(50),
    id_persona VARCHAR(255),
    FOREIGN KEY (id_persona) REFERENCES Persona(id) ON DELETE CASCADE
);

-- Tabla para guardar la informacion de los eventos por si solicitan un cambio
CREATE TABLE InformacionAnterior(
    id VARCHAR(255) PRIMARY KEY,
    cantidadPersona VARCHAR(50),
    precio FLOAT,
    horaInicio TIME,
    fecha DATE,
    estatus VARCHAR(50),
    id_evento VARCHAR(255),
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TABLE Documentacion(
    id VARCHAR(255) PRIMARY KEY,
    linkContrato VARCHAR(255),
    linkReglamento VARCHAR(255),
    linkINE VARCHAR(255),
    id_evento VARCHAR(255),
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TABLE Comprobante(
    id VARCHAR(255) PRIMARY KEY,
    linkComprobante VARCHAR(255),
    id_evento VARCHAR(255),
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TABLE Multa(
    id VARCHAR(255) PRIMARY KEY,
    motivo VARCHAR(255),
    cantidad FLOAT,
    id_evento VARCHAR(255),
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TABLE HoraExtra(
    id VARCHAR(255) PRIMARY KEY,
    cantidad VARCHAR(255),
    precio VARCHAR(255),
    id_evento VARCHAR(255),
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TABLE PrestamoInventario(
    id VARCHAR(255) PRIMARY KEY,
    cantidad INT,
    id_invetario VARCHAR(255),
    id_evento VARCHAR(255),
    FOREIGN KEY (id_invetario) REFERENCES Inventario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TABLE Notificacion(
    id VARCHAR(255) PRIMARY KEY,
    titulo VARCHAR(255),
    mensaje VARCHAR(255),
    estatus VARCHAR(255),
    fecha VARCHAR(100),
    id_evento VARCHAR(255),
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TABLE AprobacionDocumentacion(
    ID VARCHAR(255) PRIMARY KEY,
    estatusContrato TEXT,
    estatusReglamento TEXT,
    estatusINE TEXT,
    estatusComprobante1 TEXT,
    estatusComprobante2 TEXT,
    id_evento VARCHAR(255),
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TABLE Administracion(
    id VARCHAR(255) PRIMARY KEY,
    concepto TEXT,
    tipo VARCHAR(50),
    fecha VARCHAR(50),
    cantidad double
);

CREATE TABLE CancelacionEvento(
    id VARCHAR(255) PRIMARY KEY,
    motivo TEXT,
    id_evento VARCHAR(255),
    FOREIGN KEY (id_evento) REFERENCES Evento(id) ON DELETE CASCADE
);

CREATE TRIGGER eliminarRegistroSinContenido
    BEFORE DELETE ON Documentacion
    FOR EACH ROW
BEGIN
    IF OLD.linkContrato IS NULL AND OLD.linkReglamento IS NULL AND OLD.linkINE IS NULL THEN
        DELETE FROM Documentacion WHERE id = OLD.id;
    END IF;
END;


