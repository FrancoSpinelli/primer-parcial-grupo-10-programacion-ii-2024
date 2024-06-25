package Auto;

import EntradaSalida.EntradaSalida;
import java.io.Serializable;

public class Gasolina implements Serializable{
    private float capacidadMaxima;
    private float cantidad;
    private float consumoDiario;

    public Gasolina(float capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.cantidad = capacidadMaxima;
        this.consumoDiario = capacidadMaxima / 5;
    }

    public void consultarGasolina(Auto auto) {
        Float porcentaje = calcularPorcentageDeTanque();
        EntradaSalida.mostrarString(
            "El auto " + auto.getId() + " tiene " + Math.round(porcentaje) + "% de gasolina.",
            true, true);
    }

    public void recargarGasolina() {
        this.cantidad = this.capacidadMaxima;
    }

    public void consumirGasolina(int dias) {
        float cosumido = this.consumoDiario * dias;
        if (this.cantidad - cosumido < 0)
            this.cantidad = 0;
        else
            this.cantidad -= cosumido;
    }

    public boolean tieneTanqueLleno() {
        return this.cantidad == this.capacidadMaxima;
    }

    private float calcularPorcentageDeTanque() {
        return this.cantidad / this.capacidadMaxima * 100;
    }
}
