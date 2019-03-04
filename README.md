#API de Receitas - Backend

----------------------

- Linguagem: Java
- Banco de Dados: MySQL (implementação com jooq)

##Características Gerais

Projeto para solução do problema apresentado para o teste. O sistema consiste em um API REST que contém 4 enpoints de requisição:
-   Criação de receita (/1/libertest/criarReceita):
	-	Método responsável pela criação de receitas;
	-	Recebe uma requição POST com o payload de criação, que uma vez validado, será salvo no banco de dados e uma mensagem de sucesso será retornada;
		-	(HttpStatus.OK) "Receita salva com sucesso";
	-	Caso o payload sejá considerado inválido, sem algum campo obrigatório ou se a receita já exista no banco, a receita não será salva e uma mensagem de erro será retornada;
	- 	Mensagens de erro são específicadas pelo tipo do erro: 
	-	    (HttpStatus.BAD_REQUEST) "Campo nome é obrigatório";
    -	    (HttpStatus.BAD_REQUEST) "Campo ingredientes é obrigatório e deve conter pelo menos um ingrediente";
    -	    (HttpStatus.BAD_REQUEST) "Campo modoDePreparo é obrigatório";
    -	    (HttpStatus.BAD_REQUEST) "Campo categorias é obrigatório e deve conter pelo menos uma categoria";
    -	    (HttpStatus.BAD_REQUEST) "Campo metadado é obrigatório";
    -	    (HttpStatus.BAD_REQUEST) "Campo tempoDePreparoMinutos é obrigatório";
    -	    (HttpStatus.BAD_REQUEST) "Campo rendimentoPorcao é obrigatório";
    -	    (HttpStatus.BAD_REQUEST) "Receita com o nome " + nome da receita + " já foi inserida no banco de dados";
	- Payload de criação
		```
			{
			    "nome": "9",
			    "ingredientes": [
			    	"leite", "cebola"
			    ],
			    "modoDePreparo": "a",
			    "categorias": [
			    	"pf", "doce"],
			    "metadado": {
			    	"tempoDePreparoMinutos": 120,
			    	"rendimentoPorcao": 4,
			    	"observacoes": null
			    }
			}
		```


-   Atualização de receita (/1/libertest/atualizarReceita):
    -   Método é responsável pela atualização de receita.
    -	Recebe uma requição PUT com o payload de atualização, que uma vez validado, será utilizado para atualizar a receita no banco de dados e uma mensagem de sucesso será retornada;
		-	(HttpStatus.OK) "Receita atualizada com sucesso";
    -	Caso o payload sejá considerado inválido, sem algum campo obrigatório ou se a receita não exista no banco, a receita não será atualizada e uma mensagem de erro será retornada;
	- 	Mensagens de erro são específicadas pelo tipo do erro: 
	
    -       (HttpStatus.BAD_REQUEST) "Campo nome é obrigatório";
	-	    (HttpStatus.BAD_REQUEST) "Campo ingredientes é obrigatório e deve conter pelo menos um ingrediente";
	-	    (HttpStatus.BAD_REQUEST) "Campo modoDePreparo é obrigatório";
	-	    (HttpStatus.BAD_REQUEST) "Campo categorias é obrigatório e deve conter pelo menos uma categoria";
	-	    (HttpStatus.BAD_REQUEST) "Campo metadado é obrigatório";
	-	    (HttpStatus.BAD_REQUEST) "Campo tempoDePreparoMinutos é obrigatório";
	-	    (HttpStatus.BAD_REQUEST) "Campo rendimentoPorcao é obrigatório";
	-	    (HttpStatus.BAD_REQUEST) "Receita com o nome " + nome da receita + " não foi localizada no banco de dados para ser atualizada"";
	- Payload de atualização
		```
			{
			    "nome": "9",
			    "ingredientes": [
			    	"leite", "cebola"
			    ],
			    "modoDePreparo": "a",
			    "categorias": [
			    	"pf", "doce"],
			    "metadado": {
			    	"tempoDePreparoMinutos": 120,
			    	"rendimentoPorcao": 4,
			    	"observacoes": null
			    }
			}
		```

        
-   Recuperar uma receita no sistema (/1/libertest/recuperarReceita):         
    -   Método é responsável pela atualização de receita.
    -	Recebe uma requição GET com parâmetros de filtragem que serão utilizados para recuperar a receita do banco de dados, retornando as receitas em uma lista;
    - 	Parâmetros do GET:
    	-	nomeReceita (String) - Recupera por nome, ignorando os outros parâmetros;
    	-	categoria (String) - Categoria da receita ser buscado;
    	-	listaIngredientes (String) - Separa cada ingrediente por vígula, sem espaços, retornando receitas que contém pelo menos dos ingredientes da lista;
    - 	Os parâmetros não são case sensitive;
    - 	Caso não recupere nenhuma receita com os parâmetros passados, retorna uma lista vazia;
    -	Exemplo de requisição completa 
    -       /1/libertest/recuperarReceita?nomeReceita=Molho&categoria=acompanhamento&listaIngredientes=ovo,leite;

    -	Exemplo de requisição 
    -       /1/libertest/recuperarReceita?listaIngredientes=leite;
    -   Resposta: 
	```
	    [
	    	{
	    	    "nome": "2",
	    	    "ingredientes": [
	    	        "leite",
	    	        "ovo",
	    	        "leite condensado"
	    	    ],
	    	    "modoDePreparo": "Bata no liquidificador e leve ao forno",
	    	    "metadado": {
	    	        "tempoDePreparoMinutos": 120,
	    	        "rendimentoPorcao": 4,
	    	        "observacoes": ""
	    	    },
	    	    "categorias": [
	    	        "sobremesa",
	    	        "doce"
	    	    ]
	    	},
	    	{
	    	    "nome": "6",
	    	    "ingredientes": [
	    	        "leite",
	    	        "cebola"
	    	    ],
	    	    "modoDePreparo": "Bata",
	    	    "metadado": {
	    	        "tempoDePreparoMinutos": 120,
	    	        "rendimentoPorcao": 4,
	    	        "observacoes": ""
	    	    },
	    	    "categorias": [
	    	        "doce"
    	        ]
	    	}
	    ]
	```

    
-   Deletar uma receita (/1/libertest/deletarReceita"):
    -   Método é responsável pela deleção de receitas.
    -	Recebe uma requição DELETE com parâmetros com o nome da receita a ser deletada;
    - 	Parâmetros do DELETE:
    	-	nomeReceita (String);
    -	Caso a receita seja deletada com sucesso, a seguinte mensagem será retornada:
		-	(HttpStatus.OK) "Receita deletada com sucesso";
	-	Possível mensagem de erro:
		-	(HttpStatus.BAD_REQUEST) "Receita com o nome " + nome da receita + " não foi localizada no banco de dados para ser deletada"";


##Utilização
-   Comando de build: mvn clean install;
-	Classes do banco serão geradas automaticamente via plugin (sql-maven-plugin), mas é necessário que o schema já esteja criado;
	-	Nome do schema: libertestdb;
-	Caso seja necessário, alterar variáveis de server port, database url, username e password no application.properties
	-   Valores default:
		-	server.port = 1000
		-	spring.datasource.url=jdbc:mysql://localhost:3306/libertestdb?useTimezone=true&serverTimezone=UTC
		-	spring.datasource.username=root
		-	spring.datasource.password=root
		-	db.gen.schema=libertestdb
-   Execute o arquivo com o comando java -jar libertest-1.0-SNAPSHOT
-   Com o sistema em execução, basta enviar uma requisição em um dos endpoints para acessar o sistema.

##Testes
-	Foi utilizada a JUnit-4;
-	Testes contemplam somente o fluxo de criação de receita;

    -   ReceitasControllerTest.criarReceitaValida
    -   ReceitasControllerTest.criarReceitaIngredientesNull
    -   ReceitasControllerTest.criarReceitaModoPreparoNull
    -   ReceitasControllerTest.criarReceitaCategoriaNull
    -   ReceitasControllerTest.criarReceitaTempoPreparoNull
    -   ReceitasControllerTest.criarReceitaNomeNull
    -   ReceitasControllerTest.criarReceitaMetadadoNull
    -   ReceitasControllerTest.criarReceitaRendimentoNull
