package TestED;

import conjuntista.ABB;
import conjuntista.Heap;

public class testEjerciciosConjuntista {
    public static void main(String[] args) {
        ABB arbol = new ABB();

        arbol.insertar(13);
        // arbol.insertar(7);
         arbol.insertar(24);
         arbol.insertar(15);
        //  arbol.insertar(8);
        // arbol.insertar(1);
        // arbol.insertar(19);
        // arbol.insertar(20);
        // arbol.insertar(11);
        // arbol.insertar(3);



        System.out.println(arbol.listar());
        arbol.eliminarMinimo();
        System.out.println(arbol.listar());

    }
}
