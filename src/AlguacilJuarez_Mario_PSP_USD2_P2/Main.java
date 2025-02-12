package AlguacilJuarez_Mario_PSP_USD2_P2;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            HiloAdivina hilo = new HiloAdivina(i);
            hilo.start();
        }
    }
}
