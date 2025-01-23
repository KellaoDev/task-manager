# Task Manager
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/KellaoDev)
# Sobre o projeto

Task Manager criado para aprimorar meus conhecimentos com spring framework, é uma api rest que os usuários podem adicionar, obter, atualizar e deletar tarefas de sua lista pessoal.

## Documentação da API

#### Efetuar Login

```http
POST {LOCALHOST}/login
```

| Campo   | Tipo       | Obrigatório                                   |  Valores Permitidos| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `username`      | `string` | **Sim** | Qualquer string (usuário padrão: admin) |
| `password`      | `string` | **Sim** | Qualquer string (senha padrão: 123456)|


#### Adicionar tarefa

```http
POST {LOCALHOST}/task-manager
```

| Campo   | Tipo       | Obrigatório                                   |  Valores Permitidos| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `Authorization`      | `string` | **Sim** | Bearer eyJhbGciOiJIUzI1Ni... **()** |
| `title`      | `string` | **Sim** | Qualquer string |
| `description`      | `string` | **Sim** | Qualquer string **(Max. 150 caracteres)**  | 
| `creatorId`      | `integer (int64)` | **Sim** | > 0 |
| `numberEstimatedHours`      | `integer` | **Sim** | > 0 |


#### Obter tarefa

```http
GET {LOCALHOST}/task-manager
```

| Campo   | Tipo       | Obrigatório                                   |  Valores Permitidos| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `Authorization`      | `string` | **Sim** | Bearer eyJhbGciOiJIUzI1Ni... |

#### Atualizar tarefa

```http
PUT {LOCALHOST}/task-manager/{id}
```

| Campo   | Tipo       | Obrigatório                                   |  Valores Permitidos| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `Authorization`      | `string` | **Sim** | Bearer eyJhbGciOiJIUzI1Ni... |
| `title`      | `string` | **Sim** | Qualquer string |
| `description`      | `string` | **Sim** | Qualquer string **(Max. 150 caracteres)**  | 
| `taskStatus`      | `enum` | **Não** | create, processing, blocked, finished  | 
| `responsibleId`      | `integer (int64)` | **Não** | > 0 |
| `numberEstimatedHours`      | `integer` | **Sim** | > 0 |
| `numberRealizeHours`      | `integer` | **Não** | >= 0 |

#### Deletar tarefa

```http
DELETE {LOCALHOST}/task-manager/{id}
```

| Campo   | Tipo       | Obrigatório                                   |  Valores Permitidos| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `Authorization`      | `string` | **Sim** | Bearer eyJhbGciOiJIUzI1Ni... **()** |

## Tecnologias utilizadas

#### • Java
#### • Spring Framework
#### • PostgreSQL / Hibernate
#### • Git / GitHub

## Como executar o projeto 

### Pré requisitos: 


#### Certifique-se de ter instalados:

JDK 17 → java -version

Maven → mvn -version

Banco de Dados (PostgreSQL)

Git (se for clonar o repositório)

```bash
# clonar repositório
https://github.com/KellaoDev/task-manager

# Configurar banco de dados
No arquivo application.properties:
Exemplo(PostgreSQL)

spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Executar o projeto com Maven
mvn spring-boot:run

# Acessar a API
http://localhost:8080 (ou outra porta configurada)
```
## Autor

### Kélio Cirilo da Silva Filho
https://www.linkedin.com/in/keliocirilo/





    
