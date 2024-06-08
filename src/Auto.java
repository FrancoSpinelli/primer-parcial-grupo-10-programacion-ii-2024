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

    public Auto(String patente, String modelo, String oficinaActual, float precioPorDia) {
        this.patente = patente;
        this.modelo = modelo;
        this.oficinaActual = oficinaActual;
        this.precioPorDia = precioPorDia;
        this.disponibilidad = true;
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

    private void transportarAOficinaOriginal() {

    }

}
