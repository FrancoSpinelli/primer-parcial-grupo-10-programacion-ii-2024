import java.util.Date;

public class Admin extends Persona {

    public Admin(int dni, String nombre, Date fechaNacimiento, String telefono, String email, String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
    }
    public void listar() {

    }

    public void crear() {

    }

    public void eliminar() {

    }

    public void asignarAutoAOficina(Auto auto, Oficina oficina) {
        oficina.agregarAuto(auto);
    }

    public void asignarVendedorAOficina(Vendedor vendedor, Oficina oficina) {
        oficina.asignarVendedor(vendedor);
    }
}
