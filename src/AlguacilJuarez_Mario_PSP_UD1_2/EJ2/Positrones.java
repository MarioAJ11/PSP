package AlguacilJuarez_Mario_PSP_UD1_2.EJ2;

public class Positrones extends Thread {
    private final Caja caja;
    public Positrones(Caja caja) {
        this.caja = caja;
    }

    @Override
    public void run() {
        while (true) {
            caja.pasanPositrones();
        }
    }
}
