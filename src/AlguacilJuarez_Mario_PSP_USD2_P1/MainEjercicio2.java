package AlguacilJuarez_Mario_PSP_USD2_P1;

public class MainEjercicio2 {
    public static void main(String[] args) {
        for(int i = 1; i < 6; i++){
            Ejercicio2 hilos = new Ejercicio2(i, "mensaje número "+i);
            Thread thread = new Thread(hilos);
            thread.start();
        }
    }
}
//¿Qué diferencias observas al ejecutar el programa usando o no el método sleep()?
/*
Para empezar que los muestra al instante y no tiene un tiempo de espera, pero bueno eso varia según lo elija yo.
Y el más importante es que al esperar no se desordenan los hilos y espera uno a uno, es decir, tiene tiempo de espera y además prioriza mostrarlos en orden
*/
