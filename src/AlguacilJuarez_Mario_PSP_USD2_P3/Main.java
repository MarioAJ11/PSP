package AlguacilJuarez_Mario_PSP_USD2_P3;

public class Main {
    private static int cont = 0;
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta();
        Persona p1 = new Persona("Mario", cuenta);
        Persona p2 = new Persona("Sergio", cuenta);
        Persona p3 = new Persona("Adri", cuenta);
        Persona p4 = new Persona("Andrea", cuenta);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
    public static synchronized void cont() {
        cont++;
        if(cont == 16) {
            System.out.println("Movimientos finalizados");
            System.exit(0);
        }
    }
}
