# PrototypeProdiit
Repositorio de pruebas de API para Prodiit

- [Configuración](#configuración)
- [User](#user)
- [Company](#company)
- [Role](#role)
- [Login](#login)
- [AddCompanyToUser](#addcompanytouser)

## Configuración
# Configuración de Proyecto Spring Boot con MySQL

En esta documentación, se describe la configuración básica de un proyecto Spring Boot que utiliza Maven 3.1.4 y JDK 17, junto con una base de datos MySQL. Asegúrate de tener estas herramientas y tecnologías instaladas antes de comenzar.

##Instalacion Jdk

Ver video de instalación y configuración del JDK

## Configuración de la Base de Datos

Para configurar la conexión a la base de datos MySQL, debes proporcionar las siguientes propiedades en el archivo `application.properties` de tu proyecto:

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ProdiitAPP
spring.datasource.username=root
spring.datasource.password=12345
```

spring.datasource.driver-class-name: Define la clase del controlador JDBC de MySQL.
spring.datasource.url: Especifica la URL de conexión a tu base de datos MySQL. Asegúrate de cambiar localhost:3306/ProdiitAPP por la URL correcta de tu base de datos.
spring.datasource.username: Define el nombre de usuario de la base de datos.
spring.datasource.password: Establece la contraseña de la base de datos.
Configuración de JPA
JPA (Java Persistence API) es una especificación de Java que permite el mapeo objeto-relacional (ORM). Para configurar JPA, debes proporcionar las siguientes propiedades en el mismo archivo application.properties:

```properties
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

spring.jpa.database-platform: Define el dialecto de MySQL que se utilizará para generar las consultas SQL. En este caso, se utiliza org.hibernate.dialect.MySQLDialect.
spring.jpa.hibernate.ddl-auto: Controla la generación de tablas en la base de datos. En este ejemplo, se establece en update, lo que significa que Hibernate actualizará automáticamente la estructura de la base de datos si es necesario. Otras opciones comunes son create y validate.
Ejecución del Proyecto
Una vez que hayas configurado tu proyecto Spring Boot, puedes ejecutarlo utilizando Maven y Spring Boot CLI. Utiliza los siguientes comandos:

```bash
mvn spring-boot:run
```
Este comando compilará y ejecutará tu proyecto Spring Boot. Asegúrate de que todos los componentes estén correctamente configurados para que tu aplicación se conecte a la base de datos MySQL.

¡Tu proyecto Spring Boot está listo para funcionar con MySQL! Asegúrate de que tu base de datos esté activa y accesible antes de ejecutar la aplicación.

Si deseas obtener más información sobre la configuración avanzada o la estructura del proyecto Spring Boot, consulta la documentación oficial de Spring Boot en spring.io.

## User

### Crear Usuario
- **Método:** `POST`
- **URL:** `/user`
- **Descripción:** Este endpoint permite crear un nuevo usuario. El cuerpo de la solicitud debe contener los datos del usuario en formato JSON. Llama al método `createAndSaveUser` del servicio `UserService` para guardar el usuario en la base de datos.

### Obtener Todos los Usuarios
- **Método:** `GET`
- **URL:** `/user`
- **Descripción:** Este endpoint devuelve una lista de todos los usuarios registrados en el sistema. Llama al método `getAllUsers` del servicio `UserService`.

### Obtener Usuario por ID
- **Método:** `GET`
- **URL:** `/user/{id}`
- **Descripción:** Este endpoint permite obtener un usuario específico por su ID. El ID del usuario se debe proporcionar en la URL. Llama al método `getUserById` del servicio `UserService`.

### Obtener Usuario por Nombre
- **Método:** `GET`
- **URL:** `/user/name/{name}`
- **Descripción:** Este endpoint devuelve una lista de usuarios que coinciden con el nombre especificado. El nombre se debe proporcionar en la URL. Llama al método `findByname` del servicio `UserService`.

### Obtener Usuario por Correo Electrónico
- **Método:** `GET`
- **URL:** `/user/email/{email}`
- **Descripción:** Este endpoint devuelve una lista de usuarios que coinciden con el correo electrónico especificado. El correo electrónico se debe proporcionar en la URL. Llama al método `findByEmail` del servicio `UserService`.

### Actualizar Usuario
- **Método:** `PUT`
- **URL:** `/user/{id}`
- **Descripción:** Este endpoint permite actualizar los datos de un usuario existente. El ID del usuario se debe proporcionar en la URL, y los nuevos datos del usuario se deben incluir en el cuerpo de la solicitud en formato JSON. Llama al método `updateUser` del servicio `UserService`.

### Borrar Usuario
- **Método:** `DELETE`
- **URL:** `/user/{id}`
- **Descripción:** Este endpoint permite eliminar un usuario por su ID. El ID del usuario se debe proporcionar en la URL. Llama al método `deleteUser` del servicio `UserService`.

## Company

Este controlador maneja operaciones relacionadas con empresas.

### Crear Empresa
- **Método:** `POST`
- **URL:** `/company`
- **Descripción:** Crea una nueva empresa. Debe proporcionar los detalles de la empresa en formato JSON en el cuerpo de la solicitud. Llama al método `createAndSaveCompany` del servicio `CompanyService` para guardar la empresa en la base de datos.

### Obtener Todas las Empresas
- **Método:** `GET`
- **URL:** `/company`
- **Descripción:** Obtiene una lista de todas las empresas registradas en el sistema. Llama al método `getAllCompanies` del servicio `CompanyService`.

### Obtener Empresa por ID
- **Método:** `GET`
- **URL:** `/company/{id}`
- **Descripción:** Obtiene una empresa específica por su ID. El ID de la empresa se debe proporcionar en la URL. Llama al método `getCompanyById` del servicio `CompanyService`.

### Obtener Empresa por Nombre
- **Método:** `GET`
- **URL:** `/company/name/{name}`
- **Descripción:** Obtiene una empresa por su nombre. El nombre de la empresa se debe proporcionar en la URL. Llama al método `getCompanyByName` del servicio `CompanyService`.

### Actualizar Empresa
- **Método:** `PUT`
- **URL:** `/company/{id}`
- **Descripción:** Actualiza los datos de una empresa existente. El ID de la empresa se debe proporcionar en la URL, y los nuevos datos de la empresa se deben incluir en el cuerpo de la solicitud en formato JSON. Llama al método `updateCompany` del servicio `CompanyService`.

### Borrar Empresa
- **Método:** `DELETE`
- **URL:** `/company/{id}`
- **Descripción:** Elimina una empresa por su ID. El ID de la empresa se debe proporcionar en la URL. Llama al método `deleteCompany` del servicio `CompanyService`.

## Obtener el número de sitios asociados a una empresa

Este endpoint permite obtener el número de sitios asociados a una empresa específica. El número de sitios es determinado por el servicio `companyService`, que realiza la lógica para contar los sitios asociados a la empresa.

- **Método:** `GET`
- **URL:** `/{id}/sites`

### Parámetros de la Solicitud
- `id` (UUID): El ID de la empresa de la cual deseas obtener el número de sitios asociados. Debes proporcionar este ID en la URL.

### Respuesta
El endpoint devuelve un entero que representa el número de sitios asociados a la empresa. Este número se puede utilizar para conocer cuántos sitios están vinculados a la empresa específica.

Ejemplo de respuesta exitosa:
```json
200 OK
5
```
En el ejemplo anterior, se devuelve el número 5, lo que indica que la empresa tiene 5 sitios asociados.

En caso de error, el endpoint puede devolver un código de estado HTTP diferente, junto con un mensaje de error.

Nota: Asegúrate de que el servicio companyService esté configurado adecuadamente para proporcionar la cantidad correcta de sitios asociados a la empresa.


## Role

### Crear Rol
- **Método:** `POST`
- **URL:** `/role`
- **Descripción:** Crea un nuevo rol. Debe proporcionar los detalles del rol en formato JSON en el cuerpo de la solicitud. Llama al método `saveRole` del servicio `RoleService` para guardar el rol.

### Obtener Todos los Roles
- **Método:** `GET`
- **URL:** `/role`
- **Descripción:** Obtiene una lista de todos los roles registrados en el sistema. Llama al método `findAll` del servicio `RoleService`.

### Obtener Rol por Nombre
- **Método:** `GET`
- **URL:** `/role/name/{name}`
- **Descripción:** Obtiene un rol por su nombre. El nombre del rol se debe proporcionar en la URL. Llama al método `findByRoleName` del servicio `RoleService`.
## Actualizar Rol
- **Método:** `PUT`
- **URL:** `/role/{id}`
- **Descripción:** Actualiza los datos de un rol existente. El ID del rol se debe proporcionar en la URL, y los nuevos datos del rol se deben incluir en el cuerpo de la solicitud en formato JSON. Llama al método `updateRole` del servicio `RoleService`.

## Borrar Rol
- **Método:** `DELETE`
- **URL:** `/role/{id}`
- **Descripción:** Elimina un rol por su ID. El ID del rol se debe proporcionar en la URL. Llama al método `deleteRole` del servicio `RoleService`.

## Asignar un Rol a un Usuario
- **Método:** `PUT`
- **URL:** `/role/user/{userId}/role/{roleId}`
- **Descripción:** Asigna un rol específico a un usuario. Debes proporcionar el ID del usuario y el ID del rol en la URL. Verifica si el usuario y el rol existen y, si es así, asigna el rol al usuario. Esto se realiza llamando al método `asignarRolAUsuario` del servicio `RoleService`.

## Login
Este controlador maneja operaciones relacionadas con la autenticación de usuarios y la generación de tokens de acceso.

### Autenticación y Generación de Token
- **Método:** `POST`
- **URL:** `/api/auth/login`
- **Descripción:** Autentica al usuario utilizando el correo electrónico y la contraseña proporcionados en el cuerpo de la solicitud (LoginRequest). Si la autenticación es exitosa, se genera un token de acceso (en este ejemplo, se devuelve un token de acceso ficticio). Si la autenticación falla, se devuelve un mensaje de error. Es importante señalar que, en una aplicación real, deberías implementar un sistema de generación de tokens de acceso seguro, como JWT, en lugar de simplemente devolver un mensaje.

#### Parámetros de la Solicitud
- `email` (cadena): Correo electrónico del usuario.
- `password` (cadena): Contraseña del usuario.

#### Respuestas
- **Código 200 (Éxito):** Si la autenticación es exitosa, se devuelve un mensaje de éxito junto con un token de acceso (en este ejemplo, se usa un token ficticio). La respuesta es del tipo `ResponseEntity<String>`.
- **Código 401 (No Autorizado):** Si la autenticación falla, se devuelve un mensaje de error y se establece el código de estado HTTP en 401 (No Autorizado). La respuesta es del tipo `ResponseEntity<String>`.

## AddCompanyToUser
Este controlador maneja operaciones relacionadas con la adición de usuarios a empresas.

### Agregar un Usuario a una Empresa
- **Método:** `POST`
- **URL:** `/api/companies/users/addUser`
- **Descripción:** Agrega un usuario a una empresa. Debes proporcionar un objeto JSON en el cuerpo de la solicitud que contenga el ID del usuario (`userId`) y el ID de la empresa (`companyId`). Si la operación es exitosa, se devuelve un mensaje de éxito. En caso de error, se devuelve un mensaje de error y se establece el código de estado HTTP en 500 (Error Interno del Servidor).

#### Parámetros de la Solicitud
- `userId` (cadena): El ID del usuario que se va a agregar a la empresa.
- `companyId` (cadena): El ID de la empresa a la que se va a agregar el usuario.

#### Respuestas
- **Código 200 (Éxito):** Si la operación es exitosa, se devuelve un mensaje de éxito indicando que el usuario se ha agregado con éxito a la empresa. La respuesta es del tipo `ResponseEntity<String>`.
- **Código 500 (Error Interno del Servidor):** Si ocurre un error durante la operación, se devuelve un mensaje de error que describe el problema. El código de estado HTTP se establece en 500 (Error Interno del Servidor). La respuesta es del tipo `ResponseEntity<String>`.
