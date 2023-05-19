package TestED;

import Dinamicas.ArbolGen;
import Dinamicas.Lista;

public class testEjerciciosArbol {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        Lista lis = new Lista();

        //lis.insertar('A', 1);
        
        lis.insertar('H', 1);
        lis.insertar('F', 2);
        lis.insertar('E', 3);
        lis.insertar('G', 4);
        lis.insertar('C', 3);
        lis.insertar('A', 4);

        

        arbol.insertar('A', null);
        arbol.insertar('B', 'A');
        arbol.insertar('C', 'A');
        arbol.insertar('D', 'A');
        arbol.insertar('E', 'B');
        arbol.insertar('F', 'B');
        arbol.insertar('G', 'B');
        arbol.insertar('H', 'D');

        System.out.println(arbol.sonFrontera(lis));
        
        //System.out.println(lis.localizar('F'));
    }
}
