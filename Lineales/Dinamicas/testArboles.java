package Dinamicas;

public class testArboles {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        ArbolBinario arbol2 = new ArbolBinario();
        Lista lis = new Lista();

        arbol.insertar('a',null,null);
        arbol.insertar('b','a',true);
        arbol.insertar('f','a',false);
        arbol.insertar('c','b',true);
        arbol.insertar('d','b',false);
        arbol.insertar('x','c',true);
        arbol.insertar('w','c',false);
        arbol.insertar('z','f',true);
        arbol.insertar('n','d',false);

        arbol2.insertar('a',null,null);
        arbol2.insertar('b','a',true);
        arbol2.insertar('f','a',false);
        arbol2.insertar('c','b',true);
        arbol2.insertar('d','b',false);
        arbol2.insertar('x','c',true);
        arbol2.insertar('w','c',false);
        //arbol2.insertar('z','f',true);
        arbol2.insertar('n','d',false);



        lis.insertar('a', 1);
        lis.insertar('b', 2);
        lis.insertar('d', 3);
        //lis.insertar('w', 4);
        System.out.println();
        System.out.println(arbol.equals(arbol2));
    }
}
