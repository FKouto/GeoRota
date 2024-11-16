# Descrição das Alterações

### 1. **Refatoração do Modelo de Dados**
- **Classes de Localização**:
    - No primeiro código, a estrutura de dados usada para representar um ponto ou local era simples, com o conceito de `Ponto` e `Cidade`, sem muita complexidade para armazenar e conectar pontos.
    - No segundo código, a estrutura foi expandida com mais classes especializadas, como `Rua`, `Ponto`, e `Local`, que representam de maneira mais detalhada os locais, com atributos como `nomePonto` (nome do ponto de referência) e `logradouro` (endereço da rua). Além disso, a classe `Local` agora inclui conexões com outros pontos (`conexao`), permitindo manipulações mais complexas (como empilhamento ou enfileiramento de pontos).

### 2. **Mudança na Representação de Locais e Conexões**
- **Conexões Entre Locais**:
    - O conceito de "conexão" entre pontos foi introduzido. Cada `Local` pode agora se conectar a outros locais com o método `criarConexaoLocal`, permitindo uma relação bidirecional entre os pontos.
    - Isso facilita o gerenciamento de uma rede de locais conectados, útil para simulações de rotas ou cálculos de distâncias.

### 3. **Uso de Estruturas de Dados Diferentes**
- **Pilha e Fila**:
    - O segundo código introduziu o uso de pilha (LIFO) e fila (FIFO) para gerenciar locais temporários, oferecendo uma maneira de organizar e manipular os pontos de interesse de acordo com diferentes critérios de processamento.
    - Foram criados os métodos `adicionarLocalTemporario` para adicionar pontos à pilha e `adicionarLocalFila` para adicionar pontos à fila. Também existem métodos para remover locais dessas estruturas (`removerLocalTemporario` e `removerLocalFila`).

### 4. **Árvore Binária para Busca Rápida**
- **ArvoreBinaria**:
    - O primeiro código não usava estruturas de dados avançadas como árvores binárias.
    - No segundo código, foi implementada uma árvore binária (usando a classe `ArvoreBinaria`) para armazenar os locais de maneira ordenada, permitindo uma busca mais eficiente. Cada `Local` é adicionado à árvore binária com base no nome do ponto, e o método `buscarLocalArvore` permite buscar um local de forma rápida na árvore.

### 5. **Integração com a API do Google (DistanceMatrix)**
- **Cálculo de Distâncias**:
    - No segundo código, foi adicionado o uso da API Google Distance Matrix (`DistanceMatrix`) para calcular as distâncias entre os locais conectados, exibindo essas distâncias durante a impressão das conexões.
    - A funcionalidade de calcular a distância entre dois locais foi introduzida, feita por meio de requisições à API do Google Maps, retornando a distância entre dois pontos com base em seus endereços.

### 6. **Métodos de Busca**
- **Busca Linear**:
    - O método de busca linear (método `buscaLinear`) foi mantido, onde a cidade é percorrida sequencialmente para encontrar um ponto específico. Esse método ainda é utilizado para buscar locais de forma direta.
- **Busca na Árvore Binária**:
    - No segundo código, a busca também pode ser realizada na árvore binária, através do método `buscarLocalArvore`, proporcionando uma maneira mais eficiente de localizar locais na estrutura.

### 7. **Refatoração das Classes de Local e Ponto**
- **Classes Mais Específicas**:
    - O código foi refatorado para ser mais modular e específico. A classe `Rua` foi criada como uma base para os pontos de referência, enquanto `Ponto` é uma classe abstrata que estende `Rua` e serve como base para locais específicos, como o `Local`.
    - Isso melhora a organização e facilita a extensão de novos tipos de pontos ou locais no futuro, além de permitir uma melhor reutilização do código.

### 8. **Mudanças no Fluxo de Execução**
- **Adição de Locais**:
    - No novo código, os locais são adicionados à cidade de maneira mais detalhada. Os locais são conectados, as distâncias são calculadas, e os pontos de interesse são manipulados por pilha e fila.
- **Manipulação de Locais com Pilha e Fila**:
    - Além da impressão dos locais e suas conexões, a aplicação agora pode manipular os locais usando pilha e fila. A pilha segue o comportamento LIFO (último a entrar, primeiro a sair), enquanto a fila segue FIFO (primeiro a entrar, primeiro a sair).

### 9. **Arquitetura de Serviços Mais Modular**
- **Modularização e Serviços**:
    - No segundo código, foi melhorada a modularização e a arquitetura do serviço. As funções de cálculo de distância, manipulação de dados e buscas estão organizadas de forma que as responsabilidades estão mais separadas. A `CidadeMapa` controla a lógica da cidade e os locais, enquanto a classe `DistanceMatrix` cuida das interações com a API do Google.
    - Além disso, as classes de locais, como `Local`, agora encapsulam atributos e métodos relacionados aos pontos de referência, tornando o código mais coeso e fácil de entender.

### 10. **Uso de Bibliotecas de Terceiros**
- **Lombok**:
    - O uso do **Lombok** foi introduzido nas classes como `Ponto` e `Local`. Com isso, a geração de getters e setters foi simplificada, reduzindo a quantidade de código boilerplate.
