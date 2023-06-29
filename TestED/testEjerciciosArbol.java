package TestED;

import Dinamicas.ArbolBinario;
import Dinamicas.ArbolGen;
import Dinamicas.Lista;

public class testEjerciciosArbol {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        
        arbol.insertar(1, null, true );
        arbol.insertar(2, 1, true);
        arbol.insertar(4, 2, true);
        arbol.insertar(6, 2, false);
        arbol.insertar(6, 4, false);
        arbol.insertar(3, 1, false);
        arbol.insertar(7, 3, true);
        arbol.insertar(7, 3, false);
        arbol.insertar(9, 6, true);


    System.out.println(arbol.toString());
        System.out.println(arbol.menosCant(6, 2));
    }
}
