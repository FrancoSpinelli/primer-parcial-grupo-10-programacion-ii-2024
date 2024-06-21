package Auto;
import java.util.Date;

import enums.Color;
import enums.Marca;

public class Auto {

    private String patente;
    private String modelo;
    private boolean disponibilidad;
    private float precioPorDia;
    private String oficinaActual;
    private Gasolina gasolina;
    private Color color;
    private Marca marca;

    @Override
    public String toString() {
        return "Auto " + patente + " (" + marca + " " + modelo + ")";
    }

    public Auto(String patente, String modelo, float precioPorDia, Color color, Marca marca, Gasolina gasolina) {
        this.patente = patente;
        this.modelo = modelo;
        this.precioPorDia = precioPorDia;
        this.color = color;
        this.marca = marca;
    }

    public boolean validarDisponibilidad(Date fechaInicio, Date fechaFin) {

        return true;
    }

    public float calcularPrecioTotal(int cantidadDias) {

        return 0;
    }

    public void verListadoAutos() {

    }

    public void entregarAuto() {

    }

    private void validadorTanqueLleno() {
        
    }   

    private void transportarAOficinaOriginal() {

    }

}
