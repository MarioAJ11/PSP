package AlguacilJuarez_Mario_PSP_UD1_2.EJ2;

public class Electrones extends Thread {
    private final Caja caja;
    public Electrones(Caja caja) {
        this.caja = caja;
    }

    @Override
    public void run() {
        while (true) {
            caja.pasanElectrones();
        }
    }
}
