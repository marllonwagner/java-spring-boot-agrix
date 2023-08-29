# Primeiro estágio: Construção do pacote da aplicação
FROM maven:3-openjdk-17 AS build-image

WORKDIR /to-build-app

# Copia os arquivos necessários
COPY pom.xml .
COPY src/ ./src/

# Instala as dependências
RUN mvn dependency:go-offline

# Constrói o pacote JAR
RUN mvn package

# Segundo estágio: Imagem final da aplicação
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia o pacote JAR do primeiro estágio
COPY --from=build-image /to-build-app/target/*.jar app.jar

# Expor a porta 8080
EXPOSE 8080

# Ponto de entrada para executar o pacote da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
