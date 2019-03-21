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

URL de acesso: /users
Esta URL de acesso só pode ser usada por usuários com papel de ADMIN.

##### Criando um novo usuário

O cadastro de novos usuários dá-se através do método POST. Devem ser informados os parâmetros -u, -H e -d.

Exemplo de uso: 

curl -u luana@gmail.com:123456 -H "Content-Type: application/json" -d "{\"username\":\"juliana@gmail.com\",\"name\":\"Juliana\",\"role\":\"USER\",\"password\":\"123456\"}" -X POST http://localhost:8080/www.videolocadora.com/users

##### Removendo um usuário

A remoção de um usuário dá-se através do método DELETE. Devem ser informados os parâmetros -u e -X, e a URL deve ser seguida do id do usuário a ser removido.

Exemplo de uso:
 
curl -u luana@gmail.com:123456 -X DELETE http://localhost:8080/www.videolocadora.com/users/julianna@gmail.com

##### Listando os usuários inscritos

A listagem dos usuários inscritos dá-se através do método GET. Deve ser informado o parâmetro -u.

Exemplo de uso:

curl -u luana@gmail.com:123456 http://localhost:8080/www.videolocadora.com/users


#### Filmes:

URL de acesso: /movies

##### Criando um novo filme

O cadastro de novos filmes dá-se através do método POST (disponível apenas para usuários com papel de ADMIN). Devem ser informados os parâmetros -u, -H e -d.
Ao cadastrar um novo filme não é necessário informar um id no corpo da requisição. O mesmo será gerado automaticamente.

Exemplo de uso: 

curl -u luana@gmail.com:123456 -H "Content-Type: application/json" -d "{\"title\":\"Filme Teste\",\"director\":\"Diretor Teste\",\"available\": true}" -X POST http://localhost:8080/www.videolocadora.com/movies

##### Editando um filme 

A edição de filmes cadastrados dá-se através do método PUT (disponível apenas para usuários com papel de ADMIN). Devem ser informados os parâmetros -u, -H e -d.
Ao editar um filme é necessário adicionar o id do mesmo ao corpo da requisição.

Exemplo de uso: 

curl -u luana@gmail.com:123456 -H "Content-Type: application/json" -d "{\"id\":\"48\",\"title\":\"Novo Titulo\",\"director\":\"Diretor Teste\",\"available\": false}" -X PUT http://localhost:8080/www.videolocadora.com/movies

##### Removendo um filme

A remoção de um filme dá-se através do método DELETE (disponível apenas para usuários com papel de ADMIN). Devem ser informados os parâmetros -u e -X, e a URL deve ser seguida do id do filme a ser removido.

Exemplo de uso:
 
curl -u luana@gmail.com:123456 -X DELETE http://localhost:8080/www.videolocadora.com/movies/48

##### Listando os filmes disponíveis

A listagem dos filmes dá-se através do método GET. Deve ser informado o parâmetro -u.
Neste listagem serão apresentados apenas os filmes disponíveis para locação.

Exemplo de uso:

curl -u luana@gmail.com:123456 http://localhost:8080/www.videolocadora.com/movies


#### Locações

##### Locando um filme

A locação de um filme dá-se através do método POST. Devem ser informados os parâmetros -u e -X, e a URL deve ser seguida do id do filme a ser locado.

Exemplo de uso:

curl -u luana@gmail.com:123456 -X POST http://localhost:8080/www.videolocadora.com/rents/100

##### Devolvendo um filme locado

A locação de um filme dá-se através do método PUT. Devem ser informados os parâmetros -u e -X, e a URL deve ser seguida do id do filme a ser retornado.

curl -u luana@gmail.com:123456 -X PUT http://localhost:8080/www.videolocadora.com/rents/100

##### Consultando todas os filmes locados e não devolvidos

A consulta das locações não retornadas dá-se através do método GET (disponível apenas para usuários com papel de ADMIN). Deve ser informado o parâmetro -u.

Exemplo de uso:

curl -u luana@gmail.com:123456 http://localhost:8080/www.videolocadora.com/rents

