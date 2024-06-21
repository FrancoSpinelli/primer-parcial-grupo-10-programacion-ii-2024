package Auto;

public class Gasolina {
    private float capacidadMaxima;
    private float cantidad;
    private float consumoDiario;

    public Gasolina(float capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.cantidad = capacidadMaxima;
    }

    public void consultarGasolina() {
        System.out.println("Cantidad de gasolina: " + this.cantidad + " litros");
    }

    public void recargarGasolina() {
        this.cantidad = this.capacidadMaxima;
    }

    public void consumirGasolina(int dias) {
        float cosumido = this.consumoDiario * dias;
        if (this.cantidad - cosumido < 0) this.cantidad = 0;
        else this.cantidad -= cosumido;
    }

    public boolean tieneTanqueLleno() {
        return this.cantidad == this.capacidadMaxima;
    }
}
