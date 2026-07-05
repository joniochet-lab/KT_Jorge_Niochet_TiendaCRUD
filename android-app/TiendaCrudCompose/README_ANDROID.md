# KT_Jorge_Niochet_TiendaCRUD

Proyecto desarrollado para la **Evaluación Autónoma del Taller de Actualización Docente DSY1105 - Desarrollo de Aplicaciones Móviles**.

La aplicación implementa un CRUD completo de productos mediante una app Android construida con **Kotlin**, **Jetpack Compose**, arquitectura **MVVM**, estado reactivo con **StateFlow**, consumo de API con **Retrofit** y persistencia a través de un **microservicio Spring Boot** con base de datos H2 en memoria.

---

## 1. Objetivo del proyecto

Desarrollar una aplicación Android funcional que permita gestionar productos de una tienda mediante operaciones CRUD:

- Listar productos.
- Agregar productos.
- Ver detalle de un producto.
- Modificar productos.
- Eliminar productos.

La aplicación consume una API REST propia implementada con Spring Boot.

---

## 2. Alcance funcional

Modelo principal:

```text
Producto
```

Atributos del modelo:

```text
id
nombre
descripcion
categoria
precio
stock
imagenUrl
```

Operaciones implementadas:

| Operación | Estado |
|---|---|
| Listar productos | Implementado |
| Agregar producto | Implementado |
| Modificar producto | Implementado |
| Eliminar producto | Implementado |
| Ver detalle | Implementado |

---

## 3. Tecnologías utilizadas

### Aplicación Android

- Kotlin.
- Jetpack Compose.
- Material 3.
- Navigation Compose.
- ViewModel.
- StateFlow / MutableStateFlow.
- Retrofit.
- Corrutinas.
- JUnit para pruebas unitarias.

### Backend

- Java.
- Spring Boot.
- Spring Web.
- Spring Data JPA.
- H2 Database.
- Maven.

---

## 4. Arquitectura general

```text
App Android
Kotlin + Jetpack Compose
        ↓
ViewModel
        ↓
Repositorio
        ↓
Retrofit
        ↓
API REST Spring Boot
        ↓
Base de datos H2
```

---

## 5. Estructura del proyecto

```text
KT_Jorge_Niochet_TiendaCRUD
│
├── android-app
│   └── TiendaCrudCompose
│       └── app
│           └── src
│               ├── main
│               │   ├── java/cl/duoc/tiendaapp
│               │   │   ├── data
│               │   │   │   ├── remote
│               │   │   │   └── repository
│               │   │   ├── model
│               │   │   ├── navigation
│               │   │   ├── ui
│               │   │   │   ├── screens
│               │   │   │   └── theme
│               │   │   ├── viewmodel
│               │   │   └── MainActivity.kt
│               │   └── res
│               │       └── drawable
│               │           └── imagen_referencial.png
│               └── test
│
├── backend
│   └── tienda-service
│       └── src
│           ├── main
│           │   ├── java/cl/duoc/tienda
│           │   │   ├── config
│           │   │   ├── controller
│           │   │   ├── model
│           │   │   ├── repository
│           │   │   └── service
│           │   └── resources
│           │       └── application.properties
│           └── test
│
├── docs
│   ├── api.http
│   └── rubrica_checklist.md
│
├── scripts
│   └── windows
│
├── README.md
└── INSTALACION_WINDOWS.md
```

---

## 6. Pantallas de la aplicación

La aplicación cuenta con pantallas construidas íntegramente con **Jetpack Compose**.

| Pantalla | Descripción |
|---|---|
| InicioScreen | Pantalla inicial de presentación |
| CatalogoScreen | Lista productos obtenidos desde la API |
| DetalleProductoScreen | Muestra información completa del producto |
| FormularioProductoScreen | Permite agregar o modificar productos |

---

## 7. Endpoints del microservicio

Base URL local:

```text
http://localhost:8080
```

Para el emulador Android:

```text
http://10.0.2.2:8080
```

