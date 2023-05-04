package Dinamicas;

public class testArboles {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        Lista lis = new Lista();

        arbol.insertar('a',null,null);
        arbol.insertar('b','a',true);
        arbol.insertar('f','a',false);
        arbol.insertar('c','b',true);
        arbol.insertar('d','b',false);
        arbol.insertar('x','c',true);
        arbol.insertar('w','c',false);
        arbol.insertar('z','f',true);

        lis.insertar('a', 1);
        lis.insertar('b', 2);
        lis.insertar('d', 3);
        //lis.insertar('w', 4);

        System.out.println(arbol.verificarPatron(lis));
        
    }
}
