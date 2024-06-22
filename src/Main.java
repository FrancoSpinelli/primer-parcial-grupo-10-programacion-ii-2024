import java.util.ArrayList;

import Auto.Auto;
import Auto.Gasolina;
import CasaMatriz.CasaMatriz;
import Interfaces.CapazDeCrear.CapacidadDeCrearCliente;
import Interfaces.CapazDeListar.CapacidadDeListarAutos;
import Interfaces.CapazDeListar.CapacidadDeListarOficinas;
import Interfaces.CapazDeListar.CapacidadDeListarPersonas;
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

    Admin admin1 = new Admin(1, 1234, "admin", LocalDate.now(), "1234", "admin@admin.com", "1234");
    personas.add(admin1);
    Vendedor vendedor1 = new Vendedor(2, 1234, "vendedor1", LocalDate.now(), "1234", "vendedor1@vendedor.com", "1234");
    personas.add(vendedor1);
    Cliente cliente1 = new Cliente(3, 1234, "cliente1", LocalDate.now(), "1234", "cliente1@cliente.com", "1234");
    personas.add(cliente1);
    Vendedor vendedor2 = new Vendedor(4, 1234, "vendedor2", LocalDate.now(), "1234", "vendedor2@vendedor.com", "1234");
    personas.add(vendedor2);

    Auto auto1 = new Auto(autos.size() + 1, "ABC123", "Corolla", 10000, Color.AZUL, Marca.CHEVROLET,
        new Gasolina(10000));
    autos.add(auto1);
    Auto auto2 = new Auto(autos.size() + 1, "DEF456", "Civic", 20000, Color.ROJO, Marca.FORD, new Gasolina(20000));
    autos.add(auto2);
    Auto auto3 = new Auto(autos.size() + 1, "GHI789", "Coupe", 30000, Color.BLANCO, Marca.BMW, new Gasolina(30000));
    autos.add(auto3);
    Auto auto4 = new Auto(autos.size() + 1, "JKL012", "Sedan", 40000, Color.NEGRO, Marca.FIAT, new Gasolina(40000));
    autos.add(auto4);

    Oficina oficina1 = new Oficina(1, "Av. Siempre Viva 123", "123456789");
    oficinas.add(oficina1);
    Oficina oficina2 = new Oficina(2, "Av. Siempre Viva 456", "987654321");
    oficinas.add(oficina2);


    CasaMatriz casaMatriz = new CasaMatriz(personas, autos, oficinas);


    casaMatriz.login();
/* 
    cliente1.crear();
    cliente1.verReservas();

    System.out.println("Soy vendedor 1");
    vendedor1.getOficina().verListadoReservasPendientes();
    System.out.println("Soy vendedor 2");
    vendedor2.getOficina().verListadoReservasPendientes();

    cliente1.pagarReserva(cliente1.getReserva(1, cliente1));
    cliente1.pagarReserva(cliente1.getReserva(1, cliente1));

    cliente1.verReservas();

    vendedor1.aceptarReserva(oficina1.getReserva(1));
    vendedor2.aceptarReserva(oficina2.getReserva(1));

    cliente1.verReservas();

    cliente1.pagarReserva(cliente1.getReserva(1, cliente1));
    cliente1.pagarReserva(cliente1.getReserva(1, cliente1)); */

  }
}
