package Dinamicas;

public class testArboles {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();

        arbol.insertar('a',null,null);
        arbol.insertar('b','a',true);
        arbol.insertar('f','a',false);
        arbol.insertar('c','b',true);
        arbol.insertar('d','b',false);
        arbol.insertar('x','c',true);
        arbol.insertar('w','c',false);
        arbol.insertar('z','f',true);
        ;



        //arbol.insertar('y','c',false);
        //System.out.println(arbol.padre('f'));
        System.out.println(arbol.listarPreorden().toString());
        System.out.println(arbol.listarPosOrden().toString());
        System.out.println(arbol.listarInOrden().toString());
        System.out.println(arbol.toString());
    }
}
