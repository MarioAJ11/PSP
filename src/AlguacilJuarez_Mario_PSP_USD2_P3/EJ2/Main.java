package AlguacilJuarez_Mario_PSP_USD2_P3.EJ2;

public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();

        Productor productor = new Productor(almacen);
        Consumidor consumidor = new Consumidor(almacen);

        Thread t1 = new Thread(productor);

        Thread t2 = new Thread(consumidor);

        t1.start();
        t2.start();
    }
}

