# Desafio Backend Java

![](https://img.shields.io/badge/java_8-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/h2-✓-blue.svg)
![](https://img.shields.io/badge/jwt-✓-blue.svg)
![](https://img.shields.io/badge/swagger_2-✓-blue.svg)

# Como rodar a aplicação?

1. Instalar [Java 8](https://www.java.com/download/) and [Maven](https://maven.apache.org)

2. Clonar o repository

3. Com o cmd utilizar na pasta o:

```
$ cd spring-boot-jwt
```

4. Instalar as dependencies

```
$ mvn install
```

5. Rodar o projeto

```
$ mvn spring-boot:run
```

6.  `http://localhost:8080/swagger-ui.html` para utilizar o swagger e testar a aplicação.

7. Para testar, primeiro fazer um GET request para `/users/me` para checar se está autenticado. Irá receber erro 403 com acesso negado

```
$ curl -X GET http://localhost:8080/users/me
```

8. Agora fazer um POST request para `/users/signin` com usuario admin e senha admin para receber um Token

```
$ curl -X POST 'http://localhost:8080/users/signin?username=admin&password=admin'
```

9. Adicionar o Token no Header e fazer um GET request para `/users/me`

```
$ curl -X GET http://localhost:8080/users/me -H 'Authorization: Bearer <JWT_TOKEN>'
```

10. E a resposta mostrará que o usuario está logado e poderá executar os requests de Tasks

```javascript
{
  "id": 1,
  "username": "admin",
  "email": "admin@email.com",
  "roles": [
    "ROLE_ADMIN"
  ]
}
```