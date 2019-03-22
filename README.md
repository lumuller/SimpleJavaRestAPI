## Luana Müller
Projeto VideoLocadora
================

## Considerações Gerais

Projeto de API REST para locação de filmes. Permite cadastro de filmes, usuários (clientes) e a efetuação de locações e devoluções de filmes.

## Detalhes do projeto

O projeto foi contruído utilizando a linguagem Java, utilizando o framework open-source Spring.
A construção do projeto foi feita através do Spring Boot. 

### Interfaces da API

Informações gerais:
Para o uso dos métodos da API, em alguns casos, poderão ser solicitadas algumas informações complementares, tais como:
Autenticação do usuário: Username e senha do usuário;
Corpo da requisição: Texto no formato JSON com os dados a serem enviados;
Especificação do Content-Type: Sempre será usada a especificação "application/json".

Os exemplos de uso a seguir são demonstrados com o uso do comando curl, onde:
-u: Autenticação do usuário
-H: Especificação do Content-Type
-d: Corpo da requisição
-x: Método requisitado (POST, GET, DELETE, PUT). Para métodos POST e GET o uso deste parâmetro é opcional

#### Usuários/Clientes:

**URL de acesso: /users**
*Esta URL de acesso só pode ser usada por usuários com papel de ADMIN.* 

##### Criando um novo usuário

O cadastro de novos usuários dá-se através do método POST. Devem ser informados os parâmetros -u, -H e -d.

Exemplo de uso: 

* curl -u luana@gmail.com:123456 -H "Content-Type: application/json" -d "{\"username\":\"juliana@gmail.com\",\"name\":\"Juliana\",\"role\":\"USER\",\"password\":\"123456\"}" -X POST http://localhost:8080/www.videolocadora.com/users *

##### Removendo um usuário

A remoção de um usuário dá-se através do método DELETE. Devem ser informados os parâmetros -u e -X, e a URL deve ser seguida do id do usuário a ser removido.

Exemplo de uso:
 
* curl -u luana@gmail.com:123456 -X DELETE http://localhost:8080/www.videolocadora.com/users/julianna@gmail.com *

##### Listando os usuários inscritos

A listagem dos usuários inscritos dá-se através do método GET. Deve ser informado o parâmetro -u.

Exemplo de uso:

* curl -u luana@gmail.com:123456 http://localhost:8080/www.videolocadora.com/users *

#### Filmes:

** URL de acesso: /movies **

##### Criando um novo filme

O cadastro de novos filmes dá-se através do método POST (disponível apenas para usuários com papel de ADMIN). Devem ser informados os parâmetros -u, -H e -d.
Ao cadastrar um novo filme não é necessário informar um id no corpo da requisição. O mesmo será gerado automaticamente.

Exemplo de uso: 

* curl -u luana@gmail.com:123456 -H "Content-Type: application/json" -d "{\"title\":\"Filme Teste\",\"director\":\"Diretor Teste\",\"available\": true}" -X POST http://localhost:8080/www.videolocadora.com/movies *

##### Editando um filme 

A edição de filmes cadastrados dá-se através do método PUT (disponível apenas para usuários com papel de ADMIN). Devem ser informados os parâmetros -u, -H e -d.
Ao editar um filme é necessário adicionar o id do mesmo ao corpo da requisição.

Exemplo de uso: 

* curl -u luana@gmail.com:123456 -H "Content-Type: application/json" -d "{\"id\":\"48\",\"title\":\"Novo Titulo\",\"director\":\"Diretor Teste\",\"available\": false}" -X PUT http://localhost:8080/www.videolocadora.com/movies * 

##### Removendo um filme

A remoção de um filme dá-se através do método DELETE (disponível apenas para usuários com papel de ADMIN). Devem ser informados os parâmetros -u e -X, e a URL deve ser seguida do id do filme a ser removido.

Exemplo de uso:
 
* curl -u luana@gmail.com:123456 -X DELETE http://localhost:8080/www.videolocadora.com/movies/48 *

##### Listando os filmes disponíveis

A listagem dos filmes dá-se através do método GET. Deve ser informado o parâmetro -u.
Neste listagem serão apresentados apenas os filmes disponíveis para locação.

Exemplo de uso:

* curl -u luana@gmail.com:123456 http://localhost:8080/www.videolocadora.com/movies *

#### Locações

** URL de acesso: /rents **

##### Locando um filme

A locação de um filme dá-se através do método POST. Devem ser informados os parâmetros -u e -X, e a URL deve ser seguida do id do filme a ser locado.

Exemplo de uso:

* curl -u luana@gmail.com:123456 -X POST http://localhost:8080/www.videolocadora.com/rents/100 *

##### Devolvendo um filme locado

A locação de um filme dá-se através do método PUT. Devem ser informados os parâmetros -u e -X, e a URL deve ser seguida do id do filme a ser retornado.

* curl -u luana@gmail.com:123456 -X PUT http://localhost:8080/www.videolocadora.com/rents/100 *

##### Consultando todas os filmes locados e não devolvidos

A consulta das locações não retornadas dá-se através do método GET (disponível apenas para usuários com papel de ADMIN). Deve ser informado o parâmetro -u.

Exemplo de uso:

* curl -u luana@gmail.com:123456 http://localhost:8080/www.videolocadora.com/rents *

##### Consultando todas as locações feitas para um determinado filme

A consulta das locações feitas para um determinado filme dá-se através do método GET (disponível apenas para usuários com papel de ADMIN). Deve ser informado o parâmetro -u,  e a URL deve ser seguida do id do filme a ser consultado.

Exemplo de uso:

* curl -u luana@gmail.com:123456 http://localhost:8080/www.videolocadora.com/rents/100 *

#### Sobre as estruturas de armazenamento

Foi utilizado durante o desenvolvimento o banco de dados MySQL. Devido à natureza da aplicação, que utiliza o framework Hibernate, ao inicializar a aplicação o banco de dados será automaticamente gerado (verifique as credenciais de banco em application.properties). O banco de dados é composto por 3 tabelas. O script para criação das tabelas e inserção de dados para teste encontra-se disponível em BancoDados/script_videolocadora.sql

### Requisitos

Java Runtime Environment 8

Java Development Kit 8

Apache Maven 3.3.x ou superior

MySQL 8.0

### Como rodar

Na pasta do projeto, execute os comandos abaixo para iniciar o serviço:

	mvnw clean
	mvnw install
	java -jar target/videolocadora-0.0.1-SNAPSHOT.jar

### Testes executados

Executados teste manuais para verificação de todos os cenários de uso, com diferentes usuários.

#### Testes Unitários

Foram incluídos no projeto, testes unitários para validar todos métodos disponibilizados através de API.


