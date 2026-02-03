# 🚀 ZipUrl - Encurtador de URLs

<p align="center">
  <img src="src/main/resources/static/ZipUrl_Logo.png" alt="ZipUrl Logo" width="120">
</p>

O **ZipUrl** é uma solução de encurtamento de links desenvolvida em java para adiquirir mais experiencia no desenvolvimento backend. O projeto permite transformar URLs longas em links curtos para fácil compartilhamento, gerenciando a persistência de forma eficiente e segura.

## 🛠️ Tecnologias Utilizadas

* **Spring Boot**: Framework para construção da API REST e gerenciamento de recursos.
* **MongoDB**: Banco de dados NoSQL para alta disponibilidade de dados.
* **Docker & Docker Compose**: Containerização para facilitar o setup do ambiente de banco de dados.
* **Spring Data MongoDB**: Abstração de persistência e criação de índices automáticos.
* **JavaScript (Fetch API)**: Frontend dinâmico para comunicação assíncrona com o backend.



## 🌟 Diferenciais Técnicos

* **Redirecionamento HTTP 302**: Implementação direta no Controller para encaminhar o usuário à URL original de forma rápida.
* **Gestão de Expiração (TTL Index)**: Configuração de índice no MongoDB que deleta automaticamente links após uma semana, otimizando o banco de dados.
* **Segurança de Credenciais**: Uso de **Externalized Configuration** com variáveis de ambiente para proteger dados sensíveis.
* **Interface Integrada**: Servindo arquivos estáticos (HTML/CSS/JS) diretamente pelo Tomcat embutido do Spring.

<details>
    docker compose --env-file .env -f docker/docker-compose.yml up -d
    docker compose --env-file .env -f docker/docker-compose.yml down
</details>
