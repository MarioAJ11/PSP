
import java.util.Scanner;

public class Ahorcado {
    private static String palabra = "";
    public static void mostrar(String texto, char caracter) {
       for (int i = 0; i < texto.length(); i++) {
           if(caracter == texto.charAt(i)) {
               //palabra.charAt(i) = caracter;
           }
           if((palabra.charAt(i) == '_') || (palabra.charAt(i) == ' ')) {
               palabra.charAt(i = '_');
           }

       }
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String []palabras = {"Hola", "Caracol", "Duro", "Nariz", "Boca", "Cubata", "Salmorejo", "Canelones", "Kazajistan"};
        boolean mal;
        String palabra = palabras[(int)(Math.random()*9)];
        int contador = palabra.length();
        int vidas = 5;
        System.out.println("Bienvenido al juego del Ahorcado");
        do{
            System.out.println("###################################################");
            System.out.println("\t\t\tLe quedan " + vidas + " vidas");
            System.out.println("###################################################");
            do{
                mal = true;
                System.out.print("Ingrese una letra: ");
                char letra = s.next().charAt(0);
                for (int i = 0; i < palabra.length(); i++) {
                    if (palabra.charAt(i) == letra) {
                        mal = false;
                        mostrar(palabra, letra);
                        contador--;
                    }
                }
                if(contador == 0) {
                    System.out.println("Ganastes");
                    System.exit(0);
                }
            }while(!mal);
            vidas--;
        }while(vidas > 0);
    }
}
