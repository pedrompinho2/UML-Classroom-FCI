<h2><a href= "https://www.mackenzie.br">Universidade Presbiteriana Mackenzie</a></h2>


# Template para Disciplina de Engenharia de Software

O repositório define um modelo (*template*) que deverá ser seguido por cada grupo no projeto.

A seguir, os passos para a preparação do projeto:

1. Um dos membros do grupo deverá realizar um *fork* deste repositório.
2. O dono do repositório deverá convidar os demais membros do grupo para serem colaboradores.
3. O dono do repositório deverá convidar o professor para ser colaborador do repositório.
4. O dono do repositório deverá habilitar o GitHub Pages. Basta seguir o [procedimento para habilitar o GitHub Pages](https://docs.github.com/pt/pages/getting-started-with-github-pages/configuring-a-publishing-source-for-your-github-pages-site), lembrando de escolher em *Source* a opção `/docs` em lugar da opção `/root`.
5. Cada membro do grupo deverá instalar o [Git](https://git-scm.com/downloads).
6. Para a edição do conteúdo deste projeto, sugere-se que cada membro do grupo faça a instalação do [Visual Studio Code](https://code.visualstudio.com/) com as extensões [Markdown All in One](https://marketplace.visualstudio.com/items?itemName=yzhang.markdown-all-in-one) e [GitHub Pull Requests and Issues](https://marketplace.visualstudio.com/items?itemName=GitHub.vscode-pull-request-github). No entanto, cada membro poderá utilizar a IDE de sua preferência.
7. Cada membro do grupo deverá [clonar o repositório do grupo no seu computador](https://learn.microsoft.com/en-us/azure/developer/javascript/how-to/with-visual-studio-code/clone-github-repository?tabs=create-repo-command-palette%2Cinitialize-repo-activity-bar%2Ccreate-branch-command-palette%2Ccommit-changes-command-palette%2Cpush-command-palette).
8. Cada membro do grupo deverá editar o seu próprio nome no arquivo em [/docs/index.md](./docs/index.md), de preferência [criando um novo *branch* e um *pull request*](https://www.youtube.com/watch?v=LdSwWxVzUpo).
9. O dono do repositório deverá editar este arquivo, removendo estas instruções iniciais e preenchendo o restante da página com os dados do projeto do seu grupo.
10. Segurança é imprescindível nas plataforma de hospedagem de repositórios GIT. CUIDADO com exposição de senha e acesso ao repositório.


# Projeto: Serviço de entregas via drones

# Grupo: Isabela Hissa Pinto e Pedro Pinho

# Descrição

Uma startup deseja oferecer entregas rápidas de pequenos pacotes usando drones em áreas urbanas. O sistema precisa gerenciar usuários, pedidos e rotas de drones.

# Documentação

Os arquivos da documentação deste projeto estão na pasta [/docs](/docs), e o seu conteúdo é publicado em **https://<usuario>.github.io/UML-Classroom-FCI/blob/master/docs/index.md**

Projeto: Sistema de Entregas com Drones
Autor(a): Isabela Hissa Pinto (RA: 10441873) e Pedro Pinho
Linguagem: Java
Banco de dados: SQLite

============================================================
1. Visão geral do projeto
============================================================

O sistema simula uma plataforma de entregas utilizando drones.
Os dados principais (clientes, endereços, usuários, drones e pedidos)
são armazenados em um banco SQLite e carregados pelo programa Java
para objetos em memória.

O fluxo principal do programa é orquestrado pela classe Main, que:

- testa a conexão com o banco de dados;
- carrega os drones disponíveis usando DroneDAO e Sistema;
- carrega clientes e seus endereços a partir do banco;
- carrega pedidos a partir da tabela PEDIDOS, reconstruindo objetos
  Cliente, Endereco, Pedido e associando Drones;
- registra esses pedidos dentro do Sistema;
- imprime, de forma organizada, os drones, clientes e pedidos carregados.

============================================================
2. Principais classes
============================================================

- ConexaoBD
  Responsável por criar a conexão JDBC com o arquivo SQLite
  (docs/BancoDeDados.db). Encapsula a URL do banco e o uso do DriverManager.

- Drone
  Representa um drone físico de entrega.
  Atributos principais: id, capacidade em kg, nível de bateria e status
  (por exemplo, DISPONIVEL, INDISPONIVEL).
  Possui toString() para exibição amigável.

- DroneDAO
  Acessa a tabela DRONES no banco de dados.
  Métodos principais:
    - carregarTodos(): lê todos os drones do banco e devolve uma lista.
    - atualizarStatus(id, novoStatus): atualiza o status de um drone.

- Endereco
  Representa um endereço com rua, número, cidade, estado e CEP.
  É usado tanto para cadastro de clientes quanto como destino dos pedidos.

- Cliente
  Representa o cliente que realiza pedidos.
  Armazena nome, login, email, senha (hash) e um Endereco associado.

- Usuario
  Representa o usuário de autenticação ligado ao cliente no banco
  (compartilha o mesmo id no banco, mas é mantido como entidade separada).

- Pedido
  Representa um pedido de entrega.
  Possui cliente, destino, peso em kg, status e, opcionalmente, um drone
  atribuído. Ao chamar setDroneAtribuido(), o status do pedido é ajustado
  conforme a lógica definida na classe.

- PedidoStatus
  Enum que representa os estados possíveis de um pedido
  (por exemplo, CRIADO, EM_ROTA, ENTREGUE).

- Sistema
  Atua como camada de orquestração em memória.
  No construtor, carrega os drones disponíveis via DroneDAO e inicializa
  a lista de pedidos. A classe expõe métodos para acessar a lista de
  drones e a coleção de pedidos gerenciados.

- Main
  Ponto de entrada do programa.
  Faz:
    1) teste da conexão com o banco;
    2) criação de um objeto Sistema (que carrega os drones);
    3) leitura de CLIENTES, ENDERECOS e USUARIOS diretamente do banco;
    4) leitura de PEDIDOS, reconstruindo objetos Pedido com Cliente,
       Endereco e Drone associado quando possível;
    5) registro desses pedidos dentro do Sistema;
    6) exibição dos drones, clientes e pedidos no console.

