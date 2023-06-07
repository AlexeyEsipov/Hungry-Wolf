"# Hungry_Wolf-Kitchen"
# kitchen
Этот сервис является частью микросервисного приложения по заказу еды Голодный Волк.

## Используемые технологии:
![java](https://img.shields.io/badge/Java--17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot--2.7.3-F2F4F9?style=for-the-badge&logo=spring-boot)
![PostgresSQL](https://img.shields.io/badge/PostgreSQL--14-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-1.18.24-green?style=for-the-badge&logo=lombok&logoColor=white)
![Liquibase](https://img.shields.io/badge/Liquibase-4.9.1-red?style=for-the-badge&logo=liquibase&logoColor=white)

Перед запуском установите:
- PostgreSQL 14
- Java 17
- Apache Maven 3.x
- Apache Kafka 3.4.0

## Запуск приложения

1. Создайте базу данных hungry-wolf-kitchen:
```sql
create database hungry-wolf-kitchen;
```
2. Сервис для работы использует порт 8084.
   Запуск сервиса производится с использованием maven.
   Перейдите в корневой каталог проекта и в командной строке
   выполните команды:
```
    mvn clean install
    mvn spring-boot:run
```
Сервис принимает от сервиса Order заказа,
готовит блюда, сохраняет в базу данных и извещает сервис Order
о готовности заказанных блюд или об отказе. 

