package TestED;

import Dinamicas.ArbolGen;
import Dinamicas.Lista;

public class testEjerciciosArbol {
    public static void main(String[] args) {
        ArbolGen arbol = new ArbolGen();
        Lista lis = new Lista();

        //lis.insertar('A', 1);
        //lis.insertar(20, 1);
        //lis.insertar(13, 2);
        lis.insertar(54, 3);
        lis.insertar(27, 4);
        lis.insertar(17, 5);
        // lis.insertar('C', 5);

        

        arbol.insertar(20, null);
        arbol.insertar(13, 20);
        arbol.insertar(54, 20);
        arbol.insertar(15, 13);
        arbol.insertar(12, 13);
        arbol.insertar(11, 54);
        arbol.insertar(27, 54);
        arbol.insertar(4, 54);
        arbol.insertar(17, 27);

        
        //System.out.println(arbol.verificarCamino(lis));
        
        arbol.insertarEnPos(6, 15, 0);



        
    }
}
