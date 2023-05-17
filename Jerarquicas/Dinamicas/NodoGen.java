package Dinamicas;

public class NodoGen {
    Object elem;
    NodoGen hijoIzq;
    NodoGen hermanoDer;

    public NodoGen(Object elemento){
        elem = elemento;
        hijoIzq =null;
        hermanoDer = null;
    }
    public Object getElem(){
        return elem;
    }
    public NodoGen getHijoIzq(){
        return hijoIzq;
    }
    public NodoGen getHermanoDer(){
        return hermanoDer;
    }
    public void setElem(Object element){
        elem=element;
    }
    public void setHijoIzq(NodoGen izq){
        hijoIzq=izq;
    }
    public void setHermanoDer(NodoGen der){
        hermanoDer=der;
    }
}
