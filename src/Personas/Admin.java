package Personas;
import java.time.LocalDate;
import java.util.List;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Interfaces.CreadorStrategy;
import Interfaces.MenuAdmin;
import Interfaces.ListadorStrategy;
import Oficina.Oficina;

public class Admin extends Persona {

    private ListadorStrategy<?> listadorStrategy;
    private CreadorStrategy creadorStrategy;

    public Admin(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email, String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new MenuAdmin(this));
    }

    public void setCreadorStrategy(CreadorStrategy creadorStrategy){
        this.creadorStrategy = creadorStrategy;
    }

    public void setListadorStrategy(ListadorStrategy<?> listadorStrategy){
        this.listadorStrategy = listadorStrategy;
    }

    public void crear() {
        creadorStrategy.crear();
    }

    public void asignarAutoAOficina(Auto auto, Oficina oficina) {
        oficina.agregarAuto(auto);
    }

    public void asignarVendedorAOficina(Vendedor vendedor, Oficina oficina) {
        oficina.asignarVendedor(vendedor);
    }
}