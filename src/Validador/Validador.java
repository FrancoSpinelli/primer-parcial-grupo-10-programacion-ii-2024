package Validador;

public class Validador {

    public static boolean validarRango(int valor, int min, int max) {
        return valor >= min && valor <= max;
    }

    public static boolean validarString(String valor) {
        return valor != null && !valor.isEmpty();
    }

    public static boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean validarContrasenia(String contrasenia) {
        return contrasenia != null && contrasenia.length() >= 4;
    }

}
