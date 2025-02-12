package AlguacilJuarez_Mario_PSP_USD2_P1;

public class MainEjercicio1 {
    public static void main(String[] args) {
        for(int i = 1; i <= 5; i++){
            //llamamos a la clase y la ejecutamos 5 veces
            Ejercicio1 ejercicio1 = new Ejercicio1(i);
            ejercicio1.start();
        }
    }
}
//con este ejercicio vemos q los procesos de los hilos entran de distinta forma
//pudiendo ser aleatorios
