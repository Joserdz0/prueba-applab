# Prueba Applab

Este proyecto es una aplicación backend desarrollada en Java, utilizando el framework **Spring Boot**. Su propósito es servir como  plantilla para construir APIs RESTful. Expone algunas rutas que permiten probar la conexión y ejecución del backend.

## Tecnologías Utilizadas

- Java 17+
- Spring Boot
- MySQl


---

## Instalación

### Prerrequisitos

Antes de comenzar asegúrate de tener instalado lo siguiente:

- Java 17 o superior
- Git (opcional, para clonar el repositorio)
- MySQL

### Clonar el repositorio

```bash
git clone https://github.com/joserdz0/prueba-applab.git
cd prueba-applab
```

### Crear DB

Creamos la Base de datos con nombre **prueba-applab**

```bash
CREATE DATABASE IF NOT EXISTS prueba_applab;
```


### Ejecutar
```bash
.\mvnw.cmd spring-boot:run
```

## Rutas del API - TaskController

- `GET - /api/v1/tasks`  
  → Sirve para extraer los datos de **todas las tareas**.

- `GET - /api/v1/tasks/{taskId}`  
  → Sirve para extraer los datos de **una sola tarea** por su ID.

- `POST - /api/v1/tasks`  
  → Sirve para **crear una nueva tarea**.

- `PUT - /api/v1/tasks/{taskId}`  
  → Sirve para **actualizar completamente una tarea existente** (por su ID).

- `PATCH - /api/v1/tasks/{taskId}`  
  → Sirve para **actualizar parcialmente una tarea existente** (solo campos específicos, por su ID).

- `DELETE - /api/v1/tasks/{taskId}`  
  → Sirve para **eliminar una tarea existente** (por su ID).