##### Consultando todas as locações feitas para um determinado filme

A consulta das locações feitas para um determinado filme dá-se através do método GET (disponível apenas para usuários com papel de ADMIN). Deve ser informado o parâmetro -u,  e a URL deve ser seguida do id do filme a ser consultado.

Exemplo de uso:

curl -u luana@gmail.com:123456 http://localhost:8080/www.videolocadora.com/rents/100

#### Sobre as estruturas de armazenamento

Foi utilizado durante o desenvolvimento o banco de dados MySQL. Devido à natureza da aplicação, que utiliza o framework Hibernate, ao inicializar a aplicação o banco de dados será automaticamente gerado (verifique as credenciais de banco em application.properties). O banco de dados é composto por 3 tabelas, geradas a partir dos scripts apresentados a seguir:
DROP TABLE IF EXISTS `tb_rents`;
DROP TABLE IF EXISTS `tb_movies`;
DROP TABLE IF EXISTS `tb_users`;

CREATE TABLE `tb_movies` (
  `id` int(11) NOT NULL,
  `available` bit(1) NOT NULL,
  `director` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `tb_users` (
  `username` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
);

CREATE TABLE `tb_rents` (
  `id` int(11) NOT NULL,
  `rental_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `fk_tb_movies` int(11) NOT NULL,
  `fk_tb_users` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhd6k1mpe5yq12o58pi7go7v61` (`fk_tb_movies`),
  KEY `FKoi3mubmff6mdfbs2jgsi79g9m` (`fk_tb_users`),
  CONSTRAINT `FKhd6k1mpe5yq12o58pi7go7v61` FOREIGN KEY (`fk_tb_movies`) REFERENCES `tb_movies` (`id`),
  CONSTRAINT `FKoi3mubmff6mdfbs2jgsi79g9m` FOREIGN KEY (`fk_tb_users`) REFERENCES `tb_users` (`username`)
);

##### Scripts para inserção de dados de teste no Banco de Dados

INSERT INTO `tb_users` VALUES ('joao@gmail.com','Joao','123456','USER'),('luana@gmail.com','Luana','123456','ADMIN'),('maria@gmail.com','Maria','123456','USER'),('marta@gmail.com','Marta','123456','USER');

INSERT INTO `tb_movies` VALUES (11,1,'Director','Movie One'),(12,1,'Director','Movie Two'),(13,1,'Director','Movie Three'),(14,1,'Director','Movie Four'),(15,1,'Director','Movie Five'),(16,1,'Director','Movie Six'),(17,1,'Director','Movie Seven'),(18,1,'Director','Movie Eight'),(19,1,'Director','Movie Nine'),(20,1,'Director','Movie Ten');


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

#### Testes Unitários

Foram incluídos no projeto, testes unitários para validar:
-Cenário onde 3 documentos foram co-visualizados pelos mesmo usuário, no mesmo número de vezes. 
-Cenário onde 5 documentos recebem visualizações por diversos usuários (10 diferentes usuários).
Ambos cenários validam que a recomendação recebida pelos documentos esteja de acordo com o resultado esperado.

#### Testes Funcionais

Os testes funcionais executados seguiram os mesmos cenários dos testes unitários, porém, utilizando números maiores de registros, e análise dos resultados obtidos.

#### Testes de carga

Testes de carga foram executados utilizando a ferramenta Postman.
A ferramenta perde a estabilidade (e trava) em testes acima de 10 mil iterações, portanto, os testes de POST foram executados usando estes valores.

Em testes de POST, para a inserção de 10 mil registros, foram obtidos os seguintes resultados:
	"delay": 0
	"count": 10000
	"totalPass": 10000
	"totalFail": 0
	"totalTime": 32182 ms
	
Em testes de GET, para a requisição de recomendações para 3 mil documentos, foram obtidos os seguintes resultados:
	"delay": 0,
	"count": 3000,
	"totalPass": 3000,
	"totalFail": 0,
	"totalTime": 18131 ms

