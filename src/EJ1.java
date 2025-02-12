
import java.util.Scanner;

public class EJ1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int opc = 0;
        System.out.println("1. Suma");
        System.out.println("2. Resta");
        System.out.println("3. Multiplicación");
        System.out.println("4. División");
        System.out.print("Elija una de las opciones: ");
        opc = s.nextInt();
        System.out.println("Ahora introduzca dos números: ");
        int n1 = s.nextInt();
        int n2 = s.nextInt();
        int num = 0;
        switch (opc) {
            case 1:
                System.out.println("El resulado de sumar " + n1 + " + " + n2 + " es igual a: " +(n1+n2));
                break;
            case 2:
                System.out.println("El resulado de restar " + n1 + " - " + n2 + " es igual a: " +(n1-n2));
                break;
            case 3:
                num = n1;
                for(int i = 1; i < n2; i++){
                    num += n1;
                }
                System.out.println("El resultado de multiplicar " + n1 + " * " + n2 + " es igual a: " +num);
                break;
            case 4:
                num = n1;
                int cont = 0;
                while(num >= n2){
                    num -= n2;
                    cont++;
                }
                System.out.println("El resultado de dividir " + n1 + " entre " + n2 + " es igual a: " +cont);
                break;
            default:
                System.out.println("Elija una opcion correcta");
        }
    }
}
