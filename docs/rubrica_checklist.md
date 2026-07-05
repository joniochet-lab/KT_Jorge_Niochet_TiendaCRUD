# Checklist de rúbrica DSY1105

| Criterio | Evidencia en el proyecto |
|---|---|
| C1 · Base Kotlin | `data class Producto`, funciones en ViewModel, nulabilidad controlada en navegación y formularios. |
| C2 · UI Compose | `InicioScreen`, `CatalogoScreen`, `FormularioProductoScreen`, `DetalleProductoScreen`. CRUD completo en Compose. |
| C3 · MVVM | Capas `ui`, `viewmodel`, `data/remote`, `data/repository`, `model`. |
| C3 · Estado reactivo | `StateFlow` y `MutableStateFlow` en `TiendaViewModel`. |
| C4 · Persistencia/API | Retrofit consume microservicio Spring Boot: `GET`, `POST`, `PUT`, `DELETE /api/productos`. |
| C5 · Pruebas unitarias | `TiendaViewModelTest.kt` con dos pruebas relevantes. |
| C6 · APK | Proyecto preparado para `Build > Build APK(s)` desde Android Studio. |

## Modelo principal

`Producto`:

- id
- nombre
- descripcion
- categoria
- precio
- stock
- imagenUrl

## Pantallas

- Inicio.
- Catálogo/listado.
- Formulario agregar/modificar.
- Detalle.

## CRUD

- Listar: `CatalogoScreen` + `GET /api/productos`.
- Agregar: `FormularioProductoScreen` + `POST /api/productos`.
- Modificar: `FormularioProductoScreen` + `PUT /api/productos/{id}`.
- Eliminar: `CatalogoScreen` y `DetalleProductoScreen` + `DELETE /api/productos/{id}`.
