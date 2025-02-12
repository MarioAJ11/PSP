package AlguacilJuarez_Mario_PSP_USD2_P4;

public class Coches extends Thread {
    private final Puente puente;
    private final int posicion;
    public Coches(int pos, Puente puente) {
        this.posicion = pos;
        this.puente = puente;
    }

    @Override
    public void run() {
        while (true) {
            if(posicion == 0) {
                puente.sur();
            } else if (posicion == 1) {
                puente.norte();
            }
        }
    }
}
