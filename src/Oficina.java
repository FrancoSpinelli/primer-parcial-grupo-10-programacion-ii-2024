import java.util.ArrayList;

public class Oficina {
    private int sucursalId;
    private String dirección;
    private String teléfono;
    private Vendedor vendedor;
    private ArrayList<Auto> autos;
    private ArrayList<Reserva> reservas;
    

    public Oficina(int sucursalId, String dirección, String teléfono) {
        this.sucursalId = sucursalId;
        this.dirección = dirección;
        this.teléfono = teléfono;
    }

    public void asignarVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

}
