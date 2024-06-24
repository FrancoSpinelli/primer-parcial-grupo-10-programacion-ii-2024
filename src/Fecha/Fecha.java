package Fecha;

import java.time.LocalDate;

public class Fecha {

    private LocalDate inicio;
    private LocalDate fin;
    private int cantDias;

    @Override
    public String toString() {
        return "Del " + inicio + " al " + fin + " (" + cantDias + " días)";
    }

    public Fecha(LocalDate inicio, int cantDias) {
        this.inicio = inicio;
        this.cantDias = cantDias;
        this.fin = calcularFin();
    }

    public boolean estaEnRango(LocalDate fecha) {
        return true;
    }

    public LocalDate calcularFin() {
        return this.inicio.plusDays(this.cantDias);
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public int getCantDias() {
        return cantDias;
    }
}
