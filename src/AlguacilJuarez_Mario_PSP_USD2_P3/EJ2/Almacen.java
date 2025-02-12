package AlguacilJuarez_Mario_PSP_USD2_P3.EJ2;

public class Almacen {
    private int stock = 8000;
    private final int capacidadMaxima = 20000;
    public synchronized void agregarPiezas(int cantidad) {
        try {
            if (stock + cantidad > capacidadMaxima) {
                System.out.println("Almacén lleno");
                wait();
            } else {
                stock += cantidad;
                System.out.println("El productor añade: " + cantidad + " piezas. Stock: " + stock);
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public synchronized void quitarPiezas(int cantidad) {
        try {
            if (stock < cantidad) {
                System.out.println("Almacén con stock insuficiente");
                wait();
            } else {
                stock -= cantidad;
                System.out.println("Consumidor quita: " + cantidad + " piezas. Stock actual: " + stock + "\n");
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

