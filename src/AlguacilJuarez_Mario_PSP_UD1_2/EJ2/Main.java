package AlguacilJuarez_Mario_PSP_UD1_2.EJ2;

public class Main {
    public static void main(String[] args) {
        Caja caja = new Caja();
        Positrones positrone = new Positrones(caja);
        Electrones electron = new Electrones(caja);
        Thread t1 = new Thread(positrone);
        Thread t2 = new Thread(electron);
        t1.start();
        t2.start();
    }
}
