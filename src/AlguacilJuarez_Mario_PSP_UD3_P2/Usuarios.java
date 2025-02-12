package AlguacilJuarez_Mario_PSP_UD3_P2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Usuarios {
    private static final File file = new File("src/AlguacilJuarez_Mario_PSP_UD3_P2/Usuarios.txt");

    public synchronized static int[] infoUsuario(String nom) throws IOException {
        int[] info = new int[4];
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        try {
            while (br.ready()) {
                linea = br.readLine();
                String[] l = linea.split("\\s+");
                if (l[0].equals(nom)) {
                    for (int i = 0; i < 4; i++) {
                        info[i] = Integer.parseInt(l[i+2]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return info;
    }

    public synchronized static void usuarioNuevo(String nom, String con) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        try {
            bw.newLine();
            bw.write( nom + " " + con + " " + 0 + " " + 0 + " " + 0 + " " + 0);//nombre, contraseña, partidas, victorias, derrotas, puntos
            bw.flush();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public synchronized static boolean usuarioEncontrar(String nom) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        try {
            while (br.ready()) {
                linea = br.readLine();
                String[] l = linea.split("\\s+");
                if (l[0].equals(nom)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
    }

    public synchronized static boolean usuarioPassword(String nom, String con) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        try {
            while (br.ready()) {
                linea = br.readLine();
                String[] l = linea.split("\\s+");
                if (l[0].equals(nom) && l[1].equals(con)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return false;
    }

    public synchronized static void guardadoAutomatico(String nom, int[] info) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea;
        ArrayList<String[]> lineas = new ArrayList<>();
        int contador = -1, temp = -1;
        try {
            while (br.ready()) {
                linea = br.readLine();
                String[] l = linea.split("\\s+");
                contador++;
                if (l[0].equals(nom)) {
                    l[2] = String.valueOf(info[0]);
                    l[3] = String.valueOf(info[1]);
                    l[4] = String.valueOf(info[2]);
                    l[5] = String.valueOf(info[3]);
                    temp = contador;
                }
                lineas.add(l);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        if(temp != -1) {
            try {
                for (String[] ls : lineas) {
                    bw.write(ls[0] + " " + ls[1] + " " + ls[2] + " " + ls[3] + " " + ls[4] + " " + ls[5]);
                    bw.newLine();
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        bw.flush();
        bw.close();
    }
}
