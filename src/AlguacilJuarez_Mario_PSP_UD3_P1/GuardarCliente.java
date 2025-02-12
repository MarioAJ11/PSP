package AlguacilJuarez_Mario_PSP_UD3_P1;

import java.net.InetAddress;
import java.util.ArrayList;

public class GuardarCliente {
    private final String nombre;
    private final InetAddress ip;
    private final int puerto;
    private final static ArrayList<GuardarCliente> clientes = new ArrayList<>();

    public GuardarCliente(String nombre, InetAddress ip, int puerto) {
        this.nombre = nombre;
        this.ip = ip;
        this.puerto = puerto;
    }

    public String getNombre() {
        return nombre;
    }

    public InetAddress getIp() {
        return ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public static ArrayList<GuardarCliente> getClientes() {
        return clientes;
    }

    public static void setClientes(GuardarCliente cliente) {
        clientes.add(cliente);
    }
}