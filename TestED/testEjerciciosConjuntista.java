package TestED;

import conjuntista.ABB;
import conjuntista.Heap;

public class testEjerciciosConjuntista {
    public static void main(String[] args) {
        ABB arbol = new ABB();

        arbol.insertar(10);
        arbol.insertar(6);
        arbol.insertar(12);
        arbol.insertar(11);
        arbol.insertar(19);
        arbol.insertar(20);
        // arbol.insertar(19);
        // arbol.insertar(20);
        // arbol.insertar(11);
        // arbol.insertar(3);
        System.out.println(arbol.toString());
        //System.out.println(arbol.listar());
        System.out.println(arbol.clonarParteInvertida(10).listar());
    }
}
