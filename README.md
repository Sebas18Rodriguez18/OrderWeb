# 🛠️ Order Web 

**Order Web** es una aplicación web desarrollada con **Spring Boot**, **Thymeleaf** y **MySQL** para gestionar órdenes de trabajo, actividades y técnicos. Cuenta con funcionalidad para exportar listados a PDF y Excel.
---
## 📋 Funcionalidades principales
<picture> <img align="right" src="https://certificadossena.net/wp-content/uploads/2022/10/logo-sena-verde-complementario-svg-2022.svg" width="250px"></picture>

- 📦 **Gestión de entidades**:
  - Órdenes (`Order`)
  - Actividades (`Activity`)
  - Técnicos (`Technician`)
  - Roles, causas y observaciones

- 📅 Manejo completo de fecha de legalización

- 💾 Exportación de listados a:
  - **PDF** (iText): con estilos, títulos destacados y tabla zebra
  - **Excel** (Apache POI): archivos `.xlsx` bien formateados

- 🧭 Navegación intuitiva por secciones: órdenes, actividades, técnicos y roles.

---

## 🚀 Comenzando

### Requisitos previos

- JDK 17
- Maven
- MySQL Server (o MariaDB)

### Instalación

1. Clona el repositorio:
   ```bash
   https://github.com/Sebas18Rodriguez18/OrderWeb.git
   cd orderWeb
   ```
2. Crea la base de datos en MySQL:
   En el repositorio se dejará el archivo SQL ("bd_order")
3. Ajusta `application.properties`:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/orderweb
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   ```
4. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```
5. Accede en tu navegador:
   ```
   http://localhost:8080/
   ```

---

## 📌 Estructura del proyecto

```
src/
├── main/
│   ├── java/com/order/orderWeb/
│   │   ├── model/         # Entidades JPA (Order, Activity, Technician, Role…)
│   │   ├── repository/    # Interfaces Spring Data JPA
│   │   └── controller/    # Controladores Thymeleaf (ViewOrder, ViewActivity…)
│   └── resources/
│       ├── templates/     # Vistas HTML (home, order, activity, technician…)
│       └── application.properties
└── pom.xml                # Dependencias: Spring Boot, Thymeleaf, MySQL, iText, POI…
```

---

## 📄 Exportar listados

- **PDF**: botón “Exportar PDF” genera un reporte estilizado con iText.
- **Excel**: genera `.xlsx` usando Apache POI con tablas configuradas y estilos.

---

## 🧩 Personalizaciones

- Los estilos de PDF pueden editarse desde los métodos `exportPdf(...)` en cada controlador.
- Las columnas que se muestran se configuran en los archivos HTML `*.html` y métodos Java correspondientes.

---

## 🛠️ Buenas prácticas

- Controladores `@Controller` con Thymeleaf (vistas MVC)
- Endpoints:
  - `/view/order`, `/viewA`, `/viewT`
  - Menú lateral consistente
- Exportación gestionada con iText y Apache POI
- Tablas HTML responsivas con Bootstrap 5

---

## Actividad SENA
Este proyecto fue desarrollado como actividad del <a href="https://www.sena.edu.co/es-co/Paginas/default.aspx" target="_blank">SENA (Servicio Nacional de Aprendizaje)</a> <a href="https://sena-clem.blogspot.com" target="_blank">CLEM (Centro Latinoámericano de Especies Menores)</a> dentro del programa de análisis y desarrollo de software (ADSO).


---

## Connect with Me
<p align="center">
 <img src="https://github.com/Sebas18Rodriguez18/Sebas18Rodriguez18/blob/main/logo-page.png" width="210">
<p align="center">
  <a href="mailto:sr1290853@gmail.com" target="_blank">
    <img src="https://img.shields.io/badge/Email-D14836?style=for-the-badge&logo=gmail&logoColor=white" alt="Email">
  </a>
  &nbsp;
  <a href="https://t.me/SebasDevCruz26" target="_blank">
    <img src="https://img.shields.io/badge/Telegram-26A5E4?style=for-the-badge&logo=telegram&logoColor=white" alt="Telegram">
  </a>
  &nbsp;
  <a href="https://www.instagram.com/srcj_26/" target="_blank">
    <img src="https://img.shields.io/badge/Instagram-E4405F?style=for-the-badge&logo=instagram&logoColor=white" alt="Instagram">
  </a>
  &nbsp;
  <a href="https://www.linkedin.com/in/sebastian-cruz-43b733343/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn">
  </a>
  &nbsp;
  <a href="https://github.com/Sebas18Rodriguez18" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
  </a>
</p>

<p align="center">
  <img src="https://media.giphy.com/media/jpVnC65DmYeyRL4LHS/giphy.gif" width="300">
</p>
