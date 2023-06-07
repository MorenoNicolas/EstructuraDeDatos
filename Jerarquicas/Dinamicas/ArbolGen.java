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
                if (nivelRetorna == -1 && n.getHijoIzq() != null) {
                    NodoGen hijosDer = n.getHijoIzq();
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
                NodoGen hijo = n.getHijoIzq();
                while (hijo != null) {
                    listarPreAux(hijo, ls);
                    hijo = hijo.getHermanoDer();
                }
            }
        }
    }

    public Lista listarPosOrden() {
        Lista ls = new Lista();
        listarPosAux(raiz, ls);
        return ls;
    }

    private void listarPosAux(NodoGen n, Lista ls) {
        if (n != null) {
            if (n.getHijoIzq() != null) {
                NodoGen hijo = n.getHijoIzq();
                while (hijo != null) {
                    listarPosAux(hijo, ls);
                    hijo = hijo.getHermanoDer();
                }
            }
            ls.insertar(n.getElem(), ls.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        Lista ls = new Lista();
        listarNivelAux(raiz, ls);
        return ls;
    }

    private void listarNivelAux(NodoGen n, Lista ls) {
        Cola q = new Cola();
        q.poner(n);
        if (raiz != null) {
            while (!q.esVacia()) {
                NodoGen nodo = (NodoGen) q.obtenerFrente();
                q.sacar();
                ls.insertar(nodo.getElem(), ls.longitud() + 1);
                if (nodo.getHijoIzq() != null) {
                    NodoGen hijo = nodo.getHijoIzq();
                    while (hijo != null) {
                        q.poner(hijo);
                        hijo = hijo.getHermanoDer();
                    }
                }
            }
        }
    }

    public ArbolGen clonar() {
        ArbolGen arbolito = new ArbolGen();
        if (!esVacio()) {
            arbolito.raiz = new NodoGen(null);
            cloneAux(this.raiz, arbolito.raiz);
        }
        return arbolito;
    }

    private void cloneAux(NodoGen nodo, NodoGen nodoClon) {
        if (nodo != null) {
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

    public Lista ancestros(Object elem) {
        Lista ls = new Lista();
        ancestrosAux(raiz, elem, ls);
        return ls;
    }

    private boolean ancestrosAux(NodoGen n, Object elem, Lista ls) {
        boolean retorno = false;
        if (n != null && !retorno) {
            if (n.getElem().equals(elem)) {
                retorno = true;
            } else {
                if (n.getHijoIzq() != null) {
                    NodoGen hijo = n.getHijoIzq();
                    while (hijo != null && !retorno) {
                        retorno = ancestrosAux(hijo, elem, ls);
                        hijo = hijo.getHermanoDer();
                    }
                    if (retorno) {
                        ls.insertar(n.getElem(), 1);
                    }
                }
            }
        }
        return retorno;
    }

    public boolean sonFrontera(Lista unaLista) {
        boolean esta = true;
        if (!esVacio()) {
            esta = fronteraAux(raiz, unaLista);
        }
        return esta;
    }

    private boolean fronteraAux(NodoGen n, Lista ls) {
        boolean retorno = true;
        if (n != null && retorno) {
            if (n.getHijoIzq() != null) {
                NodoGen hijo = n.getHijoIzq();
                while (hijo != null && retorno) {
                    retorno = fronteraAux(hijo, ls);
                    hijo = hijo.getHermanoDer();
                }
            } else {
                if (ls.localizar(n.getElem()) <= 0) {
                    retorno = false;
                }
            }
        }
        return retorno;

    }

    // ANDA
    public Lista ancestros2(Object elemento) {
        Lista listaAncestros = new Lista();
        if (this.raiz != null) {
            ancestrosAux2(this.raiz, listaAncestros, elemento);
        }
        return listaAncestros;
    }

    private boolean ancestrosAux2(NodoGen nodo, Lista lista, Object elemento) {
        boolean encontrado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elemento)) {
                encontrado = true;
            }
            if (!encontrado) {
                encontrado = ancestrosAux2(nodo.getHijoIzq(), lista, elemento);
            }
            if (!encontrado) {
                NodoGen aux = nodo.getHijoIzq();
                while (aux != null && !encontrado) {
                    encontrado = ancestrosAux2(aux, lista, elemento);
                    aux = aux.getHermanoDer();
                }
            }
            if (encontrado && (!nodo.getElem().equals(elemento))) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            }
        }
        return encontrado;
    }

    // public boolean verificarCamino(Lista ls) {
    // boolean retorno = false;
    // if (raiz != null) {
    // retorno = caminoAux(ls, raiz, 1);
    // }
    // return retorno;
    // }

    // private boolean caminoAux(Lista ls, NodoGen n, int i) {
    // boolean retorno = false;
    // if (n != null && i <= ls.longitud()) {
    // Object elemNodo = n.getElem();
    // Object elemLista = ls.recuperar(i);
    // if (elemNodo.equals(elemLista)) {
    // if (i == ls.longitud()) {
    // retorno = true;
    // }
    // if (n.getHijoIzq() != null && !retorno) {
    // i++;
    // NodoGen hijo = n.getHijoIzq();
    // while (hijo != null && !retorno) {
    // retorno = caminoAux(ls, n, i);
    // hijo = hijo.getHermanoDer();
    // }
    // }
    // }
    // }
    // return retorno;
    // }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista ls = new Lista();
        int nivel = 0;
        if (raiz != null) {
            entreNivelesAux(raiz, niv1, niv2, nivel, ls);
        }
        return ls;
    }

    private void entreNivelesAux(NodoGen n, int niv1, int niv2, int nivel, Lista ls) {

        if (n != null && nivel <= niv2) {
            if (n.getHijoIzq() != null) {
                entreNivelesAux(n.getHijoIzq(), niv1, niv2, nivel + 1, ls);
            }
            if (nivel >= niv1) {
                ls.insertar(n.getElem(), ls.longitud() + 1);
            }
            if (n.getHijoIzq() != null) {
                NodoGen hijo = n.getHijoIzq().getHermanoDer();
                while (hijo != null) {
                    System.out.println(nivel);
                    entreNivelesAux(hijo, niv1, niv2, nivel + 1, ls);
                    hijo = hijo.getHermanoDer();
                }
            }
        }
    }

    public void eliminar(Object elem) {
        if (raiz != null) {
            if (raiz.getElem() == elem) {
                raiz = null;
            } else {
                eliminarConDescendientesAux(raiz, elem);
            }
        }
    }

    private boolean eliminarConDescendientesAux(NodoGen nodo, Object obj) {
        boolean esAnterior = false;
        if (nodo != null) {
            int caso = -1; // caso 0 hijIzq , caso 1 her
            if (nodo.getElem().equals(obj)) {
                esAnterior = true;
            } else {
                esAnterior = eliminarConDescendientesAux(nodo.getHijoIzq(), obj);
                if (esAnterior) {
                    caso = 0;
                } else {
                    esAnterior = eliminarConDescendientesAux(nodo.getHermanoDer(), obj);
                    if (esAnterior) {
                        caso = 1;
                    }
                }
                if (esAnterior) {
                    switch (caso) {
                        case 0:
                            nodo.setHijoIzq(nodo.getHijoIzq().getHermanoDer());
                            break;
                        case 1:
                            nodo.setHermanoDer(nodo.getHermanoDer().getHermanoDer());
                            break;
                    }
                    esAnterior = false;
                }
            }
        }
        return esAnterior;
    }

    public Lista listarHastaNivel(int niv1) {
        Lista ls = new Lista();
        int nivel = 0;
        if (raiz != null) {
            hastaAux(raiz, niv1, nivel, ls);
        }
        return ls;
    }

    private void hastaAux(NodoGen n, int niv1, int niv, Lista ls) {
        if (n != null && niv <= niv1) {
            if (n.getHijoIzq() != null) {
                hastaAux(n.getHijoIzq(), niv1, niv + 1, ls);
            }
            ls.insertar(n.getElem(), ls.longitud() + 1);

            if (n.getHijoIzq() != null) {
                NodoGen hijo = n.getHijoIzq().getHermanoDer();
                while (hijo != null) {
                    hastaAux(hijo, niv1, niv + 1, ls);
                    hijo = hijo.getHermanoDer();
                }
            }
        }
    }

    public boolean verificarCamino(Lista ls) {
        boolean retorno = false;
        if (raiz != null) {
            retorno = caminoAux(raiz, ls, 1);
        }
        return retorno;
    }

    private boolean caminoAux(NodoGen n, Lista ls, int i) {
        boolean retorno=false;
        Object nodo = n.getElem(), lista = ls.recuperar(i);
        if (n != null) {
            System.out.println(nodo);
            if (nodo == lista && i <= ls.longitud()) {
                if (n.getHijoIzq() == null && i == ls.longitud()) {
                    retorno = true;
                }
                if (n.getHijoIzq() != null) {
                    NodoGen hijo = n.getHijoIzq();
                    while (hijo != null&&!retorno) {
                        retorno = caminoAux(hijo, ls, i + 1);
                        hijo = hijo.getHermanoDer();
                    }
                }
            }
        }
        return retorno;
    }
    public void insertarEnPos(Object elem, Object padre, int pos){
        
        if(raiz!=null&&pos>=0){
            NodoGen nodoP = obtenerNodo(raiz, padre);
            System.out.println(obtenerNodo(raiz, padre).getElem());
        }

    }
    private NodoGen buscarNodo(NodoGen n, Object padre){
        NodoGen retorno = new NodoGen(null);
        if(n!=null){
            if(n.getElem()==padre){
                retorno = n;
            }else{
                if(n.getHijoIzq()!=null){
                    NodoGen hijo = n.getHijoIzq();
                    while(hijo!=null){
                        if(hijo.getElem()==padre){
                            retorno = hijo;
                        }else{
                            retorno = buscarNodo(hijo, padre);
                        }
                            hijo = hijo.getHermanoDer();
                       
                    }
                }
            }
        }
        return retorno;
    }

}
