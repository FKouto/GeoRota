# Georota Project

## Descrição
O projeto Georota é uma aplicação Spring Boot que utiliza a API do Google para calcular distâncias e rotas entre diferentes pontos geográficos. Ele permite adicionar locais, conectar esses locais e obter a melhor rota entre dois pontos.

## Tecnologias Utilizadas
- Java
- Spring Boot
- Maven
- Google APIs (Directions e Distance Matrix)

## Configuração
1. Clone o repositório:
    ```sh
    git clone <URL_DO_REPOSITORIO>
    ```
2. Navegue até o diretório do projeto:
    ```sh
    cd georota
    ```
3. Compile e execute o projeto usando Maven:
    ```sh
    ./mvnw spring-boot:run
    ```

## Rotas

### Adicionar Local
- **Descrição:** Adiciona um novo local.
- **Endpoint:** `/locais`
- **Método:** `POST`
- **Parâmetros:**
  - `nomePonto` (String): Nome do ponto.
  - `logradouro` (String): Endereço do ponto.
- **Exemplo de Requisição:**
    ```json
    {
      "nomePonto": "Ponto A",
      "logradouro": "Rua A, 123"
    }
    ```

### Conectar Elos
- **Descrição:** Conecta dois locais.
- **Endpoint:** `/elos`
- **Método:** `POST`
- **Parâmetros:**
  - `nomeOrigem` (String): Nome do ponto de origem.
  - `nomeDestino` (String): Nome do ponto de destino.
- **Exemplo de Requisição:**
    ```json
    {
      "nomeOrigem": "Ponto A",
      "nomeDestino": "Ponto B"
    }
    ```

### Obter Conexões
- **Descrição:** Obtém todas as conexões entre os locais.
- **Endpoint:** `/conexoes`
- **Método:** `GET`
- **Resposta:**
    ```json
    [
      {
        "Ponto": "Ponto A",
        "Conexões": [
          {
            "Nome": "Ponto B",
            "Distância": "5 km"
          }
        ]
      }
    ]
    ```

### Obter Melhor Rota
- **Descrição:** Obtém a melhor rota entre dois pontos.
- **Endpoint:** `/rota`
- **Método:** `GET`
- **Parâmetros:**
  - `nomeOrigem` (String): Nome do ponto de origem.
  - `nomeDestino` (String): Nome do ponto de destino.
  - `modo` (String): Modo de transporte (ex: driving, walking).
- **Exemplo de Requisição:**
    ```sh
    /rota?nomeOrigem=Ponto+A&nomeDestino=Ponto+B&modo=driving
    ```
- **Resposta:**
    ```json
    {
      "rota": "Instruções detalhadas da rota"
    }
    ```