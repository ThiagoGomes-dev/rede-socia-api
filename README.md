# CRUD de Usuários e Postagens

Este projeto é uma API Spring Boot que implementa:
- CRUD de usuários
- CRUD de postagens (relacionadas a usuários)

## Como rodar o projeto localmente

### 1. Pré-requisitos
- Java 17 ou superior instalado
- Maven instalado
- Docker instalado (para rodar o MySQL facilmente)

### 2. Subindo o MySQL com Docker
Execute o comando abaixo para subir um container MySQL:

```bash
docker run --name mysql-rede-social -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=rede_social -p 3306:3306 -d mysql:8.0
```
Esse comando irá criar o banco `rede_social` com senha root `123456`.

### 3. Criação das tabelas
Após subir o MySQL, execute os comandos abaixo para criar as tabelas necessárias:

```sql
CREATE TABLE usuarios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(50) NOT NULL,
    usuario VARCHAR(100) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE postagens (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    conteudo TEXT NOT NULL,
    usuario_id BIGINT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
```

### 4. Configuração do projeto
No arquivo `src/main/resources/application.properties`, configure:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rede_social
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.hibernate.ddl-auto=update
```

### 5. Instale as dependências
```bash
mvn clean install
```

### 6. Rode o projeto
```bash
mvn spring-boot:run
```
O backend estará disponível em `http://localhost:8080`.

### 7. Acesse o Swagger
Abra `http://localhost:8080/swagger-ui.html` ou `http://localhost:8080/swagger-ui/index.html` para testar os endpoints.

## Endpoints principais

- `/api/usuarios` - CRUD de usuários
- `/api/postagens` - CRUD de postagens
- `/api/postagens/usuario/{usuarioId}` - Listar postagens de um usuário
- `/api/postagens/usuario/{usuarioId}/{postagemId}` - Editar postagem de um usuário

## Observações
- Não há autenticação ou restrição de acesso: todos os endpoints estão liberados para facilitar testes e avaliação.
- O projeto não possui mais funcionalidades relacionadas a pedidos, apenas usuários e postagens.

---
