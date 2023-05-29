package conjuntista;

public class NodoABB {
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(Comparable eleme, NodoABB izq, NodoABB der){
        elem= eleme;
        izquierdo=izq;
        derecho=der;
    }
    public Comparable getElem(){
        return elem;
    }
    public NodoABB getIzq(){
        return izquierdo;
    }
    public NodoABB getDer(){
        return derecho;
    }
    public void setElem(Comparable element){
        elem=element;
    }
    public void setIzq(NodoABB izq){
        izquierdo=izq;
    }
    public void setDer(NodoABB der){
        derecho=der;
    }
}
