import java.util.Scanner;

public class EntradaSalida {

    private static final Scanner scan = new Scanner(System.in);

    public static char leerChar(String texto) {
        mostrarString(texto);
        String st = scan.nextLine();
        return (st == null || st.length() == 0 ? '0' : st.charAt(0));
    }

    public static String leerString(String texto) {
        mostrarString(texto);
        String st = scan.nextLine();
        return (st == null ? "" : st);
    }

    public static boolean leerBoolean(String texto) {
        mostrarString("CONTINUAR = 1.\nSALIR = Cualquier otra tecla \n");
        char c = leerChar(texto);
        if (c == '1')
            return true;
        return false;

    }

    public static void mostrarString(String s) {
        System.out.println(s);
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
