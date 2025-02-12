package AlguacilJuarez_Mario_PSP_USD2_P4;

public class Main {
    public static void main(String[] args) {
        Puente puente = new Puente();
        Coches sur = new Coches(0,puente);
        Coches norte = new Coches(1,puente);
        Thread t1 = new Thread(sur);
        Thread t2 = new Thread(norte);
        Thread t3 = new Thread(puente);
        t3.start();
        t1.start();
        t2.start();
    }
}
