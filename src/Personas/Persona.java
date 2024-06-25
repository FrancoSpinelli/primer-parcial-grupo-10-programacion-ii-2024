package Personas;

import java.io.Serializable;
import java.time.LocalDate;

import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeListar.CapacidadDeListarStrategy;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenu;
import enums.Rol;

public abstract class Persona implements Serializable{
    private int id;
    private int dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String contrasenia;
    private CapacidadDeVerMenu menu;
    private CapacidadDeListarStrategy listadorStrategy;

    @Override
    public String toString() {
        return id + " - " + getRol() + " - DNI: " + dni + " Nombre: " + nombre + " Nac.: " + fechaNacimiento + " Tel.: "
                + telefono
                + " Email: " + email;
    }

    public Persona(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        this.id = CasaMatriz.generarIdPersona();
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public void setMenu(CapacidadDeVerMenu menu) {
        this.menu = menu;
    }

    public CapacidadDeVerMenu getMenu() {
        return menu;
    }

    public void setListadorStrategy(CapacidadDeListarStrategy<?> listadorStrategy) {
        this.listadorStrategy = listadorStrategy;
    }

    public CapacidadDeListarStrategy<?> getListarStrategy() {
        return listadorStrategy;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public boolean coincideDni(int dni) {
        return this.dni == dni;
    }

    public boolean coincideContrasenia(String contrasenia) {
        return this.contrasenia.equals(contrasenia);
    }

    public boolean coincideUsuario(String correo) {
        return this.email.equals(correo);
    }

    public boolean coincideUsuarioYContrasenia(String correo, String contrasenia) {
        return coincideUsuario(correo) && coincideContrasenia(contrasenia);
    }

    public String verPersona() {
        String msg = id + " - " + getRol() + " - DNI: " + dni + " Nombre: " + nombre + " Nac.: " + fechaNacimiento
                + "\n    Tel.: " + telefono + " Email: " + email;
        return msg;
    }

    abstract public Rol getRol();

    public int getId() {
        return id;
    }

    public int getDni() {
       return dni;
    }

    public  LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }
}
