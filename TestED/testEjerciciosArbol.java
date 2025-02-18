package TestED;

import Dinamicas.ArbolBinario;
import Dinamicas.ArbolGen;
import Dinamicas.Lista;

public class testEjerciciosArbol {
    public static void main(String[] args) {
        ArbolGen arbol2 = new ArbolGen();
        arbol2.insertar('A', null);
        arbol2.insertar('H', 'A');
        arbol2.insertar('B', 'A');
        arbol2.insertar('Z', 'A');


        arbol2.insertar('D', 'H');
        arbol2.insertar('N', 'H');
        arbol2.insertar('Q', 'H');

        arbol2.insertar('P', 'D');
        arbol2.insertar('G', 'D');

        arbol2.insertar('Y', 'N');

        arbol2.insertar('L', 'Q');

        

        arbol2.insertar('F', 'Z');
        arbol2.insertar('C', 'Z');
        arbol2.insertar('J', 'Z');

        arbol2.insertar('W', 'F');
        arbol2.insertar('O', 'F');

        arbol2.insertar('V', 'J');
        arbol2.insertar('M', 'J');

        arbol2.insertar('7', 'V');
        
        //System.out.println("es J, sobrino de H?  " + arbol2.esSobrino('J', 'H'));
        System.out.println("CAMINO A HOJA MAS CERCANA:   "+arbol2.caminoAHojaMasCercana().toString()+ " moncho culorroto");
    }
}
