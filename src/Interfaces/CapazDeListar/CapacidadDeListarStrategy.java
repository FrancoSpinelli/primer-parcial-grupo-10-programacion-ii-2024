package Interfaces.CapazDeListar;

import java.util.ArrayList;

public interface CapacidadDeListarStrategy<T> {
    ArrayList<T> listar(ArrayList<?> items);
}
