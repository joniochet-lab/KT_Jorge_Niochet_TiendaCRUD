# KT_Jorge_Niochet_TiendaCRUD

Proyecto para la **Evaluación Autónoma DSY1105 · Desarrollo de Aplicaciones Móviles**.

La aplicación implementa un CRUD completo de productos usando:

- **Kotlin moderno**.
- **Jetpack Compose** sin XML de layouts.
- **Arquitectura MVVM**.
- **StateFlow / MutableStateFlow** para estado reactivo.
- **Retrofit** para consumo de API.
- **Microservicio Spring Boot** con base H2 en memoria.
- **Pruebas unitarias JUnit** sobre ViewModel.
- Proyecto preparado para generar APK desde Android Studio.

## Alcance funcional

Modelo principal: `Producto`.

CRUD completo:

- Listar productos.
- Agregar producto.
- Modificar producto.
- Eliminar producto.
- Ver detalle de producto.

## Estructura

```text
KT_Jorge_Niochet_TiendaCRUD/
├── android-app/
│   └── TiendaCrudCompose/
├── backend/
│   └── tienda-service/
├── docs/
├── scripts/
└── README.md
```

## Ejecución rápida

### 1. Levantar backend

```powershell
cd backend\tienda-service
mvn spring-boot:run
```

Probar API:

```text
http://localhost:8080/api/productos
```

### 2. Ejecutar Android

Abrir en Android Studio:

```text
android-app/TiendaCrudCompose
```

La app usa:

```kotlin
http://10.0.2.2:8080/
```

para conectarse desde el emulador Android al backend local.

## Generar APK

En Android Studio:

```text
Build > Build App Bundle(s) / APK(s) > Build APK(s)
```

Salida esperada:

```text
app/build/outputs/apk/debug/app-debug.apk
```
