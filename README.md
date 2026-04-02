# Ecommerce Microservices

Este projeto consiste em cinco serviços micro separados para um sistema de ecommerce: Product, User, Inventory, Payment e Order. Cada serviço é responsável por uma funcionalidade específica e se comunica via API REST.

## Pré-requisitos

- Java 21
- Maven 3.6+
- Banco de dados MySQL (configurado remotamente no Aiven)

## Serviços

### Product Service

- **Porta**: 8081
- **Descrição**: Gerencia produtos (criação, consulta).
- **Endpoints**:
  - `POST /products` - Criar produto
  - `GET /products/{id}` - Buscar produto por ID
  - `GET /products` - Listar todos os produtos

### User Service

- **Porta**: 8082
- **Descrição**: Gerencia usuários (criação, consulta).
- **Endpoints**:
  - `POST /user` - Criar usuário
  - `GET /user/{id}` - Buscar usuário por ID

### Inventory Service

- **Porta**: 8083
- **Descrição**: Gerencia estoque dos produtos.
- **Endpoints**:
  - `GET /inventory/{productId}` - Consultar estoque
  - `PUT /inventory/{productId}` - Atualizar estoque
  - `POST /inventory/{productId}/decrease` - Dar baixa no estoque

### Payment Service

- **Porta**: 8084
- **Descrição**: Processa pagamentos.
- **Endpoints**:
  - `POST /payments` - Processar pagamento

### Order Service

- **Porta**: 8085
- **Descrição**: Gerencia pedidos, orquestrando a criação com verificação de estoque e processamento de pagamento.
- **Endpoints**:
  - `POST /order` - Criar pedido
  - `GET /order/{id}` - Consultar status do pedido

## Inicialização dos Serviços

Para executar cada serviço, navegue até a pasta correspondente e execute:

```bash
cd <service-folder>
mvn spring-boot:run
```

Exemplo para iniciar todos em terminais separados:

1. Terminal 1: `cd product && mvn spring-boot:run`
2. Terminal 2: `cd user && mvn spring-boot:run`
3. Terminal 3: `cd inventory && mvn spring-boot:run`
4. Terminal 4: `cd payment && mvn spring-boot:run`
5. Terminal 5: `cd order && mvn spring-boot:run`

Aguarde até que todos os serviços estejam rodando (logs indicarão "Started" ou similar).

## Teste das Requisições

Use os comandos `curl` abaixo para testar as funcionalidades. Substitua `<base-url>` pela URL base de cada serviço (ex: `http://localhost:8081` para product).

### 1. Criar Produto

```bash
curl -X POST http://localhost:8081/products \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Produto Exemplo",
    "descricao": "Descrição do produto",
    "preco": 100.00
  }'
```

**Resposta esperada**: Produto criado com ID.

### 2. Atualizar Estoque do Produto

(Assumindo ID do produto = 1)

```bash
curl -X PUT http://localhost:8083/inventory/1 \
  -H "Content-Type: application/json" \
  -d '{
    "quantity": 10
  }'
```

### 3. Criar Usuário

```bash
curl -X POST http://localhost:8082/user \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@example.com"
  }'
```

**Resposta esperada**: Usuário criado com ID.

### 4. Criar Pedido

(Assumindo ID do usuário = 1, ID do produto = 1)

```bash
curl -X POST http://localhost:8085/order \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "1",
    "items": [
      {
        "productId": "1",
        "quantity": 2
      }
    ]
  }'
```

**Resposta esperada**: Pedido criado, com verificação de estoque, processamento de pagamento e baixa no estoque se aprovado.

### 5. Consultar Status do Pedido

(Assumindo ID do pedido = 1)

```bash
curl -X GET http://localhost:8085/order/1
```

**Resposta esperada**: Detalhes do pedido, incluindo status (PAGO ou CANCELADO).

## Fluxo Completo de Teste

1. Execute os comandos 1 a 3 acima para criar produto, atualizar estoque e criar usuário.
2. Execute o comando 4 para criar o pedido (isso verifica estoque, processa pagamento e confirma).
3. Execute o comando 5 para verificar o status final.

Este fluxo demonstra a integração entre os serviços, com isolamento de persistência e comunicação via REST.</content>
<parameter name="filePath">c:\Users\estev\Downloads\ecommerce\README.md
