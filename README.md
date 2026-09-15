# Sistema-Inventario-Pedidos

## Descripción del proyecto

Aplicación web para el control de inventario y el registro de pedidos de una pequeña empresa, construida como proyecto académico del curso Herramientas de Desarrollo Web. Permite registrar y consultar clientes, productos y pedidos, aplicando un flujo de trabajo colaborativo con Git y GitHub como sistema de control de versiones.

Este proyecto fue desarrollado como evaluación del curso **Herramientas de Desarrollo Web**, con el objetivo de aplicar correctamente conceptos de repositorios, ramas, commits, integración de cambios, resolución de conflictos y trabajo colaborativo.

## Integrantes

- Nicolas Jara Ortiz (U23220925)

## Docente

Luis Fernando Bejarano Arenas

## Curso

Herramientas de Desarrollo Web — Sección 20141

## Funcionalidades principales

### Módulo de Clientes
- Registrar cliente
- Listar clientes

### Módulo de Productos
- Registrar producto
- Listar productos
- Consultar producto por ID
- Actualizar producto
- Eliminar producto

### Módulo de Pedidos
- Registrar pedido
- Consultar pedidos

## Tecnologías utilizadas

### Backend
- Java 21
- Spring Boot 4.1.1
- Spring Web (MVC)
- Spring Data JPA / Hibernate
- Spring Security (configuración base)
- Bean Validation
- Maven

### Base de datos
- PostgreSQL 17

### Testing
- JUnit 5
- Mockito
- MockMvc

### Control de versiones
- Git
- GitHub

## Arquitectura

El backend sigue una arquitectura organizada por capas:

Cliente (peticion HTTP)
↓
Controller
↓
Service
↓
Repository
↓
PostgreSQL


## Estructura del proyecto

Sistema-Inventario-Pedidos/
│
├── backend/
│ ├── src/
│ │ ├── main/java/com/sistemainventario/backend/
│ │ │ ├── controller/
│ │ │ ├── service/
│ │ │ ├── repository/
│ │ │ ├── entity/
│ │ │ ├── dto/
│ │ │ ├── exception/
│ │ │ └── config/
│ │ └── test/java/com/sistemainventario/backend/
│ └── pom.xml
│
├── docs/
│
├── README.md
│
└── .gitignore


## Instalación

### Requisitos previos
- Java 21 (JDK)
- Maven (incluido via wrapper `mvnw`)
- PostgreSQL 17

### Clonar el repositorio

```bash
git clone https://github.com/U23220925/Sistema-Inventario-Pedidos.git
cd Sistema-Inventario-Pedidos
```

### Configurar la base de datos

```sql
CREATE DATABASE sistema_inventario;
```

Editar `backend/src/main/resources/application.properties` con las credenciales locales de PostgreSQL.

## Ejecución

```bash
cd backend
./mvnw spring-boot:run
```

El servidor levanta en `http://localhost:8080`.

### Ejecutar pruebas

```bash
./mvnw test
```

## Base de datos

- Motor: PostgreSQL 17
- Nombre de la base de datos: `sistema_inventario`
- Las tablas se generan automáticamente mediante Hibernate (`ddl-auto=update`)

## Estrategia de ramas

main
│
└── develop
│
├── feature/productos
├── feature/clientes
└── feature/pedidos


- `main`: versión estable del proyecto
- `develop`: integración de los cambios en desarrollo
- `feature/*`: desarrollo individual de cada módulo

## Autores

- Nicolas Jara Ortiz (U23220925)