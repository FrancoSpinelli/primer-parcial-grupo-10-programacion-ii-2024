import java.util.ArrayList;

public class CasaMatriz {
    private ArrayList<Persona> personas;
    private ArrayList<Auto> autos;
    private ArrayList<Oficina> oficinas;

    public CasaMatriz(ArrayList<Persona> personas, ArrayList<Auto> autos, ArrayList<Oficina> oficinas) {
        EntradaSalida.mostrarString("Bienvenido a la Casa Matriz");
        this.personas = personas;
        this.autos = autos;
        this.oficinas = oficinas;

        Admin admin = (Admin) this.personas.get(0); 
        Vendedor vendedor1 = (Vendedor) this.personas.get(1);
        Oficina oficina = oficinas.get(0);

        
        admin.asignarVendedorAOficina(vendedor1,oficina);
        for (Auto auto : autos) {
            admin.asignarAutoAOficina(auto,oficina);
        }

        oficina.verListadoAutos();
        oficina.verListadoReservas();
    }

    public void login() {

    }

    public void logout() {

    }

}
