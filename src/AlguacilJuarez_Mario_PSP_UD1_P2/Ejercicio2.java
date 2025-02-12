package AlguacilJuarez_Mario_PSP_UD1_P2;

import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int sol = 0;
        int edad;
        int edadMin = 100;
        int edadMax = 0;
        String nombre;
        String nombre1 = "";
        String nombre2 = "";
        while (true) {
            nombre = "";
            System.out.println((sol+1) + " - Introduce datos de alumnos: ");
            while (true) {
                System.out.println("Escribe un nombre: ");
                nombre = s.next();
                if (nombre.equals("*")) {
                    System.out.println("Fin del proceso de lectura...");
                    System.out.println("Datos leídos: " + sol);
                    System.out.println("Alumno con más edad: " + nombre2);
                    System.out.println("Alumno con menos edad: " + nombre1);
                    return;
                }
                if (!nombre.isEmpty()) {
                    break;
                }
                System.out.println("Incorrecto, escríbelo de nuevo: ");
            }
            edad = 0;
            while (true) {
                System.out.println("Introduce la edad entre 1 y 99: ");
                try {
                    edad = s.nextInt();
                    if (!(edad >= 1 && edad <= 99)) {
                        System.out.println("Incorrecto, debe estar entre 1 y 99");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Debes introducir un número.");
                }
            }

            System.out.println("Datos introducidos: " + nombre + ", " + edad);
            sol++;

            if (edad > edadMax) {
                edadMax = edad;
                nombre2 = nombre;
            }
            if (edad < edadMin) {
                edadMin = edad;
                nombre1 = nombre;
            }
        }
    }
}

