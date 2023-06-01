package TestED;

import conjuntista.ABB;
import conjuntista.Heap;

public class testEjerciciosConjuntista {
    public static void main(String[] args) {
        ABB arbol = new ABB();

        arbol.insertar(10);
        arbol.insertar(12);
        arbol.insertar(6);
        arbol.insertar(2);

        arbol.insertar(5);
        arbol.insertar(19);
        arbol.insertar(20);
        arbol.insertar(90);


        System.out.println(arbol.listarRango(6,20));

    }
}
