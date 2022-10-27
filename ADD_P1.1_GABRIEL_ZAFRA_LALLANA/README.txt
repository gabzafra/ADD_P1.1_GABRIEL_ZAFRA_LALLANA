Configuración inicial de la aplicación:

En la clase MainP11 hay tres variables String que tiene las URL de los archivos locales:
    -   Archivo de datos de los usuarios usersFilePath
    -   Archivo temporal tempFilePath
    -   Archivo de logs logFilePathh

La primera vez que se ejecute el programa creara algunos usuarios de ejemplo. Dado que
se busca trabajar con persistencia estos archivos no se borran entre ejecuciones.

Indice de clases:
- MainP11.java
    Es el punto de entrada de la aplicación. Inicializa el estado. Pide los datos de acceso
    al usuario y según si es un usuario normal le saluda y si es administrador le permite
    desbloquear usuarios.
- Usuario.java
    POJO con la información de un usuario (nombre, clave y número de intentos)
- ViewCreator.java
    Colección de métodos estáticos para mostrar información por consola al usuario. También
    tiene un método para pedir al usuario un Si o un No.
- Db.java
    Controlador del archivo de datos de los usuarios. Tiene métodos CRUD aunque el de borrar
    no se utiliza.
- UserHandler.java
    Maneja el login y el desbloqueo de usuarios.
- Logger.java
    Controlador del archivo de logs. Tiene un método que usa el manejador de usuarios para
    escribir logs cuando un usuario intenta que le autentiquen.
    
A la hora de tratar con las Excepciones he optado por capturarlas, informar por consola y devolver objetos con campos
vacíos (Usuarios con nombre = "") o en algunos casos un booleano.