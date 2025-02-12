package AlguacilJuarez_Mario_PSP_USD2_P3;

import java.text.DecimalFormat;

public class Cuenta {
    private static final int maxCuenta = 10000;
    private static double saldo;
    private final DecimalFormat formato = new DecimalFormat("#####.00");

    public Cuenta() {
        saldo = Math.random() * maxCuenta;
    }

    public static synchronized double getSaldo() {
        return saldo;
    }

    public static synchronized void setSaldo(double saldo) {
        Cuenta.saldo = saldo;
    }

    public synchronized void ingreso(double valor, String nombre) {
        String form = formato.format(valor);
        String form2;
        double sald = getSaldo();
        sald += valor;
        System.out.println("Intento de ingreso por parte de " + nombre);
        if (!(sald > maxCuenta)) {
            setSaldo(sald);
            form2 = formato.format(getSaldo());
            System.out.println("Ingreso de " + form + " efectuado satisfactoriamente, saldo de la cuenta es: " + form2);
            System.out.println();
        } else {
            setSaldo(maxCuenta);
            System.out.println("Máximo de cuenta alcanzado , el saldo es de 10000.00. El intento de ingreso fue de " + form);
            System.out.println();
        }
        Main.cont();
    }

    public synchronized void reintegro(double valor, String nombre) {
        String form = formato.format(valor);
        String form2;
        double sald = getSaldo();
        sald -= valor;
        System.out.println("Intento de reintegro por parte de " + nombre);

        if (!(sald <= 0)) {
            setSaldo(sald);
            form2 = formato.format(getSaldo());
            System.out.println("Reintegro de " + form + " efectuado satisfactoriamente, saldo de la cuenta es: " + form2);
            System.out.println();
        } else {
            setSaldo(0);
            System.out.println("Reintegro negativo, se ha quedado a 0.00, el intento fue de " + form);
            System.out.println();
        }
        Main.cont();
    }
}
