package Personas;
import java.util.ArrayList;

import Auto.Auto;
import Interfaces.MenuCliente;
import Oficina.Oficina;
import Reserva.Reserva;

import java.time.LocalDate;

public class Cliente extends Persona {
    private ArrayList<Auto> favoritos;
    private ArrayList<Reserva> reservas;

    public Cliente(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new MenuCliente());
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
