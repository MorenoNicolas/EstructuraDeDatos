package Dinamicas;

public class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(Object eleme, NodoArbol izq, NodoArbol der){
        elem= eleme;
        izquierdo=izq;
        derecho=der;
    }
    public Object getElem(){
        return elem;
    }
    public NodoArbol getIzq(){
        return izquierdo;
    }
    public NodoArbol getDer(){
        return derecho;
    }
    public void setElem(Object element){
        elem=element;
    }
    public void setIzq(NodoArbol izq){
        izquierdo=izq;
    }
    public void setDer(NodoArbol der){
        derecho=der;
    }
}
