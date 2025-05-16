CREATE TABLE IF NOT EXISTS frases (
    id serial PRIMARY KEY,
    frase VARCHAR(255) NOT NULL,
    personagem VARCHAR(50) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    poster VARCHAR(255) NOT NULL
);