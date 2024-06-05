import java.util.Date;

public class Persona {
    private int dni;
    private String nombre;
    private Date fechaNacimiento;
    private int documento;
    private String telefono;
    private String email;
    private String contrasenia;

    public Persona(int dni, String nombre, Date fechaNacimiento, int documento, String telefono, String email,
            String contrasenia) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.documento = documento;
        this.telefono = telefono;
        this.email = email;
        this.contrasenia = contrasenia;
    }
}
