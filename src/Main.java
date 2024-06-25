import Control.Control;
import EntradaSalida.EntradaSalida;

public class Main {
  public static void main(String[] args) throws Exception {

    try {
      Control control = new Control();
      control.ejecutar();
    } catch (Exception e) {
      EntradaSalida.error("Error inesperado: " + e.getMessage());
    }

  }
}