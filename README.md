# PrototypeProdiit
Repositorio de pruebas de API para Prodiit

- [Configuración](#configuración)
- [User](#user)
- [Company](#company)
- [Role](#role)
- [Login](#login)
- [AddCompanyToUser](#addcompanytouser)

## Configuración
Descripción de la configuración de tu proyecto.

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
