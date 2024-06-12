import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Persona {
    private ArrayList<Auto> favoritos;
    private ArrayList<Reserva> reservas;

    public Cliente(int dni, String nombre, Date fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.favoritos = new ArrayList<Auto>();
    }

    public void agregarFavoritos(Auto a){

    }

    public void sacarFavoritos(Auto a){

    }

    public void crear(){

    }

    public void retirarAutos(Reserva r){

    }

    public void devolverAuto(Reserva r, Oficina o){

    }
}
