Os funcionários de uma empresa X enfrentam um grande problema, como eles são muito democráticos, todos os dias gastam 30 minutos decidindo onde irão almoçar.
Por isso foi criado este pequeno sistema para auxiliar essa tomada de decisão!

Este projeto é um serviço REST para consultar qual o restaurante mais votado para ir almoçar. E para votar em um restaurante.

Como acessar o banco da aplicação:
- O projeto já vem com um banco acoplado em memória o H2 e com dados pre populados. Mas caso queira usar outro banco como o oracle por exemplo, então segue os scripts para criação de tabela e massa de dados em \src\main\resources\data.sql
- Baixar o projeto e executá-lo.
- com o projeto rodando é habilitado um console do banco, em http://localhost:8080/h2-console
- informe em JDBC URL: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;Mode=Oracle
- informe User Name: sa
- informe Driver Class: org.h2.Driver
- o Password: pode ficar em branco e após clicar em connect

Como acessar a aplicação:
- com o projeto em execução é só acessar o endereço do swagger da api em: http://localhost:8080/swagger-ui.html#


#####
Segue abaixo as estórias definidas para criação do projeto:

Estória 01
Eu como profissional faminto
Quero votar no meu restaurante favorito
Para que eu consiga democraticamente levar meus colegas a comer onde eu
gosto.

Critério de Aceitação
 Um profissional só pode votar em um restaurante por dia.


Estória 2
Eu como facilitador do processo de votação
Quero que um restaurante não possa ser repetido durante a semana
Para que não precise ouvir reclamações infinitas!

Critério de Aceitação
 O mesmo restaurante não pode ser escolhido mais de uma vez durante a semana.


Estória 3
Eu como profissional faminto
Quero saber qual foi o restaurante escolhido
Para que eu possa me despir de preconceitos e preparar o psicológico.
Critério de Aceitação
 Mostrar de alguma forma o resultado da votação.