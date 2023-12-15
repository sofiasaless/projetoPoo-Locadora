# conteudos de poo no projeto #

<ENCAPSULAMENTO-AGREGAÇÃO>

- todas as classes fazem uso de encapsulamento, alguns tendo apenas get() e outras tendo get() e sent();
- boa parte das classes do projeto fazem uso de agregação, as classes filhas de Locadora fazem agregação de classes necessárias para o funcionamento de suas opções;
    -- a principal que é agregada é a classe Filme, responsável por gerar objetos do tipo filme;

<ENUM>

- o enum presento no projeto é o "Generos", tendo os enums de generos de filmes disponiveis para alugar (cliente) e cadastrar (funcionario) filmes;

<HERANÇA>

- a classe Usuario é abstrata, ela é herdada pelas classes Cliente e Funcionario;
    -- na classe Usuario há os atributos "filmes_in" e "filmes_out", ambos são interpretados de maneiras diferentes para o cliente e para o funcionario; para o cliente "filmes_in" são os filmes que ele tem como alugado, e "filmes_out" são os filmes que ele devolve para a locadora; ja na classe Funcionario, "filmes_in" são os filmes que ele cadastrou no catálogo da Locadora e "filmes_out" são os filmes que retirou do catálogo;
    -- ambos métodos "adcFilmesIn" e "adcFilmesOut" são sobreescritos com polimorfismo nas classes filhas;
- a classe Locadora é abstrata, ela é herdada pelas classes LocadoraCli e LocadoraFun;
    -- dependendo de cliente ou funcionario, quem for logado na locadora vai acessar diferentes opções;

<POLIMORFISMO>

- na classe Usuario, os metodos incrementarSaldo(), adcFilmeIn(), adcFilmeOut() são sobreescritos com polimorfismo nas classes Cliente e Funcionario, há a utilização do super em ambas as classes filhas;
- nas classes Locadora, os metodos verPerfil(), depositar() e setPerfilLogado() são sobreescritos com polimorfismo nas classes LocadoraCli e LocadoraFun;

<IMPLEMENTAÇÃO-INTERFACE>

- as classes EntrarCadastrar, ManipularFilmes, ListarClientes são implementadas pela interface Serializacao, todas essas classes fazem o processo de serializar e desserializar arquivos .ser;

<LISTAS(arrayList)>

- a maioria das classes utilizou listas para guardar temporiariamente objetos salvos em arquivos .ser, sendo eles filmes ou usuarios;
- no funcionamento da locadora essas listas podem sofrer alteraçações nos objetos dela, e assim sendo serializadas (salvas) nos respectivos arquivos .ser;
    ex: ao cadastrar um novo usuario na locadora, sendo ele do tipo cliente, o usuario vai ser adicionado na lista de clientes, posteriormente essa lista vai serializada e o novo cliente vai estar salvo no arquivos "clientes.ser", assim possibilitando o login dele na locadora; esse mesmo processo é feito quando é um funcionario fazendo cadastro e login; 

<MAPA>

- o maps é usado brevemente na classe EntrarCadastrar para ajudar na busca de clientes e funcionarios nos arquivos clientes.ser e funcionarios.ser; fora isso, o uso de listas foi mais eficiente para o sistema da Locadora;

<EXCEÇÕES-EXTRAS>

- todas as opções do sistema de locadora podem lançar exceções, todas foram tratadas com try catch na main;
- algumas exceções são tratadas como uma chave para poder voltar a menus de início;
    ex: ao alugar filmes, quando não é mais desejado alugar algum filme, o cliente deve digitir um input diferente de inteiro para conseguir voltar ao início;
- EXTRA: armazenamento de dados feito por serialização e desserialização de arquivos .ser;
    -- em arquivos .ser foi armazenado os usuarios e filmes de diferentes generos, podendo ser acessados e manipulados por cliente e funcionario na locadora;


# autoavaliação do trabalho #

<!-- professor, nao deu tempo mostrar esses requistos no video, mas dei uma breve resumida em cada um -->

Requisitos do Trabalho (Opção 1 - Sistema de Gerenciamento de Dados):

*Nota atendida/Nota requerida*

1. Modelagem de Classes (20 pontos):
    
    Correta criação e estruturação de classes (5 pontos). *4/5*
        - todas as classes tem suas funcionalidades adequadas e boa estruturação como foi aprendido em POO;
    Uso apropriado de associações e heranças (5 pontos). *4/5*
        - há duas heranças, Usuario e Locadora, classes pais que contém classes filhas no sistema;
    Demonstração de entendimento dos conceitos de encapsulamento (5 pontos). *5/5*
        - todas as classes tem atributos privados e as classes abstratas atributos protegidos, esses atributos podem ser acessados por get() e alterados por set();
    Implementação de métodos relevantes (5 pontos). *4/5*
        - as classes Alugar, Devolver, ListarClientes, etc... são classes que contruem diretamento para o funcionamento das opções da Locadora, todas elas com métodos relevantes para o código; 

2. Interface de Usuário (10 pontos):
    
    Interface amigável e funcional (5 pontos). *5/5*
        - a interface foi feita no terminal, a interação é partir de opções;
    Interação eficiente com o sistema (5 pontos). *4/5*

3. Funcionalidades Relevantes (30 pontos):

    Implementação de funcionalidades que atendem ao domínio escolhido (15 pontos). *12/15*
        - todas as classes agregadas pelas classes filhas de Locadora e Usuario tem funcionalidades que atendem ao objetivo do sistema;
    Correta manipulação de dados e informações (10 pontos). *8/10*
    Tratamento de casos excepcionais de forma adequada (5 pontos). *5/5*
        - todo o sistema está com try catch para interceptar exceções de input ou de ações do próprio sistema;
            ex: um input diferente das opções vai gerar um "Insira uma opção válida!"
            ex: cliente tentar alugar um filme sem saldo na carteira vai gerar um "Saldo insuficiente";

4. Persistência de Dados (10 pontos): *(usei a serialização de arquivos)*

    Escolha e implementação adequada de armazenamento de dados (5 pontos). *5/5*
        - escolhi fazer o armazenamento de dados por serialização de arquivos, assim os objetos das classes são guardados;
    Habilidade de recuperar e salvar informações de forma eficiente (5 pontos). *5/5*
        - os arquivos .ser do sistema são acessados com sucesso quando a locadora está funcionando;

5. Boas Práticas de Programação (30 pontos):
    Aplicação correta de encapsulamento, herança e polimorfismo (10 pontos). *8/10*
        - foi mostrado no vídeo a aplicação de encapsulamento, herança e polimorfismo;
    Organização e clareza do código (10 pontos). *8/10*
        - o código é comentado em toda parte, visando deixar claro as funcionalidades de métodos e os passos;
    Tratamento de exceções de forma apropriada (10 pontos) *9/10*
        - todo o sistema está com try catch para interceptar exceções de input ou de ações do próprio sistema;
            ex: um input diferente das opções vai gerar um "Insira uma opção válida!"
            ex: cliente tentar alugar um filme sem saldo na carteira vai gerar um "Saldo insuficiente";

-> NOTA FINAL: 86 <-

(sendo otimista)