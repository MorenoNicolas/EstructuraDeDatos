package Arboles;

public class ArbolBinario {
   private NodoArbol raiz;
   
   public boolean insertar(Object elem, Object padre, Boolean pos){

    boolean exito = false;
    if(raiz==null){
        //si el arbol esta vacio pone la raiz
        raiz = new NodoArbol(elem, null, null);

    }else{
        //si arbol no esta vacio, bucsca papa(como yo)
        NodoArbol nPadre= obtenerNodo(raiz, padre);
        //si el padre existe y el lugar no esta ocupado
        if(nPadre!=null){
            if(pos== true&&nPadre.getIzq()==null){
                nPadre.setIzq(new NodoArbol(elem, null, null));

            }else if(pos ==false && nPadre.getDer()==null){
                nPadre.setDer(new NodoArbol(elem, null, null));
            }else{
                exito=false;
            }
        }else{
            exito = false;
        }
    }
    return exito;
   }
   private NodoArbol obtenerNodo(NodoArbol n, Object buscado){
    //devuelde el nodo que contiene el parametro
    NodoArbol resultado = null;
    if(n!=null){
        if(n.getElem().equals(buscado)){
            //si es n lo devuelve
            resultado=n;
        }else{
            //primero busca izquierdo
            resultado = obtenerNodo(n.getIzq(), buscado);
            if(resultado == null){
                resultado = obtenerNodo(n.getDer(), buscado);
            }
        }   
    }
    return resultado;
   }
}
