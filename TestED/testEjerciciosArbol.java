package TestED;

import Dinamicas.ArbolGen;

public class testEjerciciosArbol {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();

        arbol.insertar('A', null);
        arbol.insertar('B', 'A');
        arbol.insertar('C', 'A');
        arbol.insertar('D', 'A');
        arbol.insertar('E', 'B');
        arbol.insertar('F', 'B');
        arbol.insertar('G', 'B');
        arbol.insertar('H', 'D');

        System.out.println(arbol.nivel('H'));
        
    }
}
