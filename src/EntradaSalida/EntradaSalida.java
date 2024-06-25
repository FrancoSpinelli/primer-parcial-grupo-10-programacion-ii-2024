package EntradaSalida;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import CasaMatriz.Const;

public class EntradaSalida {

    private static Scanner scan = new Scanner(System.in);

    public static char leerChar() {
        String st = scan.next();
        return (st == null || st.length() == 0 ? '0' : st.charAt(0));
    }

    public static String leerString(String texto) {
        mostrarStringSinSalto(texto + " ");
        String st = scan.nextLine();

        return (st == null ? "" : st);
    }

    public static boolean leerBoolean(String texto, String textoTrue, String textoFalse) {
        mostrarStringSinSalto(texto + " (1: " + textoTrue + " | 0: " + textoFalse + "): ");

        char c = scan.nextLine().charAt(0);

        if (c != Const.CARACTER_FALSE && c != Const.CARACTER_TRUE) {
            error("Selección inválida. Por favor, ingrese 1 o 0.");
            return leerBoolean(texto, textoTrue, textoFalse);
        }
        return c == Const.CARACTER_TRUE;

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

    public static int leerEnteroConLimites(String texto, int limiteInferior, int limiteSuperior) {
        boolean error = false;
        int numero = 0;
        do {
            if (error)
                error("El número debe estar entre " + limiteInferior + " y " + limiteSuperior + ".");
            String input = leerString(texto);
            try {
                numero = Integer.parseInt(input);
                error = (numero < limiteInferior || numero > limiteSuperior) ? true : false;
            } catch (NumberFormatException e) {
                error("Selección inválida. Por favor, ingrese un número entero válido.");
                error = true;
            }
        } while (error);

        return numero;
    }

    public static LocalDate leerFechaSinLimites(String texto) {
        return leerFecha(texto);
    }

    public static LocalDate leerFechaAnteriorAHoy(String texto) {
        LocalDate fecha = leerFecha(texto);

        if (fecha.isAfter(LocalDate.now())) {
            error("La fecha debe ser anterior a hoy.");
            return leerFechaAnteriorAHoy(texto);
        }

        return fecha;
    }

    public static LocalDate leerFechaPosteriorAHoy(String texto) {
        LocalDate fecha = leerFecha(texto);
        if (fecha.isBefore(LocalDate.now())) {
            error("La fecha debe ser posterior a hoy.");
            return leerFechaPosteriorAHoy(texto);
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
                    .println(
                            "-----------------------------------------------------------------------------------------------");
        System.out.println(s);
        if (sB)
            System.out
                    .println(
                            "-----------------------------------------------------------------------------------------------");
    }

    public static String leerPassword(String texto) {
        return leerString(texto);
    }

    public static void saltoDeLinea() {
        EntradaSalida.mostrarString("");
    }

    public static void error(String mensaje) {
        mostrarString("\nXXXXXXXX Error: " + mensaje + " XXXXXXXX\n");
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    public static void advertencia(String mensaje) {
        mostrarString("\n******** " + mensaje + " ********\n");
    }

    public static void cualquierTeclaParaContinuar() {
        mostrarString("Presione cualquier tecla para continuar...");
        scan.nextLine();
        limpiarPantalla();
    }

    private static LocalDate leerFecha(String texto) {
        mostrarString(texto);
        LocalDate fecha = null;
        int dia = leerEnteroConLimites("Ingrese el día (dd): ", Const.LIMITE_INFERIOR_DEFAULT,
                Const.LIMITE_SUPERIOR_DIA);
        int mes = leerEnteroConLimites("Ingrese el mes (mm): ", Const.LIMITE_INFERIOR_DEFAULT,
                Const.LIMITE_SUPERIOR_MES);
        int anio = leerEnteroConLimites("Ingrese el año (aaaa): ", Const.LIMITE_INFERIOR_ANIO,
                Const.LIMITE_SUPERIOR_ANIO);

        try {
            fecha = LocalDate.of(anio, mes, dia);
        } catch (DateTimeException e) {
            error("Fecha no válida. Por favor, ingrese una fecha correcta.");
        }

        return fecha;
    }
}
