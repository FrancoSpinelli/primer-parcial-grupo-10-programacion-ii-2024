import java.util.Date;

public class Vendedor extends Persona {

    public Vendedor(int dni, String nombre, Date fechaNacimiento, int documento, String telefono, String email,
            String contrasenia) {
        super(dni, nombre, fechaNacimiento, documento, telefono, email, contrasenia);
    }

    public void listarReservasPendientes() {

    }

    public void aceptarReserva(Reserva r) {

    }

    public void rechazarReserva(Reserva r) {

    }

    public void entregarAuto(Reserva r) {

    }
}
