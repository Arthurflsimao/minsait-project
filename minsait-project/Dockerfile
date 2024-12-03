# Etapa de build: usa a imagem do Maven para construir a aplicação
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copia o pom.xml para o contêiner
COPY minsait-project/pom.xml ./  # Caminho relativo para o pom.xml

# Baixa as dependências do Maven
RUN mvn dependency:go-offline

# Copia o diretório src/main
COPY ./minsait-project/src/main /app/src/main

# Faz o build do projeto
RUN mvn clean package -DskipTests

# Etapa de execução: usa uma imagem OpenJDK para rodar a aplicação
FROM openjdk:17-slim

WORKDIR /app

# Copia o JAR gerado para o contêiner
COPY --from=build /app/target/*-runner.jar /app/quarkus-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/quarkus-app.jar"]

