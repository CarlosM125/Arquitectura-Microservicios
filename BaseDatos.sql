-- Creación de la base de datos en PostgreSQL
CREATE DATABASE BancoDB;

-- Conectar a la base de datos
\c BancoDB;

-- Tabla Persona
CREATE TABLE IF NOT EXISTS Persona (
    ID SERIAL PRIMARY KEY,
    NOMBRE VARCHAR(70) NOT NULL,
    GENERO VARCHAR(10),
    EDAD INT,
    DIRECCION VARCHAR(100),
    TELEFONO VARCHAR(30)
);

-- Tabla Cliente (hereda de Persona)
CREATE TABLE IF NOT EXISTS Cliente (
    CLIENTE_ID INT PRIMARY KEY REFERENCES Persona(ID) ON DELETE CASCADE,
    PASSWORD VARCHAR(100) NOT NULL,
    ESTADO BOOLEAN NOT NULL
);

-- Tabla Cuenta
CREATE TABLE IF NOT EXISTS Cuenta (
    NUMERO_CUENTA SERIAL PRIMARY KEY,
    CLIENTE_ID INT NOT NULL REFERENCES Cliente(CLIENTE_ID) ON DELETE CASCADE,
    TIPO_CUENTA VARCHAR(20) NOT NULL,
    SALDO_INICIAL DOUBLE PRECISION NOT NULL,
    ESTADO BOOLEAN NOT NULL
);

-- Tabla Movimiento
CREATE TABLE IF NOT EXISTS Movimiento (
    ID_MOVIMIENTO SERIAL PRIMARY KEY,
    FECHA TIMESTAMP NOT NULL,
    TIPO_MOVIMIENTO VARCHAR(20) NOT NULL,
    VALOR DOUBLE PRECISION NOT NULL,
    SALDO DOUBLE PRECISION NOT NULL,
    NUMERO_CUENTA INT NOT NULL REFERENCES Cuenta(NUMERO_CUENTA) ON DELETE CASCADE
);
