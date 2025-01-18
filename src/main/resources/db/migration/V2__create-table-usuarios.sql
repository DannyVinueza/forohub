CREATE TABLE usuarios (
                          id SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          contrasena VARCHAR(255) NOT NULL,
                          perfil_id INT,
                          FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
);
