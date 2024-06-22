package Auto;

import java.util.Date;

import enums.Color;
import enums.Marca;

public class Auto {
    private int id;
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
        return id + " - " + marca + " " + modelo + " " + color + " [" + patente + "]" + " - $" + ((int) precioPorDia)
                + " por día";
    }

    public Auto(int id, String patente, String modelo, float precioPorDia, Color color, Marca marca,
            Gasolina gasolina) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public String verAuto() {
        return this.toString();
    }

    public float getPrecioPorDia() {
        return precioPorDia;
    }

    public void verListadoAutos() {

    }

    public void entregarAuto() {

    }

    public void consumirGasolina(int dias) {
        gasolina.consumirGasolina(dias);
    }

    public void consultarGasolina() {
        gasolina.consultarGasolina(this);
    }

    public void recargarGasolina() {
        gasolina.recargarGasolina();
    }

    public boolean tieneTanqueLleno() {
        return gasolina.tieneTanqueLleno();
    }

    private void transportarAOficinaOriginal() {

    }

}
