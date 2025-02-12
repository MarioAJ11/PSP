package AlguacilJuarez_Mario_PSP_UD1_2.EJ1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lanzador {
    public static void main(String[] args) {
        try {
            String classpath = System.getProperty("java.class.path");
            //creo un proceso
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath, "Numero.java");
            Process p = pb.start();
            //de ese proceso le hago un reader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            int input, suma = 0, t;
            //hago ese reader 10 veces
            for(int i = 1; i <= 10; i++){
                input = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Nª: " + i + " es el numero: " + input);
                //creo un if donde alterno entre la entrada del primer y segundo numero, sumandolos entresi y resetando suma.
                if(suma == 0){
                    suma += input;
                } else {
                    t = suma;
                    suma += input;
                    System.out.println("La suma de " + t + " + " + input + " es: " + suma);
                    suma = 0;
                }
            }

            p.waitFor();
            bufferedReader.close();

        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