============================================================
3. Estrutura de diretórios (projeto)
============================================================

UML-Classroom-FCI/
 ├── docs/
 │     BancoDeDados.db       -> arquivo SQLite com as tabelas:
 │                               DRONES, CLIENTES, ENDERECOS,
 │                               USUARIOS, PEDIDOS etc.
 ├── lib/
 │     sqlite-jdbc-3.45.1.0.jar
 │     slf4j-api-2.0.13.jar
 │     slf4j-simple-2.0.13.jar
 └── src/
       classes/
           Cliente.java
           ConexaoBD.java
           Drone.java
           DroneDAO.java
           DroneIndisponivelException.java
           Endereco.java
           Pedido.java
           PedidoStatus.java
           Sistema.java
           Usuario.java
           Main.java

============================================================
4. Pré-requisitos
============================================================

- Java JDK 24 (ou versão equivalente compatível).
- SQLite JDBC: sqlite-jdbc-3.45.1.0.jar (na pasta lib).
- SLF4J:
    - slf4j-api-2.0.13.jar
    - slf4j-simple-2.0.13.jar
  Ambos também na pasta lib.

- Banco de dados SQLite na pasta:
    docs/BancoDeDados.db

============================================================
5. Como compilar o projeto
============================================================

No terminal, dentro da pasta raiz UML-Classroom-FCI:

1) Gerar a pasta de saída para os .class (se ainda não existir):

    mkdir -p out

2) Compilar todas as classes Java, incluindo o caminho das bibliotecas:

    javac -cp "lib/*" -d out src/classes/*.java

Isso irá gerar os arquivos .class dentro da pasta out/.

============================================================
6. Como executar (suprimindo os warnings do SQLite)
============================================================

Para rodar o sistema e suprimir os avisos de acesso nativo do
driver SQLite no JDK mais recente, use:

    java --enable-native-access=ALL-UNNAMED -cp "out:lib/*" Main

Explicação:
- --enable-native-access=ALL-UNNAMED
    Permite que o driver SQLite carregue bibliotecas nativas sem emitir
    warnings de método restrito em java.lang.System.
- -cp "out:lib/*"
    Inclui tanto as classes compiladas (out) quanto todos os .jar
    presentes na pasta lib (sqlite-jdbc e slf4j).

============================================================
7. Fluxo esperado ao executar
============================================================

Ao executar o comando acima, a saída típica no console será algo como:

- Mensagem indicando sucesso na conexão com o banco de dados;
- Listagem de todos os drones cadastrados em DRONES;
- Listagem dos clientes (nome, login, email e endereço) carregados
  a partir das tabelas CLIENTES, ENDERECOS e USUARIOS;
- Leitura dos pedidos existentes na tabela PEDIDOS, reconstruindo
  objetos Pedido em memória e associando drones compatíveis;
- Impressão de cada pedido com cliente, destino, peso, drone atribuído
  (quando houver) e status atual.

Isso demonstra o funcionamento integrado entre:
banco de dados SQLite, camada de acesso a dados (ConexaoBD, DroneDAO),
camada de domínio (Cliente, Endereco, Pedido, Drone) e a camada de
orquestração (Sistema + Main).
