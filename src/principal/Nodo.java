package principal;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nodo {
    private final String nombre;
    private final Map<Nodo, Double> vecinos;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.vecinos = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Map<Nodo, Double> getVecinos() {
        return vecinos;
    }

    public void agregarVecino(Nodo vecino, double distancia) {
        vecinos.put(vecino, distancia);
    }

    public double getDistanciaHacia(Nodo vecino) {
        return vecinos.getOrDefault(vecino, Double.POSITIVE_INFINITY);
    }
}
