#FROM openjdk:17
FROM public.ecr.aws/docker/library/openjdk:17
COPY build/libs/flightdb-ms.jar app.jar

EXPOSE 8085
CMD ["java","-jar","app.jar"]