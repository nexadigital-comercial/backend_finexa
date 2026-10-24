# Finexa — Backend

Backend da **Finexa**, uma aplicação voltada para gerenciamento e organização financeira, desenvolvida com Java e Spring Boot.

O projeto utiliza uma arquitetura organizada em camadas, com foco em separação de responsabilidades, segurança, validação de dados e padronização das respostas da API REST.

## 🚀 Tecnologias

- **Java** — linguagem de programação
- **Spring Boot** — estrutura principal da aplicação
- **Maven** — gerenciamento de dependências e build
- **Spring Data JPA** — persistência e acesso a dados
- **Spring Security** — autenticação e proteção dos endpoints
- **Spring Validation** — validação dos dados recebidos
- **MySQL** — banco de dados relacional
- **REST API** — comunicação via HTTP
- **BCrypt** — codificação segura de senhas

## ✨ Funcionalidades implementadas

- Cadastro de usuários por meio da API REST.
- Validação dos dados enviados no cadastro.
- Verificação de e-mail já cadastrado.
- Armazenamento de senhas com BCrypt.
- Persistência de usuários no MySQL.
- Proteção dos endpoints com Spring Security.
- Proteção contra requisições CSRF.
- Configuração de CORS para comunicação com o frontend.
- Tratamento centralizado de exceções.
- Respostas de erro padronizadas com status HTTP, código identificador, mensagem e detalhes de validação dos campos.

## 📡 API REST

### Cadastro de usuário

**Endpoint:** `POST /usuarios`

Recebe os dados de cadastro em formato JSON.

Exemplo de requisição:

```json
{
  "nome": "João Silva",
  "email": "joao@example.com",
  "senha": "SenhaSegura123"
}
```

Os campos devem respeitar as regras de validação definidas no backend.

Em caso de sucesso, a API retorna os dados públicos do usuário cadastrado, sem expor sua senha.

### Respostas HTTP

A API utiliza os códigos HTTP para indicar o resultado das operações.

| Status | Significado |
|---|---|
| `200 OK` | Requisição concluída com sucesso, quando aplicável |
| `201 Created` | Recurso criado, quando configurado pelo endpoint |
| `400 Bad Request` | Dados enviados inválidos |
| `401 Unauthorized` | Requisição não autenticada |
| `409 Conflict` | E-mail já cadastrado |
| `500 Internal Server Error` | Erro interno no processamento |

Os status efetivamente retornados dependem da configuração de cada endpoint e dos handlers de exceção.

### Formato das respostas de erro

As exceções tratadas pela aplicação seguem um formato padronizado por meio do `ErroRespostaDTO`.

Exemplo de erro de validação:

```json
{
  "timestamp": "2026-10-24T14:30:00",
  "status": 400,
  "code": "VALIDATION_ERROR",
  "message": "Existem campos inválidos.",
  "fieldErrors": {
    "nome": "O nome é obrigatório."
  }
}
```

Principais códigos de erro:

- `VALIDATION_ERROR` — falha na validação dos campos.
- `EMAIL_ALREADY_EXISTS` — e-mail já cadastrado.
- `DATABASE_ERROR` — erro interno relacionado à persistência de dados.

O campo `fieldErrors` contém os erros associados aos campos do formulário quando aplicável. Para erros sem detalhes por campo, esse atributo pode ser `null`.

## 🔐 Segurança

O backend utiliza recursos do Spring Security para proteger a aplicação:

- **Spring Security:** gerenciamento das regras de acesso aos endpoints.
- **CSRF:** proteção contra requisições forjadas, utilizando token CSRF.
- **BCrypt:** codificação das senhas antes de armazená-las.
- **CORS:** configuração das origens autorizadas para comunicação com o frontend.

O frontend e o backend devem utilizar origens consistentes com a configuração de CORS e o fluxo de CSRF. Durante o desenvolvimento local, por exemplo, evite alternar entre `localhost` e `127.0.0.1` na mesma sessão.

## 📁 Estrutura do projeto

O projeto segue uma organização baseada em responsabilidades:

```text
src/
└── main/
    ├── java/
    │   └── nexadigital/
    │       └── finexa/
    │           ├── FinexaConfig/
    │           │   ├── CorsConfig.java
    │           │   ├── GlobalExceptionHandler.java
    │           │   ├── PasswordConfig.java
    │           │   └── SecurityConfig.java
    │           │
    │           ├── FinexaController/
    │           │   └── UsuarioController.java
    │           │
    │           ├── FinexaDTO/
    │           │   ├── ErroRespostaDTO.java
    │           │   ├── UsuarioCadastroDTO.java
    │           │   └── UsuarioRespostaDTO.java
    │           │
    │           ├── FinexaEntity/
    │           │   └── UsuarioEntity.java
    │           │
    │           ├── FinexaException/
    │           │   └── EmailJaCadastradoException.java
    │           │
    │           ├── FinexaRepository/
    │           │   └── UsuarioRepository.java
    │           │
    │           ├── FinexaService/
    │           │   └── UsuarioService.java
    │           │
    │           └── FinexaApplication.java
    │
    └── resources/
        └── application.properties
```

## ⚙️ Como executar o projeto

### Pré-requisitos

- Java compatível com a versão utilizada no projeto.
- Maven, caso não utilize o Maven Wrapper.
- MySQL instalado e em execução.

### 1. Configure o banco de dados

Crie um banco de dados para a aplicação e configure as propriedades de conexão no arquivo `src/main/resources/application.properties`.

Exemplo ilustrativo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finexa
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Ajuste as propriedades conforme sua configuração local. Os nomes das variáveis de ambiente devem ser definidos no ambiente de execução, ou substituídos pela configuração que você utiliza no projeto.

**Importante:** não envie senhas reais, credenciais ou outros segredos para o repositório.

### 2. Execute a aplicação

Pelo Maven Wrapper, no Windows:

```bash
mvnw.cmd spring-boot:run
```

Ou, com Maven instalado:

```bash
mvn spring-boot:run
```

A aplicação será iniciada na porta configurada no projeto, que normalmente é `8080` durante o desenvolvimento local.

## 🛠️ Desenvolvimento

O projeto está em desenvolvimento contínuo. A estrutura atual permite evoluir a API mantendo separadas as responsabilidades de controle das requisições, regras de negócio, persistência, segurança e tratamento de erros.

## 📌 Próximas evoluções

As próximas funcionalidades serão definidas conforme a evolução do projeto, incluindo a expansão dos recursos financeiros e o aprimoramento dos mecanismos de autenticação e gerenciamento de usuários.

---

Desenvolvido por **Nexa Digital**.
