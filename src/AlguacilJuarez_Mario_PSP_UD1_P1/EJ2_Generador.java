package AlguacilJuarez_Mario_PSP_UD1_P1;

import java.io.*;
import java.util.Scanner;

public class EJ2_Generador {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int instancias = 0;
        int cadenas = 0;
        //solicitamos q ingrese un numero dde instancias deseadas y los limitamos
        try {
            System.out.print("Ingrese el número de instancias: ");
            instancias = s.nextInt();
            while (instancias < 10) {
                System.out.print("Por favor, ingrese un número válido de instancias (mayor a 10): ");
                instancias = s.nextInt();
            }
        } catch (Exception e) {//si hay un error se finalizara el programa
            System.out.println("Error en la entrada: " + e.getMessage());
            return;
        }
        //la cantidad de cadena q quiere poc instancia
        try {
            System.out.print("Ingrese el número de cadenas por instancia: ");
            cadenas = s.nextInt();
            while (cadenas < 10) {
                System.out.print("Por favor, ingrese un número válido de cadenas (mayor a 10): ");
                cadenas = s.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Error en la entrada: " + e.getMessage());
            return;
        }
        //creamos un for para mostrar por consola las instancias con sus cadenas
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int inputInt = Integer.parseInt(reader.readLine());

            String classpath = System.getProperty("java.class.path");

            ProcessBuilder pb = new ProcessBuilder ("java", "-cp", classpath, "EJ1_Cadena.java");
            Process process = pb.start();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

            BufferedReader processOutputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            for (int i = 1; i <= instancias; i++) {
                for (int j = 1; j <= cadenas; j++) {
                    writer.write(inputInt);
                    line = processOutputReader.readLine();
                    System.out.println("Instancia " + i + ", Cadena " + j + " es igual a: "+ line);
                }
            }
            writer.newLine();
            writer.flush();
            writer.close();
            process.waitFor();
            processOutputReader.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        s.close();
    }
}

