package conjuntista;

public class ABB {
    NodoABB raiz;

    public ABB(){
        raiz = null;
    }
    public boolean pertenece(Comparable elem){
        boolean esta =false;
        if(raiz!=null){
            esta = perteneceAux(elem, raiz);
        }
        return esta;
    }
    public boolean perteneceAux(Comparable elem, NodoABB n){
        boolean retorno = false;
        if(n!=null){
            if(n.getElem().compareTo(elem)==0){
                retorno = true;
            }else{
                if(n.getElem().compareTo(elem)>0){
                    retorno = perteneceAux(elem, n.getIzq());
                }else{
                    retorno = perteneceAux(elem, n.getDer());
                }
            }
        }
        return retorno;
    }
    public boolean insertar(Comparable elem){
        boolean exito = true;
        if(raiz==null){
            raiz = new NodoABB(elem, null, null);
        }else{
            exito = insertarAux(raiz, elem);
        }
        return exito;
    }
    private boolean insertarAux(NodoABB n, Comparable elem){
        boolean exito = true;
        if(elem.compareTo(n.getElem())==0){
            exito = false;
        }else if(elem.compareTo(n.getElem())<0){
            if(n.getIzq()!=null){
                exito = insertarAux(n.getIzq(), elem);
            }else{
                n.setIzq(new NodoABB(elem, n, n));
            }
        }else if(n.getDer()!=null){
            exito = insertarAux(n.getDer(), elem);
        }else{
            n.setDer(new NodoABB(elem, n, n));
        }
        return false;
    }
}
