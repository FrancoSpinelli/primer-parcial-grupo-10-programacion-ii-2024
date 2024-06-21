package Personas;
import java.time.LocalDate;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeCrear.CapacidadDeCrearStrategy;
import Interfaces.CapazDeListar.CapacidadDeListarStrategy;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuAdmin;
import Oficina.Oficina;

public class Admin extends Persona {

    private CapacidadDeListarStrategy<?> listadorStrategy;
    private CapacidadDeCrearStrategy creadorStrategy;

    public Admin(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email, String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new CapacidadDeVerMenuAdmin(this));
    }

    public void setCreadorStrategy(CapacidadDeCrearStrategy creadorStrategy){
        this.creadorStrategy = creadorStrategy;
    }

    public void setListadorStrategy(CapacidadDeListarStrategy<?> listadorStrategy){
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