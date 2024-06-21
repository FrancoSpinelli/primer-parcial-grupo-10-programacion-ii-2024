import java.util.ArrayList;

import Auto.Auto;
import Auto.Gasolina;
import CasaMatriz.CasaMatriz;
import Interfaces.CreadorCliente;
import Interfaces.ListadorClientes;
import Oficina.Oficina;
import Personas.Admin;
import Personas.Cliente;
import Personas.Persona;
import Personas.Vendedor;

import java.time.LocalDate;

import enums.Color;
import enums.Marca;

public class Main {
    public static void main(String[] args) throws Exception {

        ArrayList<Persona> personas = new ArrayList<Persona>();
        ArrayList<Auto> autos = new ArrayList<Auto>();
        ArrayList<Oficina> oficinas = new ArrayList<Oficina>();

        CasaMatriz casaMatriz;

        Admin admin1 = new Admin(1234, "admin", LocalDate.now(), "1234", "vendedor1@vendedor.com", "1234");
        Vendedor vendedor1 = new Vendedor(1234, "vendedor1", LocalDate.now(), "1234", "vendedor1@vendedor.com", "1234");
        Cliente cliente1 = new Cliente(1234, "cliente1", LocalDate.now(), "1234", "cliente1@cliente.com", "1234");

        Auto auto1 = new Auto("ABC123", "Corolla", 10000, Color.AZUL, Marca.CHEVROLET, new Gasolina(10000));
        Auto auto2 = new Auto("DEF456", "Civic", 20000, Color.ROJO, Marca.FORD, new Gasolina(20000));
        Auto auto3 = new Auto("GHI789", "Coupe", 30000, Color.BLANCO, Marca.BMW, new Gasolina(30000));

        Oficina oficina1 = new Oficina(1, "Av. Siempre Viva 123", "123456789");
        Oficina oficina2 = new Oficina(2, "Av. Siempre Viva 456", "987654321");

        personas.add(admin1);
        personas.add(vendedor1);
        personas.add(cliente1);

        autos.add(auto1);
        autos.add(auto2);
        autos.add(auto3);

        oficinas.add(oficina1);
        oficinas.add(oficina2);

        //System.out.println("\nClientes:");
        //casaMatriz.mostrarListadoPersonas(new ListarClientesStrategy());
        

        casaMatriz = new CasaMatriz(personas, autos, oficinas);

    
        admin1.setCreadorStrategy(new CreadorCliente());
        admin1.crear();

        casaMatriz.setListadorStrategy(new ListadorClientes());
        casaMatriz.mostrarListadoPersonas(new ListadorClientes());

    }
}
