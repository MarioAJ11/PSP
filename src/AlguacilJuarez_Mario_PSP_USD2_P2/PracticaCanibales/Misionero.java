package AlguacilJuarez_Mario_PSP_USD2_P2.PracticaCanibales;

public class Misionero extends Thread {
    private final Barco b;

    public Misionero(Barco b) {
        this.b = b;
    }

    @Override
    public void run() {
        while (!Barco.fin()) {
            b.llegaM();
        }
    }
}
