package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Grafo {
    private final Map<String, Nodo> nodos;

    public Grafo() {
        nodos = new HashMap<>();
    }

    public void agregarNodo(String nombre) {
        nodos.put(nombre, new Nodo(nombre));
    }

    public void agregarArista(String origen, String destino, double distancia) {
        Nodo nodoOrigen = nodos.get(origen);
        Nodo nodoDestino = nodos.get(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            nodoOrigen.agregarVecino(nodoDestino, distancia);
            nodoDestino.agregarVecino(nodoOrigen, distancia);
        } else {
            throw new IllegalArgumentException("El nodo origen o destino no existe.");
        }
    }

    public List<String> getNodos() {
        return new ArrayList<>(nodos.keySet());
    }

    public List<String> getVecinos(String nombre) {
        Nodo nodo = nodos.get(nombre);
        if (nodo != null) {
            List<String> vecinos = new ArrayList<>();
            for (Map.Entry<Nodo, Double> entry : nodo.getVecinos().entrySet()) {
                vecinos.add(entry.getKey().getNombre());
            }
            return vecinos;
        } else {
            throw new IllegalArgumentException("El nodo no existe.");
        }
    }

    public double getDistancia(String origen, String destino) {
        Nodo nodoOrigen = nodos.get(origen);
        Nodo nodoDestino = nodos.get(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            return nodoOrigen.getDistanciaHacia(nodoDestino);
        }

        return Double.POSITIVE_INFINITY;
    }

    public void imprimirMatrizAdyacencia() {
        List<String> nodos = getNodos();
        int n = nodos.size();

        double[][] matriz = new double[n][n];

        for (int i = 0; i < n; i++) {
            String origen = nodos.get(i);
            for (int j = 0; j < n; j++) {
                String destino = nodos.get(j);
                double distancia = getDistancia(origen, destino);
                matriz[i][j] = distancia;
            }
        }

        // Imprimir la matriz de adyacencia con las distancias
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.print("NC\t"); // NC significa "No Conexión"
                } else {
                    System.out.print(matriz[i][j] + " km\t");
                }
            }
            System.out.println();
        }
    }
}