package AlguacilJuarez_Mario_PSP_UD3_P2;

import java.util.ArrayList;
import java.util.Arrays;

public class Juego {
    private static final ArrayList<Character> vocales = new ArrayList<>(Arrays.asList('a','e','i','o','u'));
    private static final ArrayList<Character> consonantes = new ArrayList<>(Arrays.asList('b','c','d','f','g','h','j','k','l','m','n','ñ','p','q','r','s','t','v','w','x','y','z'));
    private static String refranBase;
    private static String refranActualizado = null;
    private static int cont, intentos = 0;
    //Refranes
    private static final String[] refranes = {
            "por el interes, te quiero andres",
            "a caballo regalado no le mires el diente",
            "balbalo, nueve, nueve",
            "barriga llena, corazon contento",
            "quien mucho abarca, poco aprieta",
            "tira la piedra y esconde la mano",
            "dios los cria y ellos se juntan",
            "mas vale maña que fuerza",
            "hoy por ti, mañana por mi"
    };

    public static void elegirRefran() {
        refranBase = refranes[(int) (Math.random() * refranes.length)];
        recorrerRefran('-');
    }
    public static void recorrerRefran(char l) {
        String refran = "";
        cont = 0;
        if(refranActualizado != null) {
            for (int i = 0; i < refranBase.length(); i++) {
                if (refranBase.charAt(i) == l && refranActualizado.charAt(i) == '-') {
                    refran += l;
                    cont++;
                } else if (refranBase.charAt(i) == ',') {
                    refran += ',';
                } else if (refranBase.charAt(i) == ' ') {
                    refran += ' ';
                } else if (refranActualizado.charAt(i) != '-') {
                    refran += refranActualizado.charAt(i);
                } else {
                    refran += '-';
                }
            }
            refranActualizado = refran;
        } else {
            refranActualizado = "";
            for (int i = 0; i < refranBase.length(); i++) {
                if (refranBase.charAt(i) == ',') {
                    refranActualizado += ',';
                } else if (refranBase.charAt(i) == ' ') {
                    refranActualizado += ' ';
                } else {
                    refranActualizado += '-';
                }
            }
        }

    }
    public static String comprobarRefran(String msg, boolean individual, String user) {
        String[] mensajes = msg.split("\\s+");
        String texto = "-Err. Comando desconocido.";
        switch(mensajes[0]) {
            case "VOCAL":
                intentos++;
                if(vocales.contains(mensajes[1].charAt(0))) {
                    recorrerRefran(mensajes[1].charAt(0));
                    if(cont > 0) {
                        if(individual) {
                            texto = "+Ok. Existe la vocal. Refrán: " + refranActualizado;
                        } else {
                            if(Multijugador.getPts(user) >= 50){
                                texto = "+Ok. Existe la vocal. -50pts. Refrán: " + refranActualizado;
                                Multijugador.setPts(user,(Multijugador.getPts(user) - 50));
                            } else {
                                texto = "-Err. Saldo insuficiente.";
                            }
                        }
                    } else {
                        texto = "-Ok. Vocal Incorrecta. Refrán: " + refranActualizado;
                    }
                } else if (consonantes.contains(mensajes[1].charAt(0))) {
                    texto = "-Err. Debe introducir una vocal no una consonante.";
                }
                break;
            case "CONSONANTE":
                intentos++;
                if(consonantes.contains(mensajes[1].charAt(0))) {
                    recorrerRefran(mensajes[1].charAt(0));
                    if(cont > 0) {
                        if(individual) {
                            texto = "+Ok. Existe la consonante. Refrán: " + refranActualizado;
                        } else {
                            texto = "+Ok. Existe la consonante. +" + 50*cont + "pts. Refrán: " + refranActualizado;
                            Multijugador.setPts(user,(Multijugador.getPts(user) + (50*cont)));
                        }
                    } else {
                        texto = "-Ok. Consonante Incorrecta. Refrán: " + refranActualizado;
                    }
                } else if (vocales.contains(mensajes[1].charAt(0))) {
                    texto = "-Err. Debe introducir una consonante no una vocal.";
                }
                break;
            case "RESOLVER":
                String[] resolver = refranBase.split("\\s+");
                int contadorAciertos = 0;
                for(int i = 0; i < resolver.length; i++) {
                    if(mensajes[i+1].equals(resolver[i])) {
                        contadorAciertos++;
                    }
                }
                if(contadorAciertos == resolver.length) {
                    HiloAhorcado.setPuntos(getPuntos() + HiloAhorcado.getPuntos());
                    texto = "Perfecto, refrán acertado correctamente, puntos obtenidos " + getPuntos() + " Refrán: " + refranBase;
                    HiloAhorcado.setAcertado(0);
                } else {
                    texto = "-Err. Mal :(, refrán incorrecto, el refrán era: " + refranBase;
                    HiloAhorcado.setAcertado(1);
                }
                break;
        }
        return texto;
    }

    public static int getPuntos() {
        int puntos = 0;
        if(intentos > 0 && intentos <= 5) {
            puntos = 150;
        } else if (intentos > 5 && intentos <= 8) {
            puntos = 100;
        } else if (intentos > 8 && intentos <= 11) {
            puntos = 70;
        } else if (intentos > 12 && intentos <= 15) {
            puntos = 50;
        }
        return puntos;
    }
}