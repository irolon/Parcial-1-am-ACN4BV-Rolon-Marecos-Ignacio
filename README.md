# Synchro App – E‑Commerce de relojes inteligentes

Aplicación móvil para **Android** (Java) que simula una tienda online de relojes inteligentes. Incluye **registro e inicio de sesión** con Firebase, un **catálogo dinámico** traído desde Cloud Firestore e **imágenes cargadas desde una URL** con la biblioteca Glide.

## 📱 Descripción general

* **Inicio de sesión:** login por **nombre de usuario**. La app busca el usuario en Cloud Firestore, obtiene el email asociado y valida la contraseña contra **Firebase Authentication**. Incluye un checkbox “Recordar” y un texto “Olvidar contraseña” a modo ilustrativo.
* **Registro:** desde el botón “Crear cuenta” se accede a una pantalla que da de alta un nuevo usuario en **Firebase Authentication** y guarda sus datos (usuario, nombre, email) en la colección `usuarios` de Firestore.
* **Catálogo:** tras iniciar sesión aparece la lista de smartwatches, que **se lee de la colección `productos` de Firestore** y se muestra con un **RecyclerView**. Cada tarjeta muestra la imagen (descargada desde una URL con **Glide**), el nombre y el precio. También hay un ícono de carrito con un badge que indica la cantidad de productos agregados.
* **Detalle del producto:** al pulsar una tarjeta se navega a la vista detallada con la foto ampliada, el nombre, la descripción y el precio. Tiene un botón **Comprar** (agrega el producto al carrito, muestra un Toast y actualiza el badge) y un botón **Volver**.

## 🧭 Navegación y flujo de la aplicación

1. **Inicio de sesión:** ingresá tu nombre de usuario y contraseña y pulsá **Iniciar Sesión**. Si no tenés cuenta, tocá **Crear cuenta**.
2. **Registro:** completá usuario, nombre, email y contraseña (mínimo 6 caracteres) y pulsá **Registrarme**. La cuenta se crea y volvés al login.
3. **Catálogo:** aparece un encabezado «Bienvenido» con tu nombre y la lista de relojes traída de Firestore. Podés desplazarte verticalmente.
4. **Detalle y compra:** tocá una tarjeta para ver la descripción. Con **Comprar** se suma el producto al carrito (Toast + badge rojo con la cantidad). Con **Volver** regresás al catálogo.

## 🛠️ Tecnologías y librerías utilizadas

- **Java** como lenguaje principal, sobre **Android Studio**.
- **Android SDK 36** (minSdk 29). Requiere Android 10 o superior y conexión a internet.
- **Firebase Authentication** para el registro y el inicio de sesión (email + contraseña).
- **Cloud Firestore** como base de datos NoSQL en la nube (colecciones `usuarios` y `productos`).
- **Glide** para descargar y mostrar las imágenes de los productos desde una URL.
- **RecyclerView** con su Adapter y ViewHolder para la lista dinámica de productos.
- **Material Design Components**, **ConstraintLayout**, **LinearLayout** y **CardView** para las vistas.
- **Recursos XML** organizados en `strings.xml`, `colors.xml` y `dimens.xml`.

## 🚀 Puesta en marcha

Para probar la aplicación en **Android Studio**:

1. Cloná este repositorio y abrilo con **Open an existing project**.
2. Como el proyecto usa **Firebase**, colocá tu propio archivo **`google-services.json`** dentro de la carpeta `app/`. Ese archivo se obtiene desde la consola de Firebase, con **Authentication (Email/Password)** y **Cloud Firestore** habilitados en el proyecto.
3. Esperá a que Gradle resuelva las dependencias (ya están declaradas en `build.gradle.kts`).
4. Elegí un emulador o dispositivo con **Android 10+** y **conexión a internet**, y pulsá **Run**.

## 📁 Estructura del proyecto

```
final-am-acn4b-rolon-marecos-ignacio/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/synchroapp/
│   │   │   ├── MainActivity.java       // Login (busca el usuario en Firestore + Auth)
│   │   │   ├── RegisterActivity.java   // Registro (alta en Auth + guardado en Firestore)
│   │   │   ├── ResultActivity.java     // Catálogo (lee productos de Firestore, RecyclerView)
│   │   │   ├── ProductoAdapter.java    // Adaptador del RecyclerView
│   │   │   ├── CardProduct.java         // Detalle del producto y acción de compra
│   │   │   ├── Producto.java            // Clase modelo (producto)
│   │   │   ├── Usuario.java             // Clase modelo (usuario)
│   │   │   └── CartManager.java         // Lógica del carrito
│   │   ├── res/
│   │   │   ├── layout/                  // activity_main, activity_register,
│   │   │   │                            // activity_result, activity_card_product, item_producto
│   │   │   ├── drawable/                // Logo, fondos e íconos
│   │   │   └── values/                  // strings.xml, colors.xml, dimens.xml, themes
│   │   ├── google-services.json         // Configuración de Firebase (agregar el propio)
│   │   └── AndroidManifest.xml
│   └── build.gradle.kts
└── settings.gradle.kts
```

## 👤 Autor

**Ignacio Rolón Marecos** — Trabajo final de Aplicaciones Móviles.
