# Task Manager
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/KellaoDev)
# About the project

Task Manager created to improve my knowledge with spring framework, it is a rest api that users can add, get, update and delete tasks from their personal list.

## API documentation

#### Login

```http
POST {LOCALHOST}/login
```

| Field   | Type       | Required                                   |  allowed values| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `username`      | `string` | **Yes** | Qualquer string (usuário padrão: admin) |
| `password`      | `string` | **Yes** | Qualquer string (senha padrão: 123456)|


#### Add task

```http
POST {LOCALHOST}/task-manager
```

| Field   | Type       | Required                                   |  allowed values| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `Authorization`      | `string` | **Yes** | Bearer eyJhbGciOiJIUzI1Ni... **()** |
| `title`      | `string` | **Yes** | Qualquer string |
| `description`      | `string` | **Yes** | Qualquer string **(Max. 150 caracteres)**  | 
| `creatorId`      | `integer (int64)` | **Yes** | > 0 |
| `numberEstimatedHours`      | `integer` | **Yes** | > 0 |


#### Get task

```http
GET {LOCALHOST}/task-manager
```

| Field   | Type       | Required                                   |  allowed values| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `Authorization`      | `string` | **Yes** | Bearer eyJhbGciOiJIUzI1Ni... |

#### Update task

```http
PUT {LOCALHOST}/task-manager/{id}
```

| Field   | Type       | Required                                   |  allowed values| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `Authorization`      | `string` | **Yes** | Bearer eyJhbGciOiJIUzI1Ni... |
| `title`      | `string` | **Yes** | Qualquer string |
| `description`      | `string` | **Yes** | Qualquer string **(Max. 150 caracteres)**  | 
| `taskStatus`      | `enum` | **No** | create, processing, blocked, finished  | 
| `responsibleId`      | `integer (int64)` | **No** | > 0 |
| `numberEstimatedHours`      | `integer` | **Yes** | > 0 |
| `numberRealizeHours`      | `integer` | **Yes** | >= 0 |

#### Delete task

```http
DELETE {LOCALHOST}/task-manager/{id}
```

| Field   | Type       | Required                                   |  allowed values| 
| :---------- | :--------- | :------------------------------------------ | :------|
| `Authorization`      | `string` | **Yes** | Bearer eyJhbGciOiJIUzI1Ni... **()** |

## Technologies used

#### • Java
#### • Spring Framework
#### • PostgreSQL / Hibernate
#### • Git / GitHub

## How to execute the project

### Prerequisites:


#### Make sure you have installed:

JDK 17 → java -version

Maven → mvn -version

Banco de Dados (PostgreSQL)

Git (if you are going to clone the repository)

```bash
# Clone repository
https://github.com/KellaoDev/task-manager

# Configure database
In the archive application.properties:
Example(PostgreSQL)

spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=your_username
spring.datasource.password=your_password

# Run the project with Maven
mvn spring-boot:run

# Access the API
http://localhost:8080 (or another configured port)
```
## Author

### Kélio Cirilo da Silva Filho
https://www.linkedin.com/in/keliocirilo/





    
