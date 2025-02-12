package AlguacilJuarez_Mario_PSP_USD2_P1;

public class Ejercicio1 extends Thread {
    //creamos una contaste q nos marquen en q hilo estamos
    private final int hilo;
    public Ejercicio1(int hilo) {
        this.hilo = hilo;
    }
    //creamos un run donde se mostrara el hola mundo desde q hilo
    public void run () {
        System.out.println("Hola mundo, hilo: " + hilo);
    }
}
