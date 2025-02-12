package AlguacilJuarez_Mario_PSP_UD3_P2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static final int PORT = 4444;
    private static Multijugador[] colaMultijugador = new Multijugador[3];
    private static int cont = 0;

    public static void main(String[] args) throws Exception {
        // Establece el puerto en el que escucha peticiones
        ServerSocket socketServidor = null;
        try {

            socketServidor = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("No puede escuchar en el puerto: " + PORT);
            System.exit(-1);
        }

        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;

        System.out.println("Escuchando: " + socketServidor);
        try {
            while (true) {
                // Se bloquea hasta que recibe alguna petición de un cliente
                // abriendo un socket para el cliente
                socketCliente = socketServidor.accept();
                System.out.println("Conexión aceptada: " + socketCliente);
                // Para seguir aceptando peticiones de otros clientes
                // se crea un nuevo hilo que se encargará de la comunicación con el cliente
                new HiloAhorcado(socketCliente).start();
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        salida.close();
        entrada.close();
        socketCliente.close();
        socketServidor.close();
    }

    public static boolean colaMultijugador(String nombre, Socket sc) {
        boolean jugador = false;
        for (int i = 0; i < cont; i++) {
            if (colaMultijugador[i].getNombre().equals(nombre)) {
                jugador = true;
                break;
            }
        }
        if (!jugador) {
            if (colaMultijugador.length == 3) {
                Multijugador.setJugadores(colaMultijugador);
                colaMultijugador = new Multijugador[3];
                cont = 0;
                return true;
            } else {
                Multijugador m = new Multijugador(nombre, sc);
                colaMultijugador[cont] = m;
                cont++;
            }
        }
        return false;
    }
}