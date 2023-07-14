package conjuntista;

public class NodoAVL {
    private Comparable elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;

    public NodoAVL(Comparable eleme, NodoAVL izq, NodoAVL der){
        elem= eleme;
        izquierdo=izq;
        derecho=der;
        this.altura = Math.max( izquierdo==null ? 0 : izquierdo.altura + 1, derecho==null ? 0 : derecho.altura + 1 );
    }
    public Comparable getElem(){
        return elem;
    }
    public NodoAVL getIzq(){
        return izquierdo;
    }
    public NodoAVL getDer(){
        return derecho;
    }
    public void setElem(Comparable element){
        elem=element;
    }
    public void setIzq(NodoAVL izq){
        izquierdo=izq;
    }
    public void setDer(NodoAVL der){
        derecho=der;
    }
    public int getAltura(){
        return altura;
    }
    public void recalcularAltura(){
        this.altura = Math.max(izquierdo==null ? 0 : (izquierdo.getAltura() + 1), derecho==null ? 0 : (derecho.getAltura() + 1));
    }
}
