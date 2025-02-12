package AlguacilJuarez_Mario_PSP_USD2_P1;


public class Ejercicio2 implements Runnable {
    private final int hilo;
    private final String cadena;

    public Ejercicio2(int hilo, String cadena) {
        this.hilo = hilo;
        this.cadena = cadena;
    }

    public void run() {
        try{
            Thread thread = new Thread(this);
            Thread.sleep(hilo * 1000);
        }catch(InterruptedException e){
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Hola mundo, " + cadena + ". Número de hilo: " + hilo);
    }
}
