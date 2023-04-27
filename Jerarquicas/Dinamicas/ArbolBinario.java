package Dinamicas;

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
        int alturaIzquierda, alturaDerecha;
        if (nodo == null) {
            return 0;
        }
        alturaIzquierda = obtenerAlturaRecursivo(nodo.getIzq());
        alturaDerecha = obtenerAlturaRecursivo(nodo.getDer());
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
    public Lista frontera(){
        Lista lis = new Lista();
        fronteraAux(raiz, lis);
        return lis;
    }
    private void fronteraAux(NodoArbol n, Lista lis){
        if(n!=null){
            fronteraAux(n.getIzq(), lis);
            fronteraAux(n.getDer(), lis);
            if(n.getIzq()==null&&n.getDer() ==null){
                lis.insertar(n.getElem(), lis.longitud()+1);
            }
        }
    }
    public Lista obtenerAncestros(Object m){
        boolean encontrado =false;
        Lista unaLista = new Lista();
        ancestrosAux2(raiz, m, unaLista);
        return unaLista;
    }
    private void ancestrosAux(NodoArbol n, Object elem ,Lista lis){
        if(n!=null){
            if(n.getElem().equals(elem)){
                ancestrosAux(n.getIzq(),  elem ,lis);
                ancestrosAux(n.getDer(),  elem, lis);
            }else{
                lis.insertar(n.getElem(),lis.longitud()+1);
            }
        }
    }
   
    private boolean ancestrosAux2(NodoArbol nAux, Object buscado, Lista unaLista){
        boolean encontrado = false;
        if(nAux!=null){
            String elemento = nAux.getElem().toString();
            if(elemento.equals(buscado)){
                encontrado = true;
            } else {
                encontrado = ancestrosAux2(nAux.getIzq(),buscado,unaLista);
            }
            if(!encontrado){
                encontrado = ancestrosAux2(nAux.getDer(),buscado,unaLista);
            }
            if(encontrado && (!elemento.equals(buscado)||elemento.equals(this.raiz.getElem().toString()))){
                unaLista.insertar(nAux.getElem(), unaLista.longitud()+1);
            }
        }
        return encontrado;
    }
}
// private boolean ancestrosAux3(NodoArbol n, Object elem, Lista lis) {
//     if (n == null) {
//         return false;
//     }
//     if (n.getElem().equals(elem)) {
//         return true;
//     }
//     boolean encontrado = ancestrosAux3(n.getIzq(), elem, lis);
//     if (encontrado) {
//         lis.insertar(n.getElem(), lis.longitud() + 1);
//         return true;
//     }
//     encontrado = ancestrosAux3(n.getDer(), elem, lis);
//     if (encontrado) {
//         lis.insertar(n.getElem(), lis.longitud() + 1);
//         return true;
//     }
//     return false;
// }