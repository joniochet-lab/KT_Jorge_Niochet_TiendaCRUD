# Instalación y prueba en Windows

## Requisitos

- Android Studio estable.
- JDK 17 o superior.
- Apache Maven 3.9.x.
- Emulador Android creado desde Device Manager.

## Verificar herramientas

```powershell
java -version
mvn -version
```

## Levantar microservicio

```powershell
cd backend\tienda-service
mvn spring-boot:run
```

## Probar endpoint

```text
http://localhost:8080/api/productos
```

## Abrir Android

Abrir esta carpeta en Android Studio:

```text
android-app\TiendaCrudCompose
```

Ejecutar en emulador. La URL del backend para emulador es:

```text
http://10.0.2.2:8080/
```

## Si el emulador no conecta

Probar desde Chrome del emulador:

```text
http://10.0.2.2:8080/api/productos
```

Si no responde, verificar firewall o ejecutar:

```powershell
cd "$env:LOCALAPPDATA\Android\Sdk\platform-tools"
.\adb.exe reverse tcp:8080 tcp:8080
```
