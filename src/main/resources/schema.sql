DROP TABLE IF EXISTS DETALLE;
DROP TABLE IF EXISTS MAESTRO;

CREATE TABLE MAESTRO (
                         ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                         DESCRIPCION VARCHAR(50) NOT NULL,
                         FECHA_ALTA DATE NOT NULL,

    -- Auditoría
                         created_at TIMESTAMP NOT NULL,
                         created_by VARCHAR(50) NOT NULL,
                         updated_at TIMESTAMP,
                         updated_by VARCHAR(50)
);

CREATE TABLE DETALLE (
                         ID BIGINT AUTO_INCREMENT PRIMARY KEY,
                         ID_MAESTRO BIGINT NOT NULL,
                         NOMBRE VARCHAR(50) NOT NULL,
                         FECHA DATE NOT NULL,
                         IMPORTE DECIMAL(14, 2) NOT NULL,

    -- Auditoría
                         created_at TIMESTAMP NOT NULL,
                         created_by VARCHAR(50) NOT NULL,
                         updated_at TIMESTAMP,
                         updated_by VARCHAR(50),

                         CONSTRAINT FK_DETALLE_MAESTRO FOREIGN KEY (ID_MAESTRO) REFERENCES MAESTRO(ID)
);

-- Datos iniciales para MAESTRO
INSERT INTO MAESTRO (DESCRIPCION, FECHA_ALTA, created_at, created_by)
VALUES ('Maestro A', '2024-01-01', CURRENT_TIMESTAMP, 'system');

INSERT INTO MAESTRO (DESCRIPCION, FECHA_ALTA, created_at, created_by)
VALUES ('Maestro B', '2024-03-01', CURRENT_TIMESTAMP, 'system');

-- Datos iniciales para DETALLE
INSERT INTO DETALLE (ID_MAESTRO, NOMBRE, FECHA, IMPORTE, created_at, created_by)
VALUES (1, 'Detalle 1', '2024-01-05', 100.00, CURRENT_TIMESTAMP, 'system');

INSERT INTO DETALLE (ID_MAESTRO, NOMBRE, FECHA, IMPORTE, created_at, created_by)
VALUES (1, 'Detalle 2', '2024-02-10', 250.50, CURRENT_TIMESTAMP, 'system');

INSERT INTO DETALLE (ID_MAESTRO, NOMBRE, FECHA, IMPORTE, created_at, created_by)
VALUES (2, 'Detalle A', '2024-03-10', 75.00, CURRENT_TIMESTAMP, 'system');
