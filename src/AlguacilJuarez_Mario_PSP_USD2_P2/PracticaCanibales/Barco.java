package AlguacilJuarez_Mario_PSP_USD2_P2.PracticaCanibales;

public class Barco {
    private static int misioneros = 0;
    private static int canibales = 0;
    private final int tamBote = 3;
    private boolean puedeCruzar = false;
    private static int cont = 1;

    public synchronized void llegaM(){
        try {
            if (!esSegura(0)) {
                wait();
            } else {
                misioneros++;
                System.out.println("Misionero sube al bote. Misioneros en bote: " + misioneros + ", Canibales en bote: " + canibales);
                if (misioneros + canibales == tamBote) {
                    puedeCruzar = true;
                    try {
                        cruzar();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    notifyAll();
                }
            }
        }catch (InterruptedException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public synchronized void llegaC(){
        try {
            if (!esSegura(1)) {
                wait();
            } else {
                canibales++;
                System.out.println("Caníbal sube al bote. Misioneros en bote: " + misioneros + ", Caníbales en bote: " + canibales);
                if (misioneros + canibales == tamBote) {
                    puedeCruzar = true;
                    try {
                        cruzar();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    notifyAll();
                }
            }
        }catch (InterruptedException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private boolean esSegura(int c) {
        if (misioneros + canibales >= tamBote || puedeCruzar) {
            return false;
        }
        if (canibales == 1 && misioneros == 1 && c == 1) {
            return false;
        }
        if (canibales == 2 && misioneros == 0 && c == 0) {
            return false;
        }
        return true;
    }

    public synchronized void cruzar(){
        try {
            System.out.println("Bote cruzando con " + misioneros + " misioneros y " + canibales + " caníbales.");
            Thread.sleep(1000);
            misioneros = 0;
            canibales = 0;
            puedeCruzar = false;
            System.out.println("VIAJE " + cont + "\n\n");
            cont++;
            notifyAll();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static boolean finalizado = false;
    public synchronized static boolean fin(){
        if(cont == 9){
            finalizado = true;
            if(misioneros + canibales < 3 ) {
                System.out.println("Se ha finalizado los trayectos de viajes con individuos dentro, lo mismo se matan.");
            }else{
                System.out.println("Se ha finalizado los trayectos de viajes con la barca vacía, que se maten por ahí.");
            }
            System.exit(0);
        }
        return finalizado;
    }
}
