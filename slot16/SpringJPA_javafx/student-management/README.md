# Student Management System (JavaFX + Spring Data JPA)

## Yêu cầu môi trường
- JDK 21, Maven 3.9+
- SQL Server (TCP/IP port 1433 bật, tài khoản `sa`)
- IntelliJ IDEA, Gluon Scene Builder (tuỳ chọn)

## Cài đặt
1. Tạo database:
   ```sql
   IF DB_ID('StudentDB') IS NULL CREATE DATABASE StudentDB;
   ```
2. Sửa `ui-module/src/main/resources/application.properties`
   (`spring.datasource.username`, `spring.datasource.password`).

## Build & Test
```bash
mvn clean install
```
Test dùng H2 in-memory, không cần SQL Server.

## Chạy ứng dụng
- IntelliJ: Run `com.example.ui.Launcher`
- Terminal:
  ```bash
  mvn -q clean install -DskipTests
  mvn -f ui-module/pom.xml spring-boot:run
  ```

## Kiến trúc
StudentController → StudentService (interface) → StudentServiceImpl → StudentRepository (JpaRepository) → SQL Server

| Module | Nội dung |
|---|---|
| data-module | Entity, Repository (Spring Data JPA), Service + ServiceImpl |
| ui-module | JavaFX (FXML, CSS), Controller, cấu hình Spring Boot |

## Ảnh chụp
![List]
<img width="749" height="470" alt="image" src="https://github.com/user-attachments/assets/8b240608-409b-4b64-9d70-9bc927e31685" />
![Add]
<img width="750" height="472" alt="image" src="https://github.com/user-attachments/assets/858bf0ec-723e-4715-937e-9165ea6079a4" />
![Validation]
<img width="749" height="473" alt="image" src="https://github.com/user-attachments/assets/61e7a20a-a82e-4d5a-b0f9-ee3084381baa" />
<img width="752" height="475" alt="image" src="https://github.com/user-attachments/assets/fb587c96-0d54-44e5-becd-ca8833ff035b" />
<img width="752" height="472" alt="image" src="https://github.com/user-attachments/assets/d371beef-e746-421d-bf48-4bb97f726864" />
![Delete]
<img width="748" height="471" alt="image" src="https://github.com/user-attachments/assets/1bc5f43f-c557-4d36-8c5b-bb104607be77" />



