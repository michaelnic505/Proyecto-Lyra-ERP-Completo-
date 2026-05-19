package com.simplecore.erp.client.config.server;

import com.simplecore.erp.client.gui.notifications.SystemMessages;
import com.simplecore.erp.client.utils.notifications.AppMessages;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Michael F. Sánchez
 * @since 2024
 * @project Lyra Core+ ERP
 * @company SimpleCore Systems
 * @country Republic of Nicaragua
 *
 */
/**
 * ServerController se encarga de manejar la comunicación con el servidor.
 * 
 * Este controlador gestiona los flujos de entrada y salida de objetos 
 * serializados entre el cliente y el servidor. Utiliza un `ObjectOutputStream`
 * para enviar solicitudes y un `ObjectInputStream` para recibir respuestas.
 * 
 * Es importante tener en cuenta que los objetos serializados no se vuelven 
 * a serializar si son enviados más de una vez a través de un mismo flujo. 
 * Para evitar problemas con objetos actualizados (pero previamente enviados),
 * se debe utilizar el método `reset()` del `ObjectOutputStream`.
 */
public class ServerController {

    // Flujo de salida para enviar objetos al servidor
    private final ObjectOutputStream output;

    // Flujo de entrada para recibir objetos del servidor
    private final ObjectInputStream input;

    /**
     * Constructor de la clase ServerController.
     * 
     * @param output El flujo de salida para enviar objetos al servidor.
     * @param input El flujo de entrada para recibir objetos del servidor.
     */
    public ServerController(ObjectOutputStream output, ObjectInputStream input) {
        this.output = output;
        this.input = input;
    }

    /**
     * Envía una solicitud al servidor y recibe la respuesta.
     * 
     * Este método envía un objeto serializado al servidor utilizando el flujo
     * de salida y espera una respuesta del servidor, también serializada.
     * 
     * @param ClientRequest El objeto que se va a enviar como solicitud al servidor.
     * @return La respuesta del servidor, o null si no hay respuesta.
     * @throws IOException Si ocurre un error al escribir o leer los objetos.
     * @throws ClassNotFoundException Si no se encuentra la clase del objeto recibido.
     */
    public Object sendRequest(Object ClientRequest) throws IOException, ClassNotFoundException {
        return getServerResponse(ClientRequest);
    }
   
    public Object sendData(Object ClientRequest) {
        try {
            return getServerResponse(ClientRequest);
        } catch (IOException | ClassNotFoundException ex) {
            new SystemMessages().showErrorMsg(AppMessages.msg(AppMessages.Key.CONNECTION_ERROR));
        }
        return null;
    }

    /**
     * Método privado que maneja la lógica para obtener la respuesta del servidor.
     * 
     * 1. Utiliza `reset()` para limpiar la caché interna del `ObjectOutputStream`
     *    antes de escribir el objeto, asegurando que los objetos modificados 
     *    sean serializados nuevamente.
     * 2. Escribe el objeto de solicitud en el flujo de salida.
     * 3. Lee la respuesta del servidor a través del flujo de entrada.
     * 
     * @param request El objeto de solicitud a enviar al servidor.
     * @return La respuesta del servidor o null si no hay respuesta.
     * @throws IOException Si ocurre un error al escribir o leer los objetos.
     * @throws ClassNotFoundException Si no se encuentra la clase del objeto recibido.
     */
    private Object getServerResponse(Object request) throws IOException, ClassNotFoundException {
        // Muy importante: reset() borra la caché interna del flujo de salida.
        // Sin esto, los objetos con contenido actualizado no serán serializados de nuevo,
        // lo que puede generar inconsistencias al enviar múltiples solicitudes con objetos modificados.
        output.reset(); 

        // Escribe el objeto de solicitud en el flujo de salida y lo envía al servidor.
        output.writeObject(request);
        output.flush();  // Asegura que todos los datos se envíen correctamente.

        // Lee la respuesta del servidor.
        Object response = input.readObject();

        // Si se recibe una respuesta válida, la retornamos.
        if (response != null) {
            return response;
        }

        // Si no hay respuesta, retornamos null.
        return null;
    }

}
