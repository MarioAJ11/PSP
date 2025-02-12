package AlguacilJuarez_Mario_PSP_USD2_P2;

public class HiloAdivina extends Thread {
    private final int hilo;
    private static int numHilo;
    private boolean acertado = false;
    private int respuesta;
    public HiloAdivina(int hilo) {
        this.hilo = hilo;
    }

    @Override
    public void run() {
        while(!NumeroOculto.getAdivinado() || !acertado) {
            numHilo = (int)(Math.random()*100);
            try {
                respuesta = NumeroOculto.isAdivinado(numHilo);
                if (respuesta == 1) {
                    System.out.println("Hilo: " + hilo + ", Número: " + numHilo);
                    System.out.println("Adivinado \nEl número adivinado es: " + NumeroOculto.getNumero() + ", adivinado por el hilo: " + hilo);
                    acertado = true;
                } else if (respuesta == -1) {
                    System.out.println("Se ha terminado ya que ha sido acertado el número, el hilo intento de entrada es: " + hilo + " con el numero: " + numHilo);
                    acertado = true;
                } else if (respuesta == 0) {
                    System.out.println("Hilo: " + hilo + ", Número: " + numHilo);
                }
                Thread.sleep(300);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
