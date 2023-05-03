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

        System.out.println(arbol.toString());
        arbol.compHijos();
        System.out.println(arbol.toString());


        
    }
}
