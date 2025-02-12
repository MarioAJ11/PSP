package AlguacilJuarez_Mario_PSP_UD3_P2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloAhorcado extends Thread {

    private final Socket socketCliente;
    private boolean conectado = true;
    private static String user = null;
    private static String pass = null;
    private static int partidas = 0;
    private static int victorias = 0;
    private static int derrotas = 0;
    private static int puntos = 0;
    private static boolean sesion = false;
    private static int modo = 0;
    private static int acertado = 0;
    private static int[] info = new int[4];

    HiloAhorcado(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    public static void setPuntos(int puntos) {
        HiloAhorcado.puntos = puntos;
    }

    public static int getPuntos() {
        return puntos;
    }

    public static void setAcertado(int acertado) {
        HiloAhorcado.acertado = acertado;
    }

    @Override
    public void run() {
        try {
            // Establece canal de entrada
            BufferedReader entrada = new BufferedReader(new InputStreamReader(
                    socketCliente.getInputStream()));
            // Establece canal de salida
            PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socketCliente.getOutputStream())), true);

            salida.println("Si es su primera vez use REGISTER -u usuario -p contraseña.");
            salida.println("Si no use primero USUARIO usuario.");
            salida.println("Recuerde que para finalizar la sesión a de usar 'ADIOS'");

            while(conectado) {
                // Realizamos la comunicación entre servidor y cliente
                // Hacemos una recepción de información desde el cliente
                String mensajeRecibido = entrada.readLine();
                if(mensajeRecibido.equalsIgnoreCase("ADIOS")) {
                    System.out.println("<-/-> Cliente desconectado, un saludo.");
                    conectado = false;
                } else {
                    if(!sesion) {
                        System.out.println("<-/-> Cliente se encuentra iniciando sesión.");
                        salida.println(comandosInicio(mensajeRecibido));
                        if(sesion) {
                            salida.println(user + " seleccione un modo de juego, INDIVIDUAL o MULTIJUGADOR.");
                        }
                    } else {
                        if(modo == 0) {
                            System.out.println("<-- Cliente " + user + " se encuentra seleccionado un modo de juego.");
                            salida.println(comandoSesion(mensajeRecibido));
                            if(modo == 1) {
                                salida.println("Partida individual iniciada correctamente.");
                                salida.println("Comandos: VOCAL vocal , CONSONANTE consonante , RESOLVER resolver refrán.");
                                Juego.elegirRefran();
                                System.out.println(user + " ha iniciado una partida individual.");
                                acertado = -1;
                                while(acertado == -1) {
                                    String msg = entrada.readLine();
                                    salida.println(Juego.comprobarRefran(msg, true, user));
                                }
                                salida.println("Ahorcado finalizado.");
                                partidas++;
                                if(acertado == 0) {
                                    victorias++;
                                    salida.println("FELICIDADES!!!!!");
                                }else if (acertado == 1){
                                    derrotas++;
                                    salida.println("Vaya pringao -_-");
                                }
                                salida.println("\tVictorias: " + victorias + "\tDerrotas: " + derrotas + "\tPartidas: " + partidas + "\tPuntos: " + puntos);
                                info[0] = partidas;
                                info[1] = victorias;
                                info[2] = derrotas;
                                info[3] = puntos;
                                Usuarios.guardadoAutomatico(user, info);
                                modo = 0;
                            } else if (modo == 2) {
                                salida.println("Espere a que se inicie la sesión.");
                                if(!Servidor.colaMultijugador(user, socketCliente)){
                                    salida.println("Has sido añadido a la cola");
                                    new Multijugador(user, socketCliente).start();
                                    while(Multijugador.fin(user) != -1){
                                        if(Multijugador.fin(user) == 1){
                                            System.out.println("<-/-> Cliente desconectado, un saludo.");
                                            conectado = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Cliente " + user + " desconectado.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String comandosInicio(String msg) throws IOException {
        String[] mensajes = msg.split("\\s+");
        String texto, nombre, password;
        texto = "-Err. Comando desconocido.";
        switch(mensajes[0]) {
            case "REGISTER":
                nombre = mensajes[2];
                password = mensajes[4];
                if(!Usuarios.usuarioEncontrar(nombre)){
                    Usuarios.usuarioNuevo(nombre,password);
                    user = nombre;
                    pass = password;
                    texto = "Registro satisfactorio, Buenas " + user + ".";
                    sesion = true;
                } else {
                    texto = "-Err. Usuario ya existente, pruebe otro usuario";
                }
                break;
            case "USUARIO":
                nombre = mensajes[1];
                if (Usuarios.usuarioEncontrar(nombre)) {
                    texto = "Usuario encontrado, introduzca la contraseña con PASSWORD contraseña";
                    user = nombre;
                } else {
                    texto = "-Err. Usuario incorrecto.";
                }
                break;
            case "PASSWORD":
                password = mensajes[1];
                if (Usuarios.usuarioPassword(user, password)) {
                    pass = password;
                    texto = "Sesión iniciada correctamente, Buenas " + user + ".";
                    System.out.println("Usuario " + user + " iniciado correctamente con la contraseña " + password);
                    sesion = true;
                } else {
                    texto = "-Err. Contraseña incorrecta.";
                }
                break;
        }
        return texto;
    }

    public static String comandoSesion(String msg) {
        String texto;
        texto = "-Err. Comando desconocido.";
        switch(msg) {
            case "INDIVIDUAL":
                texto = "Va a comenzar el juego individual.";
                modo = 1;
                break;
            case "MULTIJUGADOR":
                texto = "Va a comenzar el juego multijugador, esta en cola para jugar.";
                modo = 2;
                break;
        }
        return texto;
    }
}