package Dinamicas;
public class ArbolGen {
    NodoGen raiz;

    public ArbolGen() {
        raiz = null;
    }

    public boolean insertar(Object objNodo, Object objPadre) {
        boolean exito = false;
        if (esVacio()) {
            raiz = new NodoGen(objNodo);
            exito = true;
        } else {
            NodoGen nodo = obtenerNodo(this.raiz, objPadre);
            if (nodo != null) {
                NodoGen nodoTemp = nodo.getHijoIzq();
                if (nodoTemp == null) {
                    nodo.setHijoIzq(new NodoGen(objNodo));
                } else {
                    while (nodoTemp.getHermanoDer() != null) {
                        nodoTemp = nodoTemp.getHermanoDer();
                    }
                    nodoTemp.setHermanoDer(new NodoGen(objNodo));
                }
                exito = true;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodoIn, Object obj) {
        NodoGen nodoTemp = null;
        if (nodoIn != null) {
            if (nodoIn.getElem().equals(obj)) {
                nodoTemp = nodoIn;
            } else {
                nodoTemp = obtenerNodo(nodoIn.getHijoIzq(), obj);
                if (nodoTemp == null) {
                    nodoTemp = obtenerNodo(nodoIn.getHermanoDer(), obj);
                }
            }
        }
        return nodoTemp;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public boolean pertenece(Object element) {
        NodoGen n = obtenerNodo(raiz, element);
        return n != null;
    }

    public int altura() {
        int altura = 0;
        if (raiz != null) {
            altura = obtenerAltura(raiz);
        }
        return altura - 1;
    }

    private int obtenerAltura(NodoGen n) {
        int izq, der;
        if (n == null) {
            return 0;
        }
        izq = obtenerAltura(n.getHijoIzq());
        der = obtenerAltura(n.getHermanoDer());
        return 1 + Math.max(izq, der);
    }

    public int nivel(Object elemento) {
        int nivel = -1;
        if (raiz != null) {
            nivel = nivelAux(raiz, 0, elemento);
        }
        return nivel;
    }

    private int nivelAux(NodoGen n, int nivel, Object obj) {
        int nivelRetorna = -1;
        if (n != null) {
            if (n.getElem().equals(obj)) {
                nivelRetorna = nivel;
            } else {
                nivelRetorna = nivelAux(n.getHijoIzq(), nivel + 1, obj);
                if (nivelRetorna == -1 && n.getHijoIzq() != null) {
                    NodoGen hijosDer = n.getHijoIzq().getHermanoDer();
                    while (hijosDer != null && nivelRetorna == -1) {
                        nivelRetorna = nivelAux(hijosDer, nivel + 1, obj);
                        hijosDer = hijosDer.getHermanoDer();
                    }
                }
            }

        }
        return nivelRetorna;
    }

    public String toString() {

        return toStringAux(raiz);
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            s += n.getElem().toString() + "->";
            NodoGen nodoHijo = n.getHijoIzq();
            while (nodoHijo != null) {
                s += nodoHijo.getElem().toString() + ",";
                nodoHijo = nodoHijo.getHermanoDer();
            }
            nodoHijo = n.getHijoIzq();
            while (nodoHijo != null) {
                s += "\n" + toStringAux(nodoHijo);
                nodoHijo = nodoHijo.getHermanoDer();
            }
        }
        return s;
    }

    public Object padre(Object elemento) {
        Object padre = null;
        if (raiz != null) {
            padre = padreAux(elemento, raiz);
        }
        return padre;
    }

    public Object padreAux(Object elem, NodoGen n) {
        Object resultado = null;
        if (n != null) {
            if (n.getHijoIzq().getElem().equals(elem)) {
                resultado = n.getElem();
            } else {
                NodoGen hijo = n.getHijoIzq();
                while (hijo != null) {
                    if (hijo.getElem().equals(elem)) {
                        resultado = n.getElem();
                    } else if (hijo.getHijoIzq() != null) {
                        resultado = padreAux(elem, hijo);
                    }
                    hijo = hijo.getHermanoDer();
                }
            }
        }
        return resultado;
    }

    public Lista listarInOrden() {
        Lista ls = new Lista();
        listarInAux(raiz, ls);
        return ls;
    }

    private void listarInAux(NodoGen n, Lista ls) {
        if (n != null) {
            if (n.getHijoIzq() != null) {
                listarInAux(n.getHijoIzq(), ls);
                ls.insertar(n.getElem(), ls.longitud() + 1);
                NodoGen hijo = n.getHijoIzq().getHermanoDer();
                while (hijo != null) {
                    listarInAux(hijo, ls);
                    hijo = hijo.getHermanoDer();
                }
            }
        }
    }

    public Lista listarPreOrden() {
        Lista ls = new Lista();
        listarPreAux(raiz, ls);
        return ls;
    }

    private void listarPreAux(NodoGen n, Lista ls) {
        if (n != null) {
            ls.insertar(n.getElem(), ls.longitud() + 1);
            if (n.getHijoIzq() != null) {
                listarPreAux(n.getHijoIzq(), ls);
                NodoGen hijo = n.getHijoIzq().getHermanoDer();
                while (hijo != null) {
                    listarPreAux(hijo, ls);
                    hijo = hijo.getHermanoDer();
                }
            }
        }
    }
    public Lista listarPosOrden(){
        Lista ls = new Lista();
        listarPosAux(raiz, ls);
        return ls;
    }
    private void listarPosAux(NodoGen n, Lista ls){
        if(n!=null){
            if(n.getHijoIzq()!=null){
                listarPosAux(n.getHijoIzq(), ls);
                NodoGen hijo = n.getHijoIzq().getHermanoDer();
                while(hijo!=null){
                    listarPosAux(hijo, ls);
                    hijo = hijo.getHermanoDer();
                }
            }
            ls.insertar(n.getElem(), ls.longitud() + 1);
        }
    }
    public Lista listarPorNiveles(){
        Lista ls = new Lista();
        listarNivelAux(raiz, ls);
        return ls;
    }
    private void listarNivelAux(NodoGen n, Lista ls){
        Cola q = new Cola();
        q.poner(n);
        if(raiz!=null){
            while(!q.esVacia()){
                NodoGen nodo = (NodoGen)q.obtenerFrente();
                q.sacar();
                ls.insertar(nodo.getElem(), ls.longitud()+1);
                if(nodo.getHijoIzq()!=null){
                    NodoGen hijo = nodo.getHijoIzq();
                    while(hijo!=null){
                        q.poner(hijo);
                        hijo = hijo.getHermanoDer();
                    }
                }
            }
        }
    }

    public ArbolGen clonar(){
        ArbolGen arbolito = new ArbolGen();
        if(!esVacio()){
            arbolito.raiz = new NodoGen(null);
            cloneAux(this.raiz, arbolito.raiz);
        }
        return arbolito;
    }
    private void cloneAux(NodoGen nodo, NodoGen nodoClon){
        if(nodo!=null){
            nodoClon.setElem(nodo.getElem());
            if (nodo.getHijoIzq() != null) {
                nodoClon.setHijoIzq(new NodoGen(null));
                cloneAux(nodo.getHijoIzq(), nodoClon.getHijoIzq());
            }
            if (nodo.getHermanoDer() != null) {
                nodoClon.setHermanoDer(new NodoGen(null));
                cloneAux(nodo.getHermanoDer(), nodoClon.getHermanoDer());
            } 
        }
    }
}