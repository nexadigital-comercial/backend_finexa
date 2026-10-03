# Finexa - Backend

Backend da **Finexa**, uma aplicação voltada para gerenciamento e organização financeira.

Este projeto está sendo desenvolvido utilizando **Java** e **Spring Boot**, seguindo uma arquitetura organizada em camadas para facilitar a manutenção, evolução e escalabilidade da aplicação.

---

## 🚀 Tecnologias

- Java
- Spring Boot
- Maven
- Spring Data JPA
- Spring Security
- Spring Validation
- MySQL
- REST API
- BCrypt

---

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
    │           │   ├── UsuarioCadastroDTO.java
    │           │   └── UsuarioRespostaDTO.java
    │           │
    │           ├── FinexaEntity/
    │           │   └── UsuarioEntity.java
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
