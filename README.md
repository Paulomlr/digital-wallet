<h1 align="center" style="font-weight: bold;">Digital Wallet API 💳</h1>

<p align="center">
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" />
  <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens" />
  <img src="https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white" />
</p>

<p align="center">
 <a href="#introducao">📌 Introdução</a> • 
  <a href="#tecnologias"> 🛠️ Tecnologias</a> • 
  <a href="#rotas">📍 API Endpoints</a> •
 <a href="#estrutura">📂 Estrutura de Arquivos</a>
</p>

<h2 id="introducao">🚀 Introdução</h2>

A **API de Carteira Digital** foi criada para aprender e aplicar os conceitos da **Clean Architecture**, 
focando em separar regras de negócio de detalhes técnicos, como banco de dados e frameworks. 
O objetivo é desenvolver um código modular, testável e fácil de manter, enquanto se aprende **Docker**, **PostgreSQL** e **Migrations**.

<h2>Funcionalidades</h2>

- 💸 **Transferências**: Fazer transferências de saldo entre os usuários.
- 📊 **Gerenciamento de Transações**: Verificar todas as transações feitas.
- 💰 **Controle de Saldo**: Verificar o saldo disponível nas contas.
- 🔒 **Autenticação e Autorização**: Segurança e controle de acesso com **JWT** e **OAuth2**.
- 🛠️ **Migrações de Banco de Dados**: Gerenciamento de esquemas de banco de dados com **Flyway**.
- 🧪 **Testes**: Testes automatizados com **JUnit** e **Mockito** para as funcionalidades principais.
- 🐳 **Containerização**: Uso de **Docker** e **Docker Compose** para a execução.

<h2 id="tecnologias">🛠️ Tecnologias</h2>

- Java
- Spring Boot
- Spring Security
- JWT e OAuth2
- PostgreSQL
- Lombok
- Flyway 
- Docker e Docker Compose
- Postman
- JUnit e Mockito

---

<h3>Pré-requisitos</h3>

- Docker
- Docker Compose
- Postman (opcional, para testar a API com as coleções exportadas)

---

<h3>Começando</h3>

<h4>Como clonar o projeto:</h4>

```bash
git clone https://github.com/Paulomlr/digital-wallet.git
```

<h4>Como iniciar o projeto:</h4>

```bash
docker-compose up -d --build
``````
<h4>Importar as variáveis de ambiente no postman:</h4>

1. Abra o Postman
2. Vá até a aba Environments
3. Clique em Import
4. No explorador de arquivos, vá até a pasta onde o repositório foi clonado
5. Acesse a pasta postman_exposed e selecione o arquivo **CarteiraDigital_EnvVars.postman_environment.json**

<h4>Importar as rotas da API no postman:</h4>

1. No postman, vá até a aba Collections
2. Clique em import
3. Vá até a pasta postman_exposed dentro do repositório clonado
4. Selecione o arquivo **CarteiraDigital_Rotas.postman_collection.json**


<h2 id="rotas">📍 API Endpoints</h2>

| Routas                                | Descrição                                  |
|--------------------------------------|----------------------------------------------|
| <kbd>POST /users</kbd>               | Criar novo usuário [request details](#create-user)
| <kbd>POST /users/login</kbd>         | Login do usuário (autenticação) [request details](#login)          
| <kbd>GET /wallets/{userId}/balance</kbd> | Obter o saldo da carteira de um usuário [response details](#get-balance)
| <kbd>POST /wallets/{userId}/balance</kbd> | Adicionar saldo à carteira de um usuário [request details](#add-balance)           
| <kbd>POST /transfers/{userId}</kbd>     | Criar uma transferência entre carteiras [request details](#create-transfer)
| <kbd>GET /transfers/{userId}</kbd>   | Listar todas as transferências de um usuário [response details](#list-transfers)          


<h3 id="create-user">POST /users</h3>

**REQUEST**

```json
{
  "name": "Ricardo Mendes Costa",
  "email": "ricardo.costa@gmail.com",
  "password": "Ricardo123@"
}
```

<h3 id="login">POST /users/login</h3>

**REQUEST**

```json
{
    "email": "ricardo.costa@gmail.com",
    "password": "Ricardo123@"
}
```

**RESPONSE**

```json
{
  "userId": "b264bb40-8616-4b4b-88b8-cac0aeac9c97",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ik1lIElqYXggVGVzdCIsImlhdCI6MTYwOTQ2NTYwMH0.Zv9bJYnWshEXaU-lORiv-9xlgnbdUke7BhzjD8Kb1p8"
}
```

<h3 id="get-balance">GET /wallets/{userId}/balance</h3>

**RESPONSE**

```json
{
  "balance": "R$ 2.000,00"
}
```

<h3 id="add-balance">POST /wallets/{userId}/balance</h3>

**REQUEST**

```json
{
  "value": 2000
}
```

<h3 id="create-transfer">POST /transfers/{userId}</h3>

**REQUEST**

```json
{
  "value": 240,
  "destinationWalletCode": "3546-0809-6401-5732"
}
```

<h3 id="list-transfers">GET /transfers/{userId}</h3>

**RESPONSE**

```json
[
    {
        "transferId": "08c361d5-f94c-4f50-bdb3-a2f785e85036",
        "date": "20-02-2025 21:31:33",
        "amount": "R$ 500,00",
        "origin": {
            "walletCode": "8780-6861-1987-9626",
            "ownerName": "Ricardo Mendes Costa"
        },
        "destination": {
            "walletCode": "6087-0323-3001-6361",
            "ownerName": "Carlos Alberto Oliveira"
        }
    }
]
```

<h2 id="estrutura">Estrutura de Arquivos</h2>

```bash
📂 projeto
├── 📂 adapters
│   ├── 📂 dtos
│   ├── 📂 mappers
│   ├── 📂 repositories
├── 📂 application
│   ├── 📂 gateways
│   ├── 📂 usecaseimpl
├── 📂 core
│   ├── 📂 domain
│   ├── 📂 ports
│   ├── 📂 usecase
├── 📂 infra
│   ├── 📂 config
│   ├── 📂 controllers
