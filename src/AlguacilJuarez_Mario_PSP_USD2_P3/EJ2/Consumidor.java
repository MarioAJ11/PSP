package AlguacilJuarez_Mario_PSP_USD2_P3.EJ2;

public class Consumidor extends Thread {
    private final Almacen almacen;
    private static

    int cont = 0;
    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        boolean seguir = true;
        while (seguir) {
            try {
                if(cont == 1) {
                    int piezasAQuitar = (int) (Math.random() * 500 + 2000);
                    almacen.quitarPiezas(piezasAQuitar);
                    Thread.sleep(6000);
                } else {
                    cont = 1;
                    Thread.sleep(6000);
                }
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

