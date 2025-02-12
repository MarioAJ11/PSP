package AlguacilJuarez_Mario_PSP_USD2_P3;

public class Persona extends Thread {
    public final Cuenta cuenta;
    private final String nombre;

    public Persona(String nombre, Cuenta cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        try{
            synchronized (this) {
                cuenta.ingreso(Math.random() * 500 + 1, nombre);
                Thread.sleep(1000);
            }

            synchronized (this) {
                cuenta.reintegro(Math.random() * 500 + 1, nombre);
                Thread.sleep(1000);
            }

            synchronized (this) {
                cuenta.reintegro(Math.random() * 500 + 1, nombre);
                Thread.sleep(1000);
            }

            synchronized (this) {
                cuenta.ingreso(Math.random() * 500 + 1, nombre);
            }

            synchronized (this) {
                this.join();
            }
        }catch(
        InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
}
}
