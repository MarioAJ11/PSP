package AlguacilJuarez_Mario_PSP_USD2_P3.EJ2;

public class Productor extends Thread {
    private final Almacen almacen;
    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        boolean seguir = true;
        while (seguir) {
            try {
                int piezas = (int)(Math.random() * 600 + 400);
                almacen.agregarPiezas(piezas);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

