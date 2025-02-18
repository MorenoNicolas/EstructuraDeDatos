package TestED;

import conjuntista.ABB;
import conjuntista.GrafoEtiquetado;
import conjuntista.Heap;

public class testEjerciciosConjuntista {
    public static void main(String[] args) {
        GrafoEtiquetado grafo = new GrafoEtiquetado();
        grafo.insertarVertice(1);
        grafo.insertarVertice(2);
        grafo.insertarVertice(3);
        grafo.insertarVertice(4);
        grafo.insertarVertice(5);

        grafo.insertarArco(1, 2, 20);
        grafo.insertarArco(1, 3, 70);
        grafo.insertarArco(1, 4, 15);
        grafo.insertarArco(4, 2, 50);
        grafo.insertarArco(5, 2, 35);
        grafo.insertarArco(4, 5, 12);
        grafo.insertarArco(2, 3, 6);
System.out.println("GRAFO:");
System.out.println(grafo.primerCaminoDeLongitudMenorA(1, 4, 5).toString());
    }
}
