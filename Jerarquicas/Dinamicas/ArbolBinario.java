package Dinamicas;
import Lineales.Dinamicas.Lista;
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
    public boolean esVacio(){
        return raiz ==null;
    }
    public Object padre(Object elem){
        Object elemento = null;

        if (raiz != null) {
            if (raiz.getElem() != elem) {
                elemento = padreAux(raiz, elem);

            } 
        }
        return elemento;
    }
    private Object padreAux(NodoArbol n, Object buscado){
         Object resultado = null;
         
         if (n == null) {
            resultado = null; // Si el nodo es nulo, no hay padre
        } else if (n.getIzq() != null && n.getIzq().getElem().equals(buscado) || n.getDer() != null && n.getDer().getElem().equals(buscado)) {
            resultado = n.getElem(); // El valor buscado es el hijo izquierdo o derecho del nodo actual
        } else {
            resultado = padreAux(n.getIzq(), buscado);
            if (resultado == null) {
                resultado = padreAux(n.getDer(), buscado);
            }
        }
        return resultado;
    }
    public int altura(){
        int altura=-1;
        if(raiz!=null){
            altura = obtenerAlturaRecursivo(raiz);
        }
        return altura;
    }

    private int obtenerAlturaRecursivo(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzquierda = obtenerAlturaRecursivo(nodo.getIzq());
        int alturaDerecha = obtenerAlturaRecursivo(nodo.getDer());
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }
    public Lista listarPreorden(){
        Lista lis = new Lista();
        listarPreOrdenAux(raiz,lis);
        return lis;
    }
    private void listarPreOrdenAux(NodoArbol n, Lista lis){
        if(n!=null){
            lis.insertar(n.getElem(),lis.longitud()+1);

            listarPreOrdenAux(n.getIzq(), lis);
            listarPreOrdenAux(n.getDer(), lis);
        }
    }
    public Lista listarPosOrden(){
        Lista lis = new Lista();
        listarPosOrdenAux(raiz, lis);
        return lis;
    }
    private void listarPosOrdenAux(NodoArbol n, Lista lis){
        if(n!=null){
            listarPosOrdenAux(n.getIzq(), lis);
            listarPosOrdenAux(n.getDer(), lis);
            lis.insertar(n.getElem(),lis.longitud()+1);  
        }  
    }
    public Lista listarInOrden(){
        Lista lis = new Lista();
        listaInOrdenAux(raiz, lis);
        return lis;
    }
    private void listaInOrdenAux(NodoArbol n, Lista lis){
        if(n!=null){
            listaInOrdenAux(n.getIzq(), lis);
            lis.insertar(n.getElem(),lis.longitud()+1);
            listaInOrdenAux(n.getDer(), lis);
            
        }
    }
    public String toString() {
        String cadena = "";
        if (!this.esVacio()) {
            cadena = stringAux(this.raiz, "");
        }

        return cadena;
    }

    private String stringAux(NodoArbol nodo, String cadena) {
        String izq = " ";
        String der = " ";
        Boolean izqExis = nodo.getIzq() != null;
        Boolean derExis = nodo.getDer() != null;
        if (izqExis)
            izq = nodo.getIzq().getElem() + "";
        if (derExis)
            der = nodo.getDer().getElem() + "";

        cadena = cadena + "[" + nodo.getElem() + "]" + ":" + "[" + izq + "]" + "[" + der + "]" + "\n";

        if (izqExis)
            cadena = stringAux(nodo.getIzq(), cadena);
        if (derExis)
            cadena = stringAux(nodo.getDer(), cadena);
        return cadena;
    }
}

//         if(n.getElem().equals(buscado)||n.getElem().equals(buscado)){
//             resultado = n.getElem();
//         }else if(n!=null){
//             resultado = padreAux(n.getIzq(), buscado);
//             if(resultado ==null){
//                 resultado = padreAux(n.getDer(), buscado);
//             }
//         }
//         return resultado;
//     }
    
//  }
