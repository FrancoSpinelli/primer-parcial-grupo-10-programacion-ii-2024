import java.util.ArrayList;

import enums.Color;
import enums.Marca;

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

        this.autos = new ArrayList<Auto>();
        this.reservas = new ArrayList<Reserva>();

        this.autos.add(new Auto("ABC123", "Corolla", 10000, Color.AZUL, Marca.CHEVROLET, new Gasolina(10000)));
        this.autos.add(new Auto("DEF456", "Civic", 20000, Color.ROJO, Marca.FORD, new Gasolina(20000)));
        this.autos.add(new Auto("GHI789", "Coupe", 30000, Color.BLANCO, Marca.BMW, new Gasolina(30000)));
    }

    @Override
    public String toString() {
        return "Oficina [sucursalId=" + sucursalId + ", dirección=" + dirección + ", teléfono=" + teléfono
                + ", vendedor=" + vendedor + ", autos=" + autos + ", reservas=" + reservas + "]";
    }

    public void asignarVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void agregarAuto(Auto auto) {
        this.autos.add(auto);
    }

}
