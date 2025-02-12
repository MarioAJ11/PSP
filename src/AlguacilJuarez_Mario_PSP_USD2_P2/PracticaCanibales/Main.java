package AlguacilJuarez_Mario_PSP_USD2_P2.PracticaCanibales;

public class Main {
    public static void main(String[] args){
        Barco b = new Barco();
        for (int i = 1; i <= 12; i++) {
            Thread t1 = new Thread(new Misionero(b));
            t1.start();
            Thread t2 = new Thread(new Canibal(b));
            t2.start();
        }
    }
}
