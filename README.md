<img src="cruzeiro00.png" alt="Logo do Projeto" width="150" />

# Projeto Vittalis

## Introdução
O sistema automatiza processos como check-in e check-out, gerencia parcerias comerciais e oferece personalização da experiência do passageiro, aumentando a eficiência e satisfação. O projeto visa superar os desafios de processos manuais e sistemas isolados no cruzeiro Vittalis, como filas e atrasos em embarque/desembarque, além de melhorar a gestão de serviços parceiros. Centraliza dados, automatiza operações, otimizando tanto a operação quanto a experiência dos clientes.

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
- JavaScript
- MySQL
  
## Estrutura básica

- `src/main/java` — backend Java
- `src/main/resources` — recursos

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

## Contribuição

- Abra issues para bugs ou melhorias.
- Faça pull requests para a branch principal.
- Não inclua credenciais nos commits.

## Licença

Adicionar arquivo LICENSE conforme a licença escolhida (exemplo: MIT).
