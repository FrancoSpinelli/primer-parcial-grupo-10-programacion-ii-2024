package Auto;

import java.io.Serializable;
import java.util.Date;

import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Oficina.Oficina;
import enums.Color;
import enums.Marca;

public class Auto implements Serializable {
    private int id;
    private String patente;
    private String modelo;
    private float precioPorDia;
    private Oficina oficinaOriginal;
    private Oficina oficinaActual;
    private Gasolina gasolina;
    private Color color;
    private Marca marca;

    @Override
    public String toString() {
        return id + " - " + marca + " " + modelo + " " + color + " [" + patente + "]" + " - $" + ((int) precioPorDia)
                + " por día - "
                + (oficinaOriginal != null ? "Oficina #" + String.valueOf(oficinaOriginal.getId())
                        : "Sin oficina asignada");
    }

    public String toString(boolean precio) {
        return id + " - " + marca + " " + modelo + " " + color + " [" + patente + "] -  "
                + oficinaOriginal != null ? "Oficina #" + String.valueOf(oficinaOriginal.getId())
                        : "Sin oficina asignada";
    }

    public Auto(String patente, String modelo, float precioPorDia, Color color, Marca marca,
            Gasolina gasolina) {
        this.id = CasaMatriz.generarIdAuto();
        this.patente = patente;
        this.modelo = modelo;
        this.precioPorDia = precioPorDia;
        this.color = color;
        this.marca = marca;
        this.gasolina = gasolina;
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

    public void setOficinaActual(Oficina oficina) {

        if (oficina == null) {
            EntradaSalida.mostrarString("Oficina inválida");
            return;
        }

        if (oficina == oficinaActual) {
            EntradaSalida.mostrarString("El auto ya está en la oficina " + oficina.toString());
            return;
        }

        this.oficinaActual = oficina;
        EntradaSalida
                .mostrarString(
                        "El auto  " + verAuto() + " ahora se encuentra en la oficina #" + oficina.getId());
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

    public boolean estaEnOficinaOriginal() {
        return oficinaActual == oficinaOriginal;
    }

    /*private void transportarAOficinaOriginal() {
        if (oficinaActual == oficinaOriginal) {
            EntradaSalida.mostrarString("El auto ya está en la oficina original");
            return;
        }
        this.oficinaActual = oficinaOriginal;
    }*/

    public void setOficinaOriginal(Oficina oficina) {
        this.oficinaOriginal = oficina;
        this.oficinaActual = oficina;
        EntradaSalida.mostrarString("El auto " + this.toString() + " fue asignado a la oficina " + oficina.toString());
    }

    public Oficina getOficinaOriginal() {
        return oficinaOriginal;
    }

    public String getPatente() {
        return patente;
    }

}
