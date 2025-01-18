CREATE TABLE topicos (
         id SERIAL PRIMARY KEY,
         titulo VARCHAR(255) NOT NULL,
         mensaje TEXT NOT NULL,
         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
         status VARCHAR(20) NOT NULL,
         autor_id INT,
         curso_id INT,
         FOREIGN KEY (autor_id) REFERENCES usuarios(id),
         FOREIGN KEY (curso_id) REFERENCES cursos(id)
);
