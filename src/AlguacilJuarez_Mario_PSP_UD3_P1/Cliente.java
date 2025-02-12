package AlguacilJuarez_Mario_PSP_UD3_P1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    private static final byte[] servidor = new byte[]{(byte) 127, (byte) 0, (byte) 0, (byte) 1};
    private static final int puertoServidor = 1234;
    private static boolean conectado = false;

    public static void main(String args[]) throws Exception {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] enviados = new byte[1024];
            byte[] recibidos = new byte[1024];

            InetAddress IPServidor = InetAddress.getByAddress(servidor);
            String mensaje;

            System.out.print("Introduce tu nombre de usuario: ");
            String nombreUsuario = in.readLine();
            enviados = nombreUsuario.getBytes();
            DatagramPacket enviarNombre = new DatagramPacket(enviados, enviados.length, IPServidor, puertoServidor);
            clientSocket.send(enviarNombre);

            Thread receptor = new Thread(() -> {
                try {
                    while (conectado) {
                        DatagramPacket recibido = new DatagramPacket(recibidos, recibidos.length);
                        clientSocket.receive(recibido);
                        String mensajeRecibido = new String(recibido.getData(), 0, recibido.getLength()).trim();
                        System.out.println(mensajeRecibido);
                    }
                } catch (Exception e) {
                    System.out.println("Error al recibir mensajes: " + e.getMessage());
                }
            });

            conectado = true;
            receptor.start();
            System.out.println("Introduce los mensajes o comandos: ");
            while (conectado) {
                mensaje = in.readLine();

                if (mensaje.equalsIgnoreCase("/salir")) {
                    enviarComando(clientSocket, IPServidor, "/salir");
                    conectado = false;
                } else if (mensaje.startsWith("/privado ")) {
                    enviarComando(clientSocket, IPServidor, mensaje);
                } else if (mensaje.equals("/usuarios")) {
                    enviarComando(clientSocket, IPServidor, "/usuarios");
                } else {
                    enviarComando(clientSocket, IPServidor, mensaje);
                }
            }
            try {
                receptor.interrupt();
                clientSocket.close();
            } catch (Exception e) {
                System.out.println("Desconectando del servidor...");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void enviarComando(DatagramSocket socket, InetAddress IPServidor, String mensaje) throws Exception {
        byte[] enviados = mensaje.getBytes();
        DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puertoServidor);
        socket.send(envio);
    }
}

