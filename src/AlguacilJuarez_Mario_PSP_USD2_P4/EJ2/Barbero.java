package AlguacilJuarez_Mario_PSP_USD2_P4.EJ2;

public class Barbero extends Thread{
    private final Barberia barberia;
    public Barbero(Barberia barberia){
        this.barberia = barberia;
    }
    public void run(){
        barberia.trabajarBarbero();
    }
}
