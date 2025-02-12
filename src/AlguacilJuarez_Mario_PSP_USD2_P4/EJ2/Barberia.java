package AlguacilJuarez_Mario_PSP_USD2_P4.EJ2;
import java.util.concurrent.Semaphore;

public class Barberia{
    private final Semaphore sillon;
    private final Semaphore silla;
    private final Semaphore barberoListo;
    private final int totalEspera;
    private static int cont;

    public Barberia(int numSillas, int espera) {
        this.totalEspera = numSillas;
        sillon = new Semaphore(1);
        silla = new Semaphore(numSillas);
        barberoListo = new Semaphore(0);
        cont = espera;
    }
    
    public void entrarBarberia(int num) {
        System.out.println("·Cliente " + num + " entra a la barbería");
        if (silla.tryAcquire()) {
            System.out.println("\tCliente " + num + " se sienta en una silla de espera");
            try {
                sillon.acquire();
                System.out.println("\t\tCliente " + num + " despierta al barbero");
                barberoListo.release();
                System.out.println("\t\tEl barbero se despierta y pela al cliente " + num);
                silla.release();
                cont--;
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("\t\t\t\tCliente " + num + " encuentra la sala de espera llena y se va");
            cont--;
        }
    }

    public void trabajarBarbero() {
        System.out.println("########################################## BARBERÍA ABIERTA ##########################################");
        while (cont != 0) {
            try {
                System.out.println("·El barbero está dormido·");
                barberoListo.acquire();
                Thread.sleep(2000);
                System.out.println("\t\tEl barbero ha terminado de pelar al cliente");
                sillon.release();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("\n########################################## BARBERÍA CERRADA ##########################################");
    }
}