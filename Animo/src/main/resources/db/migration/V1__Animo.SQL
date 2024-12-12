CREATE TABLE users (
                       id INTEGER PRIMARY KEY,
                       nome VARCHAR,
                       email VARCHAR UNIQUE,
                       senha VARCHAR,
                       created_at TIMESTAMP
);

CREATE TABLE animes (
                        id SERIAL PRIMARY KEY,
                        titulo VARCHAR,
                        genero VARCHAR,
                        descricao TEXT,
                        created_at TIMESTAMP
);

CREATE TABLE episodios (
                           id INTEGER PRIMARY KEY,
                           numero INTEGER,
                           titulo VARCHAR,
                           duracao INTEGER,
                           urlStream VARCHAR,
                           anime_id INTEGER,
                           FOREIGN KEY (anime_id) REFERENCES animes(id)
);

CREATE TABLE favoritos (
                           usuario_id INTEGER,
                           anime_id INTEGER,
                           created_at TIMESTAMP,
                           FOREIGN KEY (usuario_id) REFERENCES users(id),
                           FOREIGN KEY (anime_id) REFERENCES animes(id)
);

CREATE TABLE reviews (
                         id INTEGER PRIMARY KEY,
                         nota INTEGER, -- Rating from 1 to 5
                         usuario_id INTEGER,
                         anime_id INTEGER,
                         created_at TIMESTAMP,
                         FOREIGN KEY (usuario_id) REFERENCES users(id),
                         FOREIGN KEY (anime_id) REFERENCES animes(id)
);