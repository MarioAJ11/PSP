# Aplicación Cliente/Servidor de Chat UDP

## Descripción General

Esta es una aplicación simple de chat que utiliza el protocolo **UDP** para permitir la comunicación entre varios clientes y un servidor central. Los clientes pueden enviar mensajes públicos, mensajes privados y solicitar la lista de usuarios conectados. El servidor se encarga de gestionar las conexiones de los clientes, reenviar los mensajes, mantener el historial de mensajes, y garantizar la unicidad de los nombres de usuario.

### Características Principales

- **Comunicación Cliente-Servidor**: Los mensajes enviados por un cliente son reenviados a todos los demás clientes conectados.
- **Mensajes Privados**: Los usuarios pueden enviar mensajes privados a otros usuarios.
- **Nombres de Usuario Únicos**: El servidor garantiza que no haya usuarios con nombres duplicados.
- **Listado de Usuarios**: Los usuarios pueden solicitar la lista de usuarios conectados en cualquier momento.
- **Desconexión Limpia**: Los usuarios pueden desconectarse y notificar al servidor sobre su salida.
- **Historial de Mensajes**: El servidor almacena los últimos 10 mensajes y los envía a los nuevos usuarios al conectarse.
- **Manejo de Comandos Desconocidos**: El servidor gestiona los errores si los usuarios introducen comandos incorrectos.

### Comandos Disponibles

- `/privado (nombre del usuario) (mensaje)`: Enviar un mensaje privado a otro usuario.
- `/usuarios`: Solicitar la lista de usuarios conectados.
- `/salir`: Desconectarse del chat.

## Estructura del Proyecto

El proyecto se compone de las siguientes clases:

1. **Cliente**:
    - Gestiona la conexión de los usuarios, así como el envío y recepción de mensajes desde el servidor. Permite el uso de comandos y mensajes de chat.

2. **GuardarCliente**:
    - Clase que almacena la información de los clientes conectados (nombre, IP y puerto). Mantiene una lista estática de los usuarios conectados.

3. **Servidor**:
    - Controla la recepción de mensajes desde los clientes y los reenvía a otros clientes conectados. También gestiona los mensajes privados, historial de mensajes, desconexión de usuarios y el listado de usuarios.

## Instrucciones de Compilación y Ejecución
1. Se inicia el servidor
2. Se comprueba la IP del servior y se cambia en el cliente en:
    ```java
    // Aunque se podría dejar esta si vas a ejecutar los usuarios en el ordenador.
    private static final byte[] servidor = new byte[]{(byte) 127, (byte) 0, (byte) 0, (byte) 1};
    ```
3. Luego se inicia el cliente, para crear otro usuario puedes abrir la terminal de windows, con cd te mueves a la ruta donde esta el .java y pones "java Clase.java"
4. Luego sería simplemente comprobar las funcionalidades de la aplicación
## Uso del Cliente

1. **Nombre de Usuario**: Al ejecutar el cliente, se te pedirá que ingreses un nombre de usuario.

2. **Enviar Mensajes Públicos**: Simplemente escribe tu mensaje, y este será enviado a todos los usuarios conectados.

3. **Enviar Mensajes Privados**: Usa el comando `/privado (nombre del usuario) (mensaje)` para enviar un mensaje privado a un usuario específico.

4. **Ver Usuarios Conectados**: Usa el comando `/usuarios` para ver la lista de usuarios conectados.

5. **Salir del Chat**: Usa el comando `/salir` para desconectarte del servidor.

## Manejo de Errores

- **Nombres de Usuario Duplicados**: Si el nombre de usuario que eliges ya está en uso, el servidor te pedirá que elijas otro.
- **Comandos Desconocidos**: Si introduces un comando no válido, el servidor te notificará con un mensaje de error.
- **Desconexión**: Cuando un usuario se desconecta, el servidor notifica a los demás clientes sobre su salida.

## Pruebas Realizadas

Se han realizado las siguientes pruebas para garantizar la funcionalidad correcta:

- **Conexión de múltiples usuarios**: Se conectaron varios clientes simultáneamente y se verificó que los mensajes públicos se transmiten correctamente.
- **Mensajes Públicos y Privados**: Los mensajes públicos se muestran a todos los usuarios y los mensajes privados se envían solo al destinatario.
- **Desconexión**: Cuando un cliente se desconecta, los otros usuarios reciben una notificación.
- **Comandos Incorrectos**: El servidor responde adecuadamente cuando se introducen comandos no válidos.


Mario Alguacil Juárez

![img.png](img.png)
