# first stage
FROM sbtscala/scala-sbt:eclipse-temurin-alpine-21.0.2_13_1.9.9_3.4.1

WORKDIR /app

COPY project ./project
COPY build.sbt ./
COPY . .

RUN sbt assembly

CMD ["java", "-cp", "/app/target/scala-3.3.3/log-aggregeation-assembly-0.1.0-SNAPSHOT.jar", "dev.ardvci.mainConsumer"]
