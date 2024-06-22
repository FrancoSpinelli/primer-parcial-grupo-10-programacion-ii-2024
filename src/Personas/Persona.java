package Personas;
import java.time.LocalDate;

import Interfaces.CapazDeListar.CapacidadDeListarStrategy;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenu;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenu;

public class Persona {
    private int id;
    private int dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String contrasenia;
    private CapacidadDeVerMenu menuStrategy;
    private CapacidadDeListarStrategy listadorStrategy;

    public Persona(int id, int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email, String contrasenia) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public void setMenuStrategy(CapacidadDeVerMenu menuStrategy) {
        this.menuStrategy = menuStrategy;
    }

    public CapacidadDeVerMenu getMenuStrategy() {
        return menuStrategy;
    }

    public void setListadorStrategy(CapacidadDeListarStrategy<?> listadorStrategy) {
        this.listadorStrategy = listadorStrategy;
    }

    public CapacidadDeListarStrategy<?>  getListarStrategy(){
        return listadorStrategy;
    }

    @Override
    public String toString(){
        return "ID: " + id + "DNI: " + dni + "Nombre: " + nombre + "Fecha de nacimiento: " + fechaNacimiento + 
        "Teléfono: " + telefono + "Correo Electrónico: " + email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public boolean coincideDni(Persona persona, int dni){
        return this.dni == dni;
    }
    public boolean coincideContrasenia(Persona persona, String contrasenia){
        return this.contrasenia.equals(contrasenia);
    }
    public boolean coincideUsuario(Persona persona, String correo){
        return this.email.equals(correo);
    }
}
