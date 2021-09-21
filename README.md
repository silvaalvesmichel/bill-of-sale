Itens Detalahdos que foram desenvolvidos:
Nesta API temos 5 entidades principais: usuários, receitas, despesas, empresas e categorias. A relacionamento foi feita da seguinte forma:
Usuário pode cadastrar inúmeras despesas e receitas;
Uma receita sempre está vinculada a uma empresa;
Uma despesa está sempre vinculada a uma categoria;
Uma despesa pode ou não estar vinculada a uma empresa;
Todas as receitas e despesas devem possuir a data de ocorrência e data de competência;
Um usuário pode cadastrar e editar várias categorias de despesas;
Um usuário pode cadastrar e editar várias empresas:

Ordem dos Endpoints: Informacoes de corpo de requisicao consultar collection do postman que adicionei ao projeto -> bill-of-sale.postman_collection.json

 1 - Cadastrar usuario: method:POST -> http://localhost:8080/api/users

 2 - Fazer Login para recuperar o Token JWT para consumir os outros end-points: method:POST -> http://localhost:8080/login

 3 - Recuperar usuario pelo id: method:GET -> http://localhost:8080/api/users/1

 4 - Cadastrar empresa: method:POST -> http://localhost:8080/api/customers
 
 5 - Atualizar empresa passando o id da empresa no path: method:PUT -> http://localhost:8080/api/customers/1
 
 6 - Cadastrar Receita passando o id da empresa no path da requisicao: method:POST -> http://localhost:8080/api/revenues/1
 
 7 - Atualizar Receita passando o id da receita no path da requisicao: method:PUT -> http://localhost:8080/api/revenues/1

 8 - Recuperando empresas por nome o cnpj caso nao passe nenhum dos dois recupera todas empresas: method:POST -> http://localhost:8080/api/customers?name=&cnpj=
 
 9 - Calculo de total de receitas: method:POST -> http://localhost:8080/api/reports/total-revenue

 10 - Recuperando receitas por mes: method:POST -> http://localhost:8080/api/reports/revenue-by-month

 11 - Recuperando receitas com empresas: method:POST -> http://localhost:8080/api/reports/revenue-by-customer

 12 - URI sugerida: /api/v{n}/customers/{customerID}/archives -> esse ficou faltando por falta de informacoes que foi passado no escopo, solicitei via e-mail e nao obtive resposta.

Para Testar o Projeto:

o Projeto feito em Spring Boot versão: 2.4.2. o Banco H2. o Type: Maven o Java Version: 11

Opcional: Utilizar IDE: STS - Spring Tool Suit (Eclipse / Maven / Tomcat / Jackson / JPA): https://spring.io/tools ou INTELIJ IDEA

Para excutar o projeto acessar o arquivo com.me.desafio.BillOfSaleApplication -> Botão direito -> Run As -> Spring Boot App

Postman: https://www.postman.com/downloads/ -> para testar endPoints

Adicionei o arquivo(bill-of-sale.postman_collection.json) no projeto, aonde contem todas as chamadas dos serviços rest.

