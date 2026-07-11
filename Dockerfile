FROM gradle:8.5-jdk17

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

RUN chmod +x gradlew


COPY run-gatling.sh .
RUN chmod +x run-gatling.sh

ENTRYPOINT ["./run-gatling.sh"]

##ENTRYPOINT ["./gradlew"]

##CMD ["gatlingRun", "--simulation", "simulations.SecondSimulation"]