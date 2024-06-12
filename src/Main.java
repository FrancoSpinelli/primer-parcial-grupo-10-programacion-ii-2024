import java.util.ArrayList;
import java.util.Date;

import enums.Color;
import enums.Marca;

public class Main {
    public static void main(String[] args) throws Exception {

        ArrayList<Persona> personas = new ArrayList<Persona>();
        ArrayList<Auto> autos = new ArrayList<Auto>();
        ArrayList<Oficina> oficinas = new ArrayList<Oficina>();

        Admin admin1 = new Admin(1234, "admin", new Date(), "1234", "vendedor1@vendedor.com", "1234");
        Vendedor vendedor1 = new Vendedor(1234, "vendedor1", new Date(), "1234", "vendedor1@vendedor.com", "1234");
        Cliente cliente1 = new Cliente(1234, "cliente1", new Date(), "1234", "cliente1@cliente.com", "1234");

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

        try {
            new CasaMatriz(personas, autos, oficinas);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
