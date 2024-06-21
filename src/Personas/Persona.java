package Personas;
import java.time.LocalDate;

import Interfaces.CapazDeListar.CapacidadDeListarStrategy;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuStrategy;
import Interfaces.CapazDeVerMenu.MenuStrategy;

public class Persona {
    private int dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private String contrasenia;
    private CapacidadDeVerMenuStrategy menuStrategy;
    private CapacidadDeListarStrategy listadorStrategy;

    public Persona(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public void setMenuStrategy(CapacidadDeVerMenuStrategy menuStrategy) {
        this.menuStrategy = menuStrategy;
    }

    public void setListadorStrategy(CapacidadDeListarStrategy<?> listadorStrategy) {
        this.listadorStrategy = listadorStrategy;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return "DNI: " + dni + "Nombre: " + nombre + "Fecha de nacimiento: " + fechaNacimiento + 
        "Teléfono: " + telefono + "Correo Electrónico: " + email;
    }

    public static boolean coincideDni(Persona persona, int dni){
        return persona.dni == dni;
    }
}
