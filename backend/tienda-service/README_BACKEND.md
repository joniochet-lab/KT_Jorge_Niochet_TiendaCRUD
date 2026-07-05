# tienda-service

Microservicio REST Spring Boot para CRUD completo de `Producto`.

## Ejecutar

```powershell
mvn spring-boot:run
```

## Endpoints

```text
GET    /api/productos
GET    /api/productos/{id}
POST   /api/productos
PUT    /api/productos/{id}
DELETE /api/productos/{id}
```

## H2 Console

```text
http://localhost:8080/h2-console
```

Datos:

```text
JDBC URL: jdbc:h2:mem:tiendadb
User: sa
Password: dejar vacío
```
