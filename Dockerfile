FROM openjdk:8
ADD target/payment-reminder.jar payment-reminder.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "payment-reminder.jar"]