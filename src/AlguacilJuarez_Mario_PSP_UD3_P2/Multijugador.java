package AlguacilJuarez_Mario_PSP_UD3_P2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Multijugador extends Thread {
    private static int cont = 0;
    private static int partida = 0;
    private final Socket socketCliente;
    private String nombre;
    private int pts;
    private static Multijugador jugador;
    public Multijugador(String nombre, Socket socketCliente) {
        this.nombre = nombre;
        this.socketCliente = socketCliente;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPuntos() {
        return pts;
    }
    public void setPuntos(int pts) {
        this.pts = pts;
    }
    public static void setPts(String nombre, int puntos) {
        for(Multijugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                jugador.setPuntos(puntos);
            }
        }
    }
    public static int getPts(String nombre) {
        for(Multijugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return jugador.getPuntos();
            }
        }
        return 0;
    }
    private final static Multijugador[] jugadores = new Multijugador[3];

    public static void setJugadores(Multijugador[] j) {
        for(int i = 0; i < 3; i++){
            jugadores[i] = j[i];
        }
    }


    @Override
    public void run() {
        try {
            // Establece canal de entrada
            BufferedReader entrada = new BufferedReader(new InputStreamReader(
                    socketCliente.getInputStream()));
            // Establece canal de salida
            PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socketCliente.getOutputStream())));
            while (true) {
                if (jugadores[2] != null) {
                    salida.println("Multijugador inciado con los jugadores: " + jugadores[0].getNombre() + ", " + jugadores[1].getNombre() + ", " + jugadores[2].getNombre());
                    jugador = jugadores[cont];
                    salida.println((comprobarTurno(entrada.readLine())));
                    if(partida == -1){
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static String comprobarTurno(String msg) {
        String texto = "-Err. Comando incorrecto";
        String[] mensajes = msg.split(" ");
        if(mensajes[0].equalsIgnoreCase("ADIOS")){
            partida = -1;
        } else {
            if(jugadores[cont] == jugador){
                texto = Juego.comprobarRefran(msg, false, jugadores[cont].getNombre());
                if(cont == 2){
                    cont = 0;
                } else {
                    cont++;
                }
            } else {
                texto = "-Err. Espere su turno. Turno de: " + jugador.getNombre();
            }
        }
        return texto;
    }
    public static int fin(String nombre) {
        for(Multijugador jugador : jugadores){
            if(jugador.getNombre().equals(nombre)){
                return partida;
            }
        }
        return 0;
    }
}
