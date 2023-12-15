**LOCADORA**

- o sistema vai simular o aluguel e devolução de filmes de uma locadora local, podendo registrar clientes e o que eles tem feito na locadora, histórico de filmes alugados, filmes alugados, saldo, etc;
- o sistema vai contar com as opções de cliente e funcionário, o cliente poderá alugar e devolver filmes, já o funcionário vai controlar o sistema, cadastrar e remover filmes do catálogo, e ver a lista de todos os clientes, podendo excluir eles se quiser;
- as informações de manipulação de filmes de clientes e funcionários ficarão salvas nos arquivos .ser;

# TECNOLOGIAS UTILIZADAS #
- Serialização e desserialização de arquivos .ser;
- usuarios clientes e funcionarios são salvos em arquivos .ser usando a serialização;
- filmes salvos nas categorias também são salvos em arquivos .ser usando serialização;

# INSTRUÇÕES DE USO #
- o uso será intuitivo pelo usuário, feito na interface do terminal e selecionando as opções de acordo com os números;
- o cliente poderá inserir saldo em sua conta, navegar pela variedade de filmes registrados na locadora, escolher o filme que quer e alugar, após isso também poderá devolver filmes; todas essas informações serão serializadas no arquivo clientes.ser;
- o funcionário poderá bater o ponto do dia trabalhado e de acordo com isso seu rendimento irá aumentar, também poderá cadastrar e remover filmes no sistema, e sacar seu salário de acordo com os dias trabalhados;

# ESTRUTURA DO CÓDIGO #
**a desenvolver**
<!--- 
    - transferir todo o código para a interface gráfica do java swing (nao consegui, faltou tempo);
[...] -->

**já desenvolvido**
<!--- 
    - cadastrar e remover filmes do catálogo;
    - menu introdutorio da locadora;
    - cadastrar clientes ao alugar os filmes;
    - criar classes alugar, devolver;
    - menu para apresentação dos filmes;
    - logica para alguel e devoluação dos filmes;
    - tratamento de erros ao decorrer do codigo;
    - opções da locadora para o funcionário operar nela;
    - opção de remover clientes pelo funcionario;
    - gravar o histórico de filmes alugados pelos clientes, na propria classe usuario fazer um usu de polimorfismo;
    - colocar o genero "lançamentos" no menu de generos de filmes do cliente;
    - mudar a forma de cadastrar filmes no tipo lançamento, adicionando a escolha de genero ao cadastrar;
    - na classe usuario, adicionar os filmes que ele já adicionou ou removeu, num método filmesAd() e num método filmesRm(); vao adotar diferentes funções nas classes funcionario e cliente; (POLIMORFISMO);
    - mudar o scan da opção de inserir saldo, deve receber parametros do tipo double;
    - reavaliar a classe locadora e suas classes filhas, a classe locadora vai ficar um tanto inutilizada com as novas implementações do cliente e funcionário;
    - implementar a função de transferir saldo do usuario;
    - criar interface arquivos para que algumas classes herdem funções do tipo serializar e disserializar;
-->

+ APRESENTAÇÃO DOS CONTEÚDOS DE POO NO TRABALHO +

# ENCAPSULAMENTO E AGREGAÇÃO
- get e sets das classes, mostrando que implementei de forma correta, eficiente e útel;
- métodos privados e protegidos foram os mais usados;
- classes que agregam de outras;
- classe filme que é agregada por muitas;
> exemplo: classe EntrarCadastrar, que agrega de Usario e suas classes filhas para fazer os processos de login e cadastro de usuario no sistema;

# ENUM E EXCEÇÕES
- enum Generos; (representam mais como uma categoria dos filmes);
- exceções da locadora;
> exemplo ENUM: ao cadastrar um filme tem que selecionar um genero;
> exemplo EXCEÇÕES: lançamento de exceções para praticamente todas as funcionalidades do sistema;

# LISTAS E MAPAS
- classes que usaram listas (a maioria delas) e para que serviram;
- classe EntrarCadastrar, alugar, manipular filmes, listar clientes, etc...
> exemplo: classe alugar, falar um pouco da lógica para alugar filmes, o uso da listas...

# HERANÇA E POLIMORFISMO
- Locadora e Usuario, duas classes pais que são herdadas por suas respectivas classes filhas;
- metodos que aplicam polimorfismos nas classes filhas;
> exemplo USUARIO: incrementarSaldo(), as duas tem funcionalidades diferentes;
> exemplo LOCADORA: filmes_in, filmes_out, adcFilmes_in, adcFilmes_out;

# INTERFACE E EXTRAS (armazenamento de dados por serialização)
- armazenamento de dados feito por serialização e desserialização de arquivos .ser;
- interface de serializacao, implementada por classes que fazem uso do processo de serializar e disserializar arquivos;