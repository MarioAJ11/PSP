package AlguacilJuarez_Mario_PSP_UD1_P2;

import java.io.*;
import java.util.Arrays;

public class Ejecuta_Ejercicio1 {
    public static void main(String[] args) {
        String classpath = System.getProperty("java.class.path");
        int num = 0;
        int []resultado = new int [10];
        String []numeros = new String[20];
        for(int i = 0; i < numeros.length; i++){
            num = (int)(Math.random()*30);
            numeros[i] = String.valueOf(num);
        }

        try{
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath,"Apellido1Apellido2_Nombre_PSP_UD1_P2.Ejercicio1");
            pb.command().addAll(Arrays.asList(numeros));
            Process proceso1 = pb.start();
            Process proceso2 = pb.start();

            BufferedReader lectura1 = new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
            BufferedReader lectura2 = new BufferedReader(new InputStreamReader(proceso2.getInputStream()));

            for(int i = 0; i < resultado.length; i++){
                if (i<5) {
                    lectura2.readLine();
                    resultado[i] = Integer.parseInt(lectura1.readLine());
                } else {
                    lectura1.readLine();
                    resultado[i] = Integer.parseInt(lectura2.readLine());
                }
            }
            System.out.print("El resultado es: ");
            for(int i = 0; i < resultado.length; i++){
                System.out.print(resultado[i] + " ");
            }

            proceso1.waitFor();
            proceso2.waitFor();
            lectura1.close();
            lectura2.close();
        } catch (Exception e) {
            System.out.println("Error al ejecutar el programa: " + e.getMessage());
        }
    }
}
