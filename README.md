# Projeto Vittalis

Breve explicação: abaixo está um `README.md` simples com uma introdução ao projeto e a lista de tecnologias. Copie o conteúdo para `README.md`.

```markdown
# Vittalis

Descrição curta
- Vittalis é uma aplicação web monolítica que combina backend em Java (Spring Boot) e frontend em JavaScript. O objetivo é fornecer uma base para desenvolvimento rápido de APIs e interface cliente.

## Introdução
Vittalis reúne um backend em Spring Boot com um frontend em JavaScript (SPA). O projeto foca em arquitetura clara (controller, service, repository), fácil configuração local e integração com banco de dados relacional.

## Tecnologias
- Java 17+
- Spring Boot
- Maven
- JavaScript (frontend)
- Node.js / npm ou yarn
- PostgreSQL (recomendado)
- Docker / Docker Compose (opcional)

## Estrutura básica
- `src/main/java` — código Java (backend)
- `src/main/resources` — configurações (application.yml / application.properties)
- `src/test/java` — testes backend
- `frontend/` — código do cliente (package.json)

## Como rodar (resumo)
1. Configurar variáveis de ambiente de banco de dados (ex.: `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`).
2. Backend:
   - `mvn clean package`
   - `java -jar target/*.jar`
3. Frontend:
   - `cd frontend`
   - `npm install`
   - `npm start`

## Contribuição
- Abrir issues para bugs/feature.
- Criar PRs para a branch principal.
- Não commitar credenciais.

## Licença
Adicionar `LICENSE` conforme necessário (ex.: MIT).
```
