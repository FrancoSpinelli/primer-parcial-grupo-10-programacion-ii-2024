import java.util.ArrayList;
import java.util.Date;

public class Oficina {
    private int id;
    private String dirección;
    private String teléfono;
    private Vendedor vendedor;
    private ArrayList<Auto> autos;
    private ArrayList<Reserva> reservas;

    @Override
    public String toString() {
        return "Oficina #" + id + " (" + dirección + ")";
    }

    public Oficina(int id, String dirección, String teléfono) {
        this.id = id;
        this.dirección = dirección;
        this.teléfono = teléfono;

        this.autos = new ArrayList<Auto>();
        this.reservas = new ArrayList<Reserva>();

    }

    public void asignarVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        EntradaSalida.mostrarString("Se asignó el vendedor " + vendedor.toString() + " a la " + this.toString());
    }

    public void agregarAuto(Auto auto) {

        if (!this.tieneVendedor()) {
            EntradaSalida.mostrarString("No se puede agregar auto a oficina sin vendedor");
            return;
        }

        this.autos.add(auto);
        EntradaSalida.mostrarString(this.toString());
        EntradaSalida.mostrarString("Se agregó el " + auto.toString() + ", a la " + this.toString(), true, true);
    }

    public void verListadoAutos() {
        if (this.autos.isEmpty()) {
            EntradaSalida.mostrarString("No hay autos en la " + this.toString(), false, true);
            return;
        }

        EntradaSalida.mostrarString("Listado de autos en la " + this.toString(), true, true);
        for (Auto auto : this.autos) {
            EntradaSalida.mostrarString(auto.toString(), false, true);
        }
    }

    public void verListadoReservas() {
        if (this.reservas.isEmpty()) {
            EntradaSalida.mostrarString("No hay reservas en la " + this.toString(), false, true);
            return;
        }

        EntradaSalida.mostrarString("Listado de reservas en la " + this.toString(), true, true);
        for (Reserva reserva : this.reservas) {
            EntradaSalida.mostrarString(reserva.toString(), false, true);
        }
    }

    public void crearReserva(Cliente cliente) {

        Reserva reserva = new Reserva(generadorId(), this.autos, cliente, this, new Date(), new Date());
        this.reservas.add(reserva);
        EntradaSalida.mostrarString("Se creó la reserva " + reserva.toString());
    }

    private boolean tieneVendedor() {
        return this.vendedor != null;
    }

    private String generadorId() {
        return "RS" + this.id + "-000" + this.reservas.size() + 10;
    }

}
