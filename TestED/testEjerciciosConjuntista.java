package TestED;

import conjuntista.Heap;

public class testEjerciciosConjuntista {
    public static void main(String[] args) {
        Heap arbol = new Heap();

        arbol.insertar(10);
        arbol.insertar(12);
        arbol.insertar(15);
        arbol.insertar(21);
        arbol.insertar(45);
        arbol.insertar(19);
        arbol.insertar(20);

        System.out.println(arbol.toString());

        arbol.eliminarCima();
        System.out.println(arbol.toString());
    }
}
