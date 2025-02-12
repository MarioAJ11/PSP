package AlguacilJuarez_Mario_PSP_USD2_P4.EJ2;

public class Cliente extends Thread {
    private final int num;
    private final Barberia barberia;
    public Cliente(int num, Barberia barberia) {
        this.num = num;
        this.barberia = barberia;
    }
    public void run() {
        try {
            barberia.entrarBarberia(num);
            Thread.sleep(1000 + (int)(Math.random() * 2000));
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
