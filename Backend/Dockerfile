FROM maven AS build
COPY src /home/app/src
COPY pom.xml /home/app/
RUN mvn -f /home/app -DskipTests clean package

FROM openjdk
COPY --from=build /home/app/target/GymTonic-0.0.1-SNAPSHOT.jar /usr/local/lib/api.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/api.jar"]