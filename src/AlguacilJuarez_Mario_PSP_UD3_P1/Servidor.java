package AlguacilJuarez_Mario_PSP_UD3_P1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Servidor {
    private static boolean existe = false;
    private static ArrayList<String> historial = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(1234);
        byte[] recibidos = new byte[1024];
        byte[] enviados;
        String cadena;
        System.out.println("Esperando conexiones :)");
        while (true) {

            recibidos = new byte[1024];
            DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
            serverSocket.receive(paqRecibido);
            cadena = new String(paqRecibido.getData(), 0, paqRecibido.getLength()).trim();

            InetAddress IPOrigen = paqRecibido.getAddress();
            int puerto = paqRecibido.getPort();

            GuardarCliente clienteExistente = getClientePorIP(IPOrigen, puerto);
            if (clienteExistente == null) {
                if (nombreUsuarioExiste(cadena)) {
                    String mensajeError = "El nombre de usuario '" + cadena + "' ya está en uso. Elija otro nombre.";
                    byte[] mensajeErrorBytes = mensajeError.getBytes();
                    DatagramPacket paqError = new DatagramPacket(mensajeErrorBytes, mensajeErrorBytes.length, IPOrigen, puerto);
                    serverSocket.send(paqError);
                    continue;
                }

                GuardarCliente nuevoCliente = new GuardarCliente(cadena, IPOrigen, puerto);
                GuardarCliente.setClientes(nuevoCliente);
                System.out.println("Usuario '" + cadena + "' conectado desde " + IPOrigen + ":" + puerto);
                notificarUsuarios(serverSocket, "Usuario '" + cadena + "' conectado.", IPOrigen, puerto);
                enviarHistorial(serverSocket, IPOrigen, puerto);
                continue;
            }

            if (cadena.startsWith("/privado ")) {
                procesarComandoPrivado(serverSocket, clienteExistente, cadena);
            } else if (cadena.equals("/usuarios")) {
                enviarListaUsuarios(serverSocket, clienteExistente);
            } else if (cadena.equals("/salir")) {
                desconectarCliente(serverSocket, clienteExistente);
                continue;
            } else {
                System.out.println("[" + clienteExistente.getNombre() + "]: " + cadena);
                notificarUsuarios(serverSocket, "[" + clienteExistente.getNombre() + "]: " + cadena, IPOrigen, puerto);
                guardarEnHistorial("[" + clienteExistente.getNombre() + "]: " + cadena);
            }

            if (cadena.equals("*")) {
                break;
            }
        }

        serverSocket.close();
        System.out.println("Socket cerrado...");
    }

    private static void notificarUsuarios(DatagramSocket socket, String mensaje, InetAddress ipRemitente, int puertoRemitente) throws Exception {
        byte[] enviados = mensaje.getBytes();
        for (GuardarCliente g : GuardarCliente.getClientes()) {
            if (!(g.getIp().equals(ipRemitente) && g.getPuerto() == puertoRemitente)) {
                DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, g.getIp(), g.getPuerto());
                socket.send(paqEnviado);
            }
        }
    }

    private static void procesarComandoPrivado(DatagramSocket socket, GuardarCliente remitente, String cadena) throws Exception {
        String[] partes = cadena.split(" ", 3);
        if (partes.length < 3) {
            enviarMensaje(socket, "Comando privado incorrecto. Uso: /privado <usuario> <mensaje>", remitente);
            return;
        }

        String nombreDestino = partes[1];
        String mensajePrivado = partes[2];

        GuardarCliente destino = getClientePorNombre(nombreDestino);
        if (destino != null) {
            String mensaje = "[Privado de " + remitente.getNombre() + "]: " + mensajePrivado;
            enviarMensaje(socket, mensaje, destino);
        } else {
            enviarMensaje(socket, "El usuario '" + nombreDestino + "' no está conectado.", remitente);
        }
    }

    private static void enviarListaUsuarios(DatagramSocket socket, GuardarCliente cliente) throws Exception {
        StringBuilder listaUsuarios = new StringBuilder("Usuarios conectados: ");
        for (GuardarCliente g : GuardarCliente.getClientes()) {
            listaUsuarios.append(g.getNombre()).append(", ");
        }
        enviarMensaje(socket, listaUsuarios.toString(), cliente);
    }

    private static void desconectarCliente(DatagramSocket socket, GuardarCliente cliente) throws Exception {
        System.out.println("Usuario '" + cliente.getNombre() + "' desconectado.");
        GuardarCliente.getClientes().remove(cliente);
        notificarUsuarios(socket, "Usuario '" + cliente.getNombre() + "' se ha desconectado.", cliente.getIp(), cliente.getPuerto());
    }

    private static void guardarEnHistorial(String mensaje) {
        if (historial.size() >= 10) {
            historial.remove(0);
        }
        historial.add(mensaje);
    }

    private static void enviarHistorial(DatagramSocket socket, InetAddress ip, int puerto) throws Exception {
        for (String mensaje : historial) {
            byte[] enviados = mensaje.getBytes();
            DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, ip, puerto);
            socket.send(paqEnviado);
        }
    }

    private static void enviarMensaje(DatagramSocket socket, String mensaje, GuardarCliente cliente) throws Exception {
        byte[] enviados = mensaje.getBytes();
        DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, cliente.getIp(), cliente.getPuerto());
        socket.send(paqEnviado);
    }

    private static boolean nombreUsuarioExiste(String nombre) {
        for (GuardarCliente g : GuardarCliente.getClientes()) {
            if (g.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    private static GuardarCliente getClientePorIP(InetAddress ip, int puerto) {
        for (GuardarCliente g : GuardarCliente.getClientes()) {
            if (g.getIp().equals(ip) && g.getPuerto() == puerto) {
                return g;
            }
        }
        return null;
    }

    private static GuardarCliente getClientePorNombre(String nombre) {
        for (GuardarCliente g : GuardarCliente.getClientes()) {
            if (g.getNombre().equalsIgnoreCase(nombre)) {
                return g;
            }
        }
        return null;
    }
}
