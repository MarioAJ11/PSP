package AlguacilJuarez_Mario_PSP_UD1_P2;

public class Ejercicio1 {
    public static void main(String[] args) {
        int []array1 = new int[10];
        int []array2 = new int[10];
        int []sol = new int[10];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = Integer.parseInt(args[i]);
        }
        for (int i = 0; i < array2.length; i++) {
            array2[i] = Integer.parseInt(args[i + array1.length]);
        }
        for (int i = 0; i < sol.length; i++) {
            sol[i] = array1[i]*array2[i];
        }
        for (int i = 0; i < sol.length; i++) {
            System.out.println(sol[i]);
        }
    }
}
