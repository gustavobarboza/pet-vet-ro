INSERT INTO usuario(id, usuario, senha, nome, tipo)
VALUES(0, 'gustavo', '12345', 'Gustavo Barboza', 'ADMIN')
ON CONFLICT(usuario)
DO NOTHING;
