package principal;

import java.util.*;
import java.util.PriorityQueue;

public class Floyd {
    public static double[][] floydWarshall(Grafo grafo) {
        List<String> nodos = grafo.getNodos();
        int numNodos = nodos.size();
        double[][] distancias = new double[numNodos][numNodos];
        int[][] antecesores = new int[numNodos][numNodos];

        // Inicializar distancias y antecesores
        for (int i = 0; i < numNodos; i++) {
            for (int j = 0; j < numNodos; j++) {
                String origen = nodos.get(i);
                String destino = nodos.get(j);
                if (origen.equals(destino)) {
                    distancias[i][j] = 0.0;
                    antecesores[i][j] = -1;
                } else {
                    try {
                        distancias[i][j] = grafo.getDistancia(origen, destino);
                        antecesores[i][j] = i;
                    } catch (IllegalArgumentException e) {
                        distancias[i][j] = Double.POSITIVE_INFINITY;
                        antecesores[i][j] = -1;
                    }
                }
            }
        }

        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 0; k < numNodos; k++) {
            for (int i = 0; i < numNodos; i++) {
                for (int j = 0; j < numNodos; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        antecesores[i][j] = antecesores[k][j];
                    }
                }
            }
        }

        return distancias;
    }
}
