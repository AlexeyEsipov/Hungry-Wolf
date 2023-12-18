"# job4j-Hungry_Wolf-Order" 
# order
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

## Запуск приложения

1. Создайте базу данных hungry-wolf-order:
```sql
create database hungry-wolf-order;
```
2. Сервис для работы использует порт 8081.
   Запуск сервиса производится с использованием maven.
   Перейдите в корневой каталог проекта и в командной строке
   выполните команды:
```
    mvn clean install
    mvn spring-boot:run
```
3. При запущенном сервисе доступна документация по адресу:
```
    http://localhost:8081/swagger-ui/index.html   
```
### Описание:

1. Контроллеры для работы:
   ![Reg page](img/order001.PNG)