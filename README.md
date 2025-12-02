<img src="cruzeiro00.png" alt="Logo do Projeto" width="150" />

# Projeto Vittalis

Vittalis é uma aplicação web monolítica que integra backend em Java (Spring Boot) e frontend em JavaScript, fornecendo uma plataforma integrada para gestão operacional de cruzeiros. O sistema automatiza processos como check-in e check-out, gerencia parcerias comerciais e oferece personalização da experiência do passageiro, aumentando a eficiência e satisfação.

## Introdução

O projeto visa superar os desafios de processos manuais e sistemas isolados no cruzeiro Vittalis, como filas e atrasos em embarque/desembarque, além de melhorar a gestão de serviços parceiros. Centraliza dados, automatiza operações e utiliza inteligência artificial para recomendações personalizadas, otimizando tanto a operação quanto a experiência dos clientes.

## Arquitetura

Vittalis segue o padrão MVC com Spring Boot no backend e um SPA em JavaScript no frontend:

- **Model**: representa entidades e lógica de negócios, com persistência via JPA.
- **View**: interface frontend consumindo APIs REST.
- **Controller**: expõe endpoints REST, gerencia fluxo entre Model e View.

Camadas organizadas em serviços (lógica), repositórios (acesso a dados), entidades e configurações, facilitando manutenção, escalabilidade e testes.

## Tecnologias

- Java 17+
- Spring Boot
- Maven
- JavaScript (SPA)
- Node.js / npm ou yarn
- PostgreSQL (recomendado)
- Docker / Docker Compose (opcional)

## Estrutura básica

- `src/main/java` — backend Java
- `src/main/resources` — configurações
- `src/test/java` — testes backend
- `frontend/` — frontend JavaScript

## Como rodar

1. Configure variáveis ambiente para banco de dados:
   - `SPRING_DATASOURCE_URL`
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`
2. Backend:
   ```bash
   mvn clean package
   java -jar target/*.jar
   ```
3. Frontend:
   ```bash
   cd frontend
   npm install
   npm start
   ```

## Contribuição

- Abra issues para bugs ou melhorias.
- Faça pull requests para a branch principal.
- Não inclua credenciais nos commits.

## Licença

Adicionar arquivo LICENSE conforme a licença escolhida (exemplo: MIT).
