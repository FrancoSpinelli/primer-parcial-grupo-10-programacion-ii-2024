package Interfaces;
import java.util.List;

public interface ListadorStrategy<T> {
    List<T> listar(List<?> items);
}
