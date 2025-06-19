# E-Ticaret Uygulaması - Backend

Bu dizin, e-ticaret uygulamasının Spring Boot ile geliştirilmiş RESTful API kısmını içerir.

## Özellikler

- Kullanıcı, ürün, kategori, sepet ve sipariş yönetimi
- JWT veya benzeri bir yöntemle kimlik doğrulama (varsa)
- PostgreSQL veritabanı entegrasyonu

## Kullanılan Teknolojiler

- Java 17
- Spring Boot (Web, Data JPA)
- PostgreSQL
- Lombok

## Kurulum ve Çalıştırma

1. PostgreSQL veritabanınızı başlatın ve yapılandırın.
2. Gerekli ayarları `application.properties` dosyasında yapın.
3. Proje dizininde aşağıdaki komutu çalıştırın:
   ```bash
   mvn spring-boot:run
   ```
4. API varsayılan olarak [http://localhost:8080](http://localhost:8080) adresinde çalışır.

---

# E-Commerce Application - Backend

This directory contains the RESTful API part of the e-commerce application developed with Spring Boot.

## Features

- Management of users, products, categories, cart, and orders
- Authentication with JWT or similar (if implemented)
- PostgreSQL database integration

## Technologies Used

- Java 17
- Spring Boot (Web, Data JPA)
- PostgreSQL
- Lombok

## Setup and Run

1. Start and configure your PostgreSQL database.
2. Set the required configurations in the `application.properties` file.
3. In the project directory, run:
   ```bash
   mvn spring-boot:run
   ```
4. The API runs at [http://localhost:8080](http://localhost:8080) by default.
