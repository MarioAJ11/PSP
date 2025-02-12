package AlguacilJuarez_Mario_PSP_USD2_P4;

public class Puente extends Thread{
    private static final int n = 10;
    private static final int espera = 3000;
    private static final int tiempoCoches = 11900;
    private static int nNorte = 5;
    private static int nSur = 5;

    @Override
    public void run(){
        try {
            while (true) {
                Thread.sleep(tiempoCoches);
                nuevosCoches();
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static synchronized void sur(){
        try {
            System.out.println("Coche cruzando de sur a norte");
            System.out.println("---------------------------------");
            while(cruce(0)) {
                if(nSur != 0) {
                    System.out.println("Coche cruzado satisfactoriamente");
                    nSur--;
                    System.out.println("Cola Sur: " + nSur + "\n");
                    Thread.sleep(espera);
                } else {
                    System.out.println("\tCambio de sentido\n");
                    norte();
                }
            }
            System.out.println("\tCambio de sentido\n");
            norte();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static synchronized void norte() {
        try {
            System.out.println("Coche cruzando de norte a sur");
            System.out.println("---------------------------------");
            while(cruce(1)) {
                if(nNorte != 0) {
                    System.out.println("Coche cruzado satisfactoriamente");
                    nNorte--;
                    System.out.println("Cola Norte: " + nNorte + "\n");
                    Thread.sleep(espera);
                } else {
                    System.out.println("\tCambio de sentido\n");
                    sur();
                }
            }
            System.out.println("\tCambio de sentido\n");
            sur();
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public synchronized void nuevosCoches() {
        for(int i = 0; i < 2; i++) {
            double random = Math.random() * 2.1;
            int numCoches;
            numCoches = (int) (Math.random() * 4 + 1);
            if (random <= 1) {
                nNorte += numCoches;
                System.out.println("  Coches añadidos a la cola del norte: " + numCoches + ", cola actual del norte: " + nNorte);
            } else {
                nSur += numCoches;
                System.out.println("  Coches añadidos a la cola del sur: " + numCoches + ", cola actual del sur: " + nSur);
            }
        }
        System.out.println();
    }
    public static boolean cruce(int posicion){
        if(posicion == 0){
            if(nSur >= n || nNorte < n){
                return true;
            } else {
                return false;
            }
        } else if (posicion == 1){
            if(nSur >= n){
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
