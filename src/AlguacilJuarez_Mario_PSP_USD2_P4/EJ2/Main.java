package AlguacilJuarez_Mario_PSP_USD2_P4.EJ2;

public class Main {
    public static void main(String[] args) {
        int numeroSillasEspera = 5;
        int personas = 50;
        Barberia barberia = new Barberia(numeroSillasEspera, personas);
        Barbero barbero = new Barbero(barberia);
        Thread thread = new Thread(barbero);
        thread.start();
        int num;
        try {
            for (int i = 1; i <= personas; i++) {
                num = i;
                Thread.sleep(500 + (int) (Math.random() * 1000));
                Cliente cliente = new Cliente(num, barberia);
                Thread t = new Thread(cliente);
                t.start();
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}
