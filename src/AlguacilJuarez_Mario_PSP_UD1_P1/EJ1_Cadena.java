package AlguacilJuarez_Mario_PSP_UD1_P1;

import java.util.Scanner;

public class EJ1_Cadena {
    public static void main(String[] args) {
        //Creamos un array de chars q con caracteres unicos donde introducimos todo el abecedario,
        //tmb creamos un string cadena q sera donde se guarde la cadena producida aleatoriamente
        Scanner s = new Scanner(System.in);
        char []letras = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String cadena;
        int n = 1;
        //pedimos cuantas cadenas desea crear
        try{//le corregimos posibles errores al meter datos
            System.out.print("Selecione cuantas cadenas quiere: ");
             n = s.nextInt();
            while(n <= 0){
                System.out.print("Selecione cuantas cadenas quiere, siendo esta mayor que 0: ");
                n = s.nextInt();
            }
        }catch(Exception e){
            System.out.println("Error en la entrada: " + e.getMessage());
            return;
        }
        System.out.println();//un saltillo de linea tontorron
        //bien aqui hacemos tres cosas, primero un for con el limite de las cadenas q hemos pedido q introduzca antes
        for (int i = 0; i < n; i++) {
            cadena = "";//vaciamos la cadena
            //luego aqui otro for donde la longitud es aleatoria de maximo 20 pero nunca 0, eso lo hacemos con el +1
            for (int j = 0; j < ((int) (Math.random() * 20 + 1)); j++) {
                //y luego aqui le vamos añadiendo caracter a caracter aleatoriamente
                cadena += letras[(int) (Math.random() * 27)];//pongo 28 pq es casi imposible q salga el 28 q ese estaria fuera del array letras pq el maximo es 27
            }
            System.out.println("Cadena " + (i+1) + " : " + cadena);//lo mostramos
        }
        s.close();
    }
}
