package Personas;
import java.util.ArrayList;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeEliminar.CapacidadDeSerEliminado;
import Interfaces.CapazDeEliminar.CapazDeEliminar;
import Interfaces.CapazDeListar.CapacidadDeListarPersonas;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuCliente;
import Oficina.Oficina;
import Reserva.Reserva;

import java.time.LocalDate;

public class Cliente extends Persona implements CapacidadDeSerEliminado{
    private ArrayList<Auto> favoritos;
    private ArrayList<Reserva> reservas;

    public Cliente(int id, int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(id, dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new CapacidadDeVerMenuCliente(this));
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

    @Override
    public void eliminar(){
        EntradaSalida.mostrarString("No puede eliminar");
    }

    @Override
    public boolean capacidadDeSerEliminado(){
        return true;
    }

}
