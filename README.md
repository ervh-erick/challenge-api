# API rest CRUD de carros e usuários - challenge-api
Projeto de desafio tecnico

# Pre-requisitos<br/>
Java 17 & Maven

Construído com Spring Tools Suite

# URL Principal
https://challenge-api-mq4j.onrender.com/api</br>.

# Rotas
GET</br>
https://challenge-api-mq4j.onrender.com/api/users - Listar todos os usuários</br>
https://challenge-api-mq4j.onrender.com/api/cars - Listar todos os carros do usuário logado</br>
https://challenge-api-mq4j.onrender.com/api/users/id - Buscar um usuário por id<br/>
https://challenge-api-mq4j.onrender.com/api/cars/id - Buscar um carro do usuário logado pelo id<br/>
https://challenge-api-mq4j.onrender.com/api/me - Buscar um usuário logado<br/>

POST</br>
https://challenge-api-mq4j.onrender.com/api/users - Cadastrar um novo usuário </br>
{</br>
  "firstName": "Hello",</br>
  "lastName": "World",</br>
  "email": "hello@world.com",</br>
  "birthday": "1990-05-01",</br>
  "login": "hello.world",</br>
  "password": "h3ll0",</br>
  "phone": "988888888",</br>
}</br>

https://challenge-api-mq4j.onrender.com/api/signin - login</br>
{</br>
  "login": "login",</br>
  "password": "123"</br>
}</br>
</br>

Cadastrar um novo carro para o usuário logado</br>
{</br>
    "year": 333,</br>
    "licensePlate": "ervh",</br>
    "model": "gl",</br>
    "color": "22222"</br>
}</br></br>


PUT</br>
https://challenge-api-mq4j.onrender.com/api/users/id - Atualizar um usuário pelo id</br>
https://challenge-api-mq4j.onrender.com/api/cars/id - Atualizar um carro do usuário logado pelo id</br>

DELETE</br>
https://challenge-api-mq4j.onrender.com/api/users/id - Remover um usuário pelo id</br>
https://challenge-api-mq4j.onrender.com/api/cars/id - Remover um carro do usuário logado pelo id</br>


# Features

release-sprint1
<br/>
feature/story_CHALLENGE-S1-1: Criação do projeto de commit inicial<br/>
feature/story_CHALLENGE-S1-2: conexão com banco de dados, criação de pacotes (domínio e repositórios) e mapeamento Jpa<br/>
feature/story_CHALLENGE-S1-3: Criação de pacotes (controladores, serviços) e respectivas classes<br/>

release-sprint2<br/>
feature/story_CHALLENGE-S2-1: Criando a classe UserDTO e criando a rota /api/users Listar todos os usuários<br/>
feature/story_CHALLENGE-S2-2: Criando a rota Listar todos os usuários<br/>
feature/story_CHALLENGE-S2-3: criação da rota Procure um usuário por id<br/>
feature/story_CHALLENGE-S2-4: criando a rota Atualizar um usuário por id<br/>
feature/story_CHALLENGE-S2-5: criando a rota Remover um usuário por id<br/>

release-sprint3<br/>
feature/story_CHALLENGE-S3-1: criação da rota Login, Adicionar codificador de senha, token de autenticação e Cors<br/>

release-sprint4<br/>
feature/story_CHALLENGE-S4-1: Retornar as informações do usuário logado<br/>
feature/story_CHALLENGE-S4-2: Cadastrar um novo carro para o usuário logado<br/>
feature/story_CHALLENGE-S4-3: Listar todos os carros do usuário logado<br/>
feature/story_CHALLENGE-S4-4: Buscar um carro do usuário logado pelo id<br/>
feature/story_CHALLENGE-S4-5: Remover um carro do usuário logado pelo id<br/>
feature/story_CHALLENGE-S4-6: Atualizar um carro do usuário logado pelo idK<br/>

release-sprint5<br/>
feature/story_CHALLENGE-S5-1: config Swaggeer<br/>
feature/story_CHALLENGE-S5-2: JavaDoc<br/>
Deploy render

