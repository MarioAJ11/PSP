package EJ2;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        int opc = 0;
        int j = 0;
        do{
            System.out.println("1. Añadir estudiante");
            System.out.println("2. Eliminar estudiante");
            System.out.println("3. Mostar estudiante");
            System.out.println("4. Salir");
            System.out.print("Elija una de las opciones: ");
            opc = s.nextInt();
            switch(opc){
                case 1:
                    float notaMedia = 0;
                    System.out.println("Añade un nuevo estudiante: ");
                    System.out.print("Nombre: ");
                    String nombre = s.next();
                    System.out.print("Edad: ");
                    int edad = s.nextInt();
                    System.out.print("Nota de SGE: ");
                    float notaSge = s.nextFloat();
                    notaMedia += notaSge;
                    System.out.print("Nota de DI: ");
                    float notaDI = s.nextFloat();
                    notaMedia += notaDI;
                    System.out.print("Nota de AD: ");
                    float notaAD = s.nextFloat();
                    notaMedia += notaAD;
                    System.out.print("Nota de PMDM: ");
                    float notaPMDM = s.nextFloat();
                    notaMedia += notaPMDM;
                    System.out.print("Nota de PSP: ");
                    float notaPSP = s.nextFloat();
                    notaMedia += notaPSP;
                    System.out.print("Nota de HLC: ");
                    float notaHLC = s.nextFloat();
                    notaMedia += notaHLC;
                    System.out.print("Nota de ING: ");
                    float notaING = s.nextFloat();
                    notaMedia += notaING;
                    System.out.print("Nota de EINEM: ");
                    float notaEINEM = s.nextFloat();
                    notaMedia += notaEINEM;
                    notaMedia = notaMedia / 12;
                    j = j + 1;
                    System.out.println("Codigo estudiante: " + j);
                    Estudiante e = new Estudiante(j, nombre, edad, notaSge, notaDI, notaAD, notaPMDM, notaPSP, notaHLC, notaING, notaEINEM, notaMedia);
                    estudiantes.add(e);
                    break;
                case 2:
                    System.out.println("Elimina un estudiante insertando su codigo: ");
                    int codigo = s.nextInt();
                    for(Estudiante est : estudiantes){
                        if(est.getCodigo() == codigo){
                            estudiantes.remove(est);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Elimina un estudiante insertando su codigo: ");
                    System.out.println(estudiantes.toString());
                    break;
                case 4:
                    System.out.println("Salir");
                    break;
                        default:
                            System.out.println("Opcion no valida");
            }
        }while (opc != 4);
    }
}
