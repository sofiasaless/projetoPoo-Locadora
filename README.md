# LOCADORA

- o sistema vai simular o aluguel e devolução de filmes de uma locadora local, podendo registrar clientes e o que eles tem feito na locadora, histórico de filmes alugados, filmes alugados, saldo, etc;
- o sistema vai contar com as opções de cliente e funcionário, o cliente poderá alugar e devolver filmes, já o funcionário vai controlar o sistema, cadastrar e remover filmes do catálogo, e ver a lista de todos os clientes, podendo fazer exclusão de algum;
- as informações de manipulação de filmes de clientes e funcionários ficarão salvas nos arquivos .ser;

## TECNOLOGIAS UTILIZADAS #
- Serialização e desserialização de arquivos .ser;
- Usuarios clientes e funcionarios são salvos em arquivos .ser usando a serialização;
- filmes salvos nas categorias também são salvos em arquivos .ser usando serialização;

## INSTRUÇÕES DE USO #
- o uso será intuitivo pelo usuário, feito pelo terminal e selecionando as opções de acordo com os respectivos números;
- o cliente poderá inserir saldo em sua conta, navegar pela variedade de filmes registrados na locadora, escolher o filme que quer e alugar, após isso também poderá devolver filmes; todas essas informações serão serializadas no arquivo clientes.ser;
- o funcionário poderá bater o ponto do dia trabalhado e de acordo com os dias, seu rendimento irá aumentar, também poderá cadastrar e remover filmes no sistema, e sacar seu salário de acordo com os dias trabalhados;

# ESTRUTURA DO CÓDIGO #

* *a desenvolver...*
    - transferir todo o código para a interface gráfica do java swing (nao consegui, faltou tempo);

* *já desenvolvido*
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

## APRESENTAÇÃO DOS CONTEÚDOS DE POO NO TRABALHO

* ENCAPSULAMENTO E AGREGAÇÃO
    - a maioria das classes tem seus atributos privados ou protegido, sendo acessados por meio de get();
    - métodos privados e protegidos foram os mais usados;
    - as classes Alugar, Devolver, Listarliente e ManipularFilmes são agregadas pelas filhas de Locadora para fazer suas funcionalidades;
    - classe filme é essencial, é agregada por muitas;
> exemplo: classe EntrarCadastrar, que agrega de Usario e suas classes filhas para fazer os processos de login e cadastro de usuario no sistema;

* ENUM E EXCEÇÕES
    - enum Generos; (representam categorias dos filmes);
    - as exceções estão presentes na própria interface, se o Usuario colocar uma opção que não condiz, automaticamente será lançada uma exceção;
> exemplo ENUM: ao cadastrar um filme tem que selecionar um genero;
> exemplo EXCEÇÕES: lançamento de exceções para praticamente todas as funcionalidades do sistema;

* LISTAS E MAPAS
    - a maioria das classes usaram listas para armazenar dados enquanto o sistema está estava em execução, seja lista de Clientes ou filmes de diferentes categorias;
    - classe EntrarCadastrar usa Map para fazer login de usuarios no sistema;
> exemplo: classe alugar armazena em lista os objetos do tipo filme guardados no arquivo .ser da categoria que o Cliente deseja alugar;

* HERANÇA E POLIMORFISMO
    - Locadora e Usuario, duas classes pais que são herdadas por suas respectivas classes filhas;
    - metodos que aplicam polimorfismos nas classes filhas;
> exemplo USUARIO: incrementarSaldo(), as duas tem funcionalidades diferentes;
> exemplo LOCADORA: filmes_in, filmes_out, adcFilmes_in, adcFilmes_out;

* INTERFACE E EXTRAS (armazenamento de dados por serialização)
    - armazenamento de dados feito por serialização e desserialização de arquivos .ser;
    - interface de serializacao, implementada por classes que fazem uso do processo de serializar e disserializar arquivos;