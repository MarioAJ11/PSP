package AlguacilJuarez_Mario_PSP_UD1_P2;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Ejecuta_Ejercicio2 {
    public static void main(String[] args) {
        String classpath = System.getProperty("java.class.path");
        String fNombre = "src/AlguacilJuarez_Mario_PSP_UD1_P2/Ejercicio28";
        File file = new File(fNombre);

        String dir = file.getParent();
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath, fNombre);
            pb.redirectOutput(new File(dir, "S"+file.getName()));
            pb.redirectError(new File(dir, "E"+file.getName()));
            Process proceso = pb.start();

            BufferedReader lectura = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            while(lectura.ready()) {
                System.out.println(lectura.readLine());
            }

            proceso.waitFor();
            lectura.close();
        } catch (Exception e) {
            System.out.println("Error al ejecutar el programa: " + e.getMessage());
        }
    }
}