Endpoints disponibles:

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/productos` | Lista todos los productos |
| GET | `/api/productos/{id}` | Obtiene un producto por ID |
| POST | `/api/productos` | Crea un nuevo producto |
| PUT | `/api/productos/{id}` | Actualiza un producto existente |
| DELETE | `/api/productos/{id}` | Elimina un producto |

---

## 8. Ejecución del backend

Abrir PowerShell en la carpeta:

```text
KT_Jorge_Niochet_TiendaCRUD/backend/tienda-service
```

Ejecutar:

```powershell
C:\tools\apache-maven-3.9.16\bin\mvn.cmd spring-boot:run
```

También puede ejecutarse con:

```powershell
mvn spring-boot:run
```

si Maven está configurado en el PATH del sistema.

Cuando el backend esté activo, se podrá probar la API en el navegador:

```text
http://localhost:8080/api/productos
```

---

## 9. Consola H2

La base de datos H2 puede revisarse desde:

```text
http://localhost:8080/h2-console
```

Datos de conexión:

```text
JDBC URL: jdbc:h2:mem:tiendadb
User: sa
Password: dejar vacío
```

---

## 10. Ejecución de la app Android

Abrir en Android Studio la carpeta:

```text
KT_Jorge_Niochet_TiendaCRUD/android-app/TiendaCrudCompose
```

Pasos:

1. Esperar la sincronización de Gradle.
2. Iniciar el emulador Android.
3. Verificar que el backend esté corriendo.
4. Ejecutar la app con el botón **Run**.

La aplicación está configurada para consumir la API desde el emulador usando:

```text
http://10.0.2.2:8080
```

---

## 11. Pruebas unitarias

El proyecto incluye pruebas unitarias sobre la lógica del ViewModel.

Para ejecutar las pruebas desde Android Studio:

```text
app > src > test
```

También pueden ejecutarse desde Android Studio usando la opción **Run tests**.

Desde terminal, dentro de la carpeta del proyecto Android:

```powershell
cd android-app/TiendaCrudCompose
.\gradlew.bat test
```

---

## 12. Generación de APK

Para generar un APK desde Android Studio:

```text
Build > Build App Bundle(s) / APK(s) > Build APK(s)
```

También puede generarse desde terminal:

```powershell
cd android-app/TiendaCrudCompose
.\gradlew.bat assembleDebug
```

El APK debug queda normalmente en:

```text
app/build/outputs/apk/debug/app-debug.apk
```

Para generar un APK firmado:

```text
Build > Generate Signed App Bundle or APK
```

Seleccionar:

```text
APK
```

Luego seleccionar la variante:

```text
release
```

---

## 13. Relación con la rúbrica

| Criterio | Evidencia en el proyecto |
|---|---|
| C1 · Base Kotlin | Uso de data class, funciones Kotlin, nulabilidad y sintaxis moderna |
| C2 · UI Compose | Pantallas construidas con Jetpack Compose, sin XML de layouts |
| C3 · Arquitectura MVVM | Separación entre UI, ViewModel, Repository, Model y Remote API |
| C4 · Persistencia/API | Backend Spring Boot + API REST + Retrofit |
| C5 · Pruebas unitarias | Pruebas sobre lógica del ViewModel |
| C6 · APK | Proyecto preparado para generar APK desde Android Studio |

---

## 14. Consideraciones importantes

- La app Android requiere que el backend esté encendido para listar, agregar, modificar y eliminar productos.
- El backend usa base de datos H2 en memoria, por lo que los datos se reinician al detener el servidor.
- Para pruebas en emulador se utiliza `10.0.2.2`, que permite acceder al `localhost` del computador desde Android.
- La imagen de productos se maneja con un recurso local referencial para evitar dependencia de imágenes externas.
- El proyecto fue desarrollado con apoyo de documentación técnica e IA como herramienta de asistencia, manteniendo estructura y funcionalidad verificable.

---

## 15. Flujo de uso de la aplicación

1. El usuario abre la aplicación.
2. Se muestra la pantalla de inicio.
3. El usuario ingresa al catálogo de productos.
4. La app consulta los productos al microservicio mediante Retrofit.
5. El usuario puede agregar un nuevo producto.
6. El usuario puede revisar el detalle de un producto.
7. El usuario puede modificar un producto existente.
8. El usuario puede eliminar un producto.
9. Los cambios se reflejan en la API y en la lista de productos.

---

## 16. Flujo técnico de comunicación

```text
Usuario
  ↓
Pantalla Compose
  ↓
TiendaViewModel
  ↓
ProductoRepository
  ↓
RetrofitProductoRepository
  ↓
TiendaApi
  ↓
Microservicio Spring Boot
  ↓
Base de datos H2
```

---

## 17. Prueba rápida de API

Con el backend encendido, abrir en navegador:

```text
http://localhost:8080/api/productos
```

Para consultar un producto específico:

```text
http://localhost:8080/api/productos/1
```

Para probar desde el emulador Android:

```text
http://10.0.2.2:8080/api/productos
```

---

## 18. Entregable

Nombre sugerido del archivo comprimido:

```text
KT_Jorge_Niochet.zip
```

Contenido esperado:

```text
Código fuente completo
Backend Spring Boot
App Android
Documentación
Scripts de apoyo
README.md
```

---

## 19. Autor

```text
Jorge Niochet
Docente Escuela de Informática y Telecomunicaciones
Duoc UC
```

---

## 20. Estado final del proyecto

| Elemento | Estado |
|---|---|
| Aplicación Android | Implementada |
| Jetpack Compose | Implementado |
| CRUD completo de productos | Implementado |
| Microservicio Spring Boot | Implementado |
| Retrofit | Implementado |
| StateFlow | Implementado |
| MVVM | Implementado |
| Pruebas unitarias | Implementadas |
| Generación de APK | Preparado |