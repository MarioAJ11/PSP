package AlguacilJuarez_Mario_PSP_UD1_2.EJ2;

public class Caja {
    private static int tam = 0;
    private static final int max = 5;
    private static final int espera = 1000;
    private static int colaElectrones = 5;
    private static int colaPositrones = 5;

    public synchronized void pasanElectrones(){
        try{
            if(!pueden(0)){
                wait();
            } else {
                System.out.println("\tEntran los electrones");
                System.out.println("-----------------------------------");
                while (pueden(0)) {
                    if (tam != max && colaElectrones != 0) {
                        tam++;
                        colaElectrones--;
                        System.out.println("Entra electron, electrones en fiesta: " + tam + ". Cola: " + colaElectrones);
                    } else {
                        System.out.println("\t\tCAJA LLENA POR ELECTRONES");
                        Thread.sleep(espera);
                        generar();
                    }
                }
                System.out.println("-----------------------------------");
                System.out.println("\tSalen los electrones\n");
                tam = 0;
                pasanPositrones();
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public synchronized void pasanPositrones(){
        try{
            if(!pueden(1)){
                wait();
            } else {
                System.out.println("\tEntran los positrones");
                System.out.println("-----------------------------------");
                while (pueden(1)) {
                    if (tam != max && colaPositrones != 0) {
                        tam++;
                        colaPositrones--;
                        System.out.println("Entra positron, positrones en fiesta: " + tam + ". Cola: " + colaPositrones);
                    } else {
                        System.out.println("\t\tCAJA LLENA POR POSITRONES");
                        Thread.sleep(espera);
                        generar();
                    }
                }
                System.out.println("-----------------------------------");
                System.out.println("\tSalen los positrones\n");
                tam = 0;
                pasanElectrones();
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public synchronized void generar(){
        double random = Math.random() * 2.2;
        int num;
        num = (int) (Math.random() * 4 + 1);
        if (random <= 1) {
            colaPositrones += num;
            System.out.println("  Positrones añadidos a la cola: " + num + ", cola total: " + colaPositrones);
        } else {
            colaElectrones += num;
            System.out.println("  Electrones añadidos a la cola: " + num + ", cola total: " + colaElectrones);
        }
    }
    public static boolean pueden(int num){
        if(num == 0){
            if(colaPositrones < max && colaElectrones >= max){
                return true;
            } else if(colaElectrones >= max*2){
                return false;
            }else if (colaElectrones < max && colaPositrones < max){
                return true;
            } else if( colaPositrones >= max && colaElectrones >= max){
                return true;
            } else if (colaPositrones >= max && colaElectrones < max){
                return false;
            } else {
                return false;
            }
        } else if (num == 1) {
            if(colaPositrones < max && colaElectrones >= max){
                return false;
            } else if (colaElectrones >= max && colaElectrones < max) {
                return true;
            }else if(colaPositrones >= max*2){
                return false;
            }else if (colaElectrones < max && colaPositrones < max){
                return true;
            } else if( colaElectrones >= max ){
                return true;
            }  else if (colaPositrones < max && colaElectrones >= max){
                return false;
            } else {
                return false;
            }
        }
        return false;
    }
}
