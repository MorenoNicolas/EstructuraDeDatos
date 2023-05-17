package TestED;

import Dinamicas.ArbolGen;

public class testEjerciciosArbol {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();

        arbol.insertar(1, null);
        arbol.insertar(2, 1);
        arbol.insertar(3, 1);
        arbol.insertar(4, 2);
        arbol.insertar(5, 3);
        arbol.insertar(6, 3);

        System.out.println(arbol.clonar());
    }
}
