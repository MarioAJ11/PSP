package AlguacilJuarez_Mario_PSP_USD2_P2;

public class NumeroOculto{
    private static final int numero = (int)(Math.random()*100);
    private static boolean adivinado = false;
    public static int getNumero() {
        return numero;
    }
    public static boolean getAdivinado() {
        return adivinado;
    }
    public static synchronized int isAdivinado(int numHilo) {
        if(numHilo == NumeroOculto.getNumero()){
            adivinado = true;
            return 1;
        } else if (adivinado) {
            return -1;
        }
        return 0;
    }
}
