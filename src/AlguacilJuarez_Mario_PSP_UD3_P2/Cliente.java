package AlguacilJuarez_Mario_PSP_UD3_P2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        boolean conectado = false;
        InetAddress ipServidor;

        try {
            do {
                try {
                    System.out.println("Introduzca la ip, ejemplo: Ip de manolito, 111.111.111.111. Porfa sino da error :)");
                    String ip = sc.next();
                    ipServidor = InetAddress.getByName(ip);
                    socketCliente = new Socket(ipServidor, 4444);
                    conectado = true;
                } catch (IOException e){
                    System.out.println("Ip incorrecta o no se encuentra el servidor");
                }
            } while (!conectado);
            entrada = new BufferedReader(
                    new InputStreamReader(socketCliente.getInputStream()));
            // Obtenemos el canal de salida
            salida = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socketCliente.getOutputStream())), true);
        } catch (IOException e) {
            System.err.println("No puede establecer canales de E/S para la conexión");
            System.exit(-1);
        }

        new Thread(new ReceptorMensajes(entrada)).start();

        String linea;

        // El programa cliente no analiza los mensajes enviados por el
        // usuario, simplemente los reenvía al servidor hasta que este
        // se despide con "Adios"
        try {
            while (conectado) {
                // Leo la entrada del usuario
                linea = s.nextLine();
                String msg = linea.trim();
                // Si es "Adios" es que finaliza la comunicación
                if (msg.equalsIgnoreCase("ADIOS")) {
                    salida.println(linea);
                    System.out.println("Se nos fue un grande");
                    conectado = false;
                } else {
                    // Envía el mensaje al servidor
                    salida.println(linea);
                }
            }
            System.out.println("Cliente finalizado.");
            salida.close();
            s.close();
            socketCliente.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    static class ReceptorMensajes implements Runnable {
        private final BufferedReader entrada;

        public ReceptorMensajes(BufferedReader entrada) {
            this.entrada = entrada;
        }

        @Override
        public void run() {
            String mensaje;
            try {
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println("Servidor: " + mensaje);
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}