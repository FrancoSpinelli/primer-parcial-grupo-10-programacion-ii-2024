package EntradaSalida;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class EntradaSalida {

    private static Scanner scan = new Scanner(System.in);

    public static char leerChar() {
        String st = scan.next();
        return (st == null || st.length() == 0 ? '0' : st.charAt(0));
    }

    public static String leerString(String texto) {
        mostrarStringSinSalto(texto + " ");
        String st = scan.next();

        return (st == null ? "" : st);
    }

    public static boolean leerBoolean(String texto, String textoTrue, String textoFalse) {
        mostrarString(texto + " (1: " + textoTrue + " | 0: " + textoFalse + ")");
        char c = leerChar();

        if (c != '1' && c != '0') {
            mostrarString("Error. Por favor, ingrese una opción válida. \n");
            return leerBoolean(texto, textoTrue, textoFalse);
        }

        return c == '1';

    }

    public static int leerEntero(String texto) {
        boolean error = false;
        int numero = 0;
        do {
            String input = leerString(texto);
            try {
                numero = Integer.parseInt(input);
                error = false;
            } catch (NumberFormatException e) {
                mostrarString("Error. Por favor, ingrese un número entero válido.");
                error = true;
            }
        } while (error);

        return numero;
    }

    /*
     * public int menu(String texto){
     * int eleccion;
     * String[] lineas = texto.split("\n");
     * 
     * for(int i=0; i<lineas.length; i++){
     * mostrarString(lineas[i]);
     * }
     * eleccion = leerEnteroConLimites("Ingrese la opcion: ", 1, lineas.length+1);
     * 
     * return eleccion;
     * }
     */

    public static int leerEnteroConLimites(String texto, int limiteInferior, int limiteSuperior) {
        boolean error = false;
        int numero = 0;
        do {
            if (error)
                mostrarString("Error. El número debe estar entre " + limiteInferior + " y " + limiteSuperior + ".");
            String input = leerString(texto);
            try {
                numero = Integer.parseInt(input);
                error = (numero < limiteInferior || numero > limiteSuperior) ? true : false;
            } catch (NumberFormatException e) {
                mostrarString("Error. Por favor, ingrese un número entero válido.");
                error = true;
            }
        } while (error);

        return numero;
    }

    public static LocalDate leerFecha(String texto) {
        LocalDate fecha = null;
        mostrarString(texto);
        int dia = leerEnteroConLimites("Ingrese el día (1 - 31): ", 1, 31);
        int mes = leerEnteroConLimites("Ingrese el mes (1 - 12): ", 1, 12);
        int anio = leerEnteroConLimites("Ingrese el año: ", 1900, LocalDate.now().getYear());

        try {
            fecha = LocalDate.of(anio, mes, dia);
        } catch (DateTimeException e) {
            mostrarString("Error. Fecha no válida. Por favor, ingrese una fecha correcta.");
        }

        return fecha;
    }

    public static void mostrarString(String s) {
        System.out.println(s);
    }

    public static void mostrarStringSinSalto(String s) {
        System.out.print(s);
    }

    public static void mostrarString(String s, boolean sA, boolean sB) {

        if (sA)
            System.out
                    .println("---------------------------------------------------------------------------------------");
        System.out.println(s);
        if (sB)
            System.out
                    .println("---------------------------------------------------------------------------------------");
    }

    public static String leerPassword(String texto) {
        return leerString(texto);
    }
}
