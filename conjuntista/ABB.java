package conjuntista;

import Dinamicas.Lista;

public class ABB {
    private NodoABB raiz;

    public ABB() {
        raiz = null;
    }

    public boolean pertenece(Comparable elem) {
        boolean esta = false;
        if (raiz != null) {
            esta = perteneceAux(elem, raiz);
        }
        return esta;
    }

    private boolean perteneceAux(Comparable elem, NodoABB n) {
        boolean retorno = false;
        if (n != null) {
            if (n.getElem().compareTo(elem) == 0) {
                retorno = true;
            } else {
                if (n.getElem().compareTo(elem) > 0) {
                    retorno = perteneceAux(elem, n.getIzq());
                } else {
                    retorno = perteneceAux(elem, n.getDer());
                }
            }
        }
        return retorno;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (raiz == null) {
            raiz = new NodoABB(elem, null, null);
        } else {
            exito = insertarAux(raiz, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB n, Comparable elem) {
        boolean exito = true;
        if (elem.compareTo(n.getElem()) == 0) {
            exito = false;
        } else if (elem.compareTo(n.getElem()) < 0) {
            if (n.getIzq() != null) {
                exito = insertarAux(n.getIzq(), elem);
            } else {
                n.setIzq(new NodoABB(elem, null, null));
            }
        } else {
            if (n.getDer() != null) {
                exito = insertarAux(n.getDer(), elem);
            } else {
                n.setDer(new NodoABB(elem, null, null));
            }
        }
        return false;
    }

    public boolean eliminar(Comparable elem) {
        boolean exito = false;
        if (raiz != null) {
            exito = eliminarAux(elem, raiz, null);
        }
        return exito;
    }

    private boolean eliminarAux(Comparable ele, NodoABB n, NodoABB padre) {

        boolean encontrado = false;
        if (n != null) {
            if (encontrado == false) {
                if (n.getElem().compareTo(ele) == 0) {
                    encontrarCaso(n, padre);
                    encontrado = true;
                }
            }
            if (n.getElem().compareTo(ele) > 0) {
                encontrado = eliminarAux(ele, n.getIzq(), n);
            } else {
                encontrado = eliminarAux(ele, n.getDer(), n);
            }
        }
        return encontrado;
    }

    private void encontrarCaso(NodoABB nodoE, NodoABB nodoP) {

        if (nodoE.getDer() == null && nodoE.getIzq() == null) {
            caso1(nodoE, nodoP);
            System.out.println("caso1");
        } else if (nodoE.getDer() != null || nodoE.getIzq() != null) {
            caso2(nodoE, nodoP);
            System.out.println("caso2");
        } else {
            // encontrado = caso3(nodoE , nodoP);
        }
    }
    
    private boolean caso1(NodoABB eliminar, NodoABB padre) {
        if (padre.getDer() == eliminar) {
            padre.setDer(null);
        } else {
            padre.setIzq(null);
        }
        return true;
    }

    private boolean caso2(NodoABB eliminar, NodoABB padre) {
        if (eliminar.getElem().compareTo(padre.getElem()) > 0) {
            padre.setDer(eliminar.getDer());
        } else {
            padre.setIzq(eliminar.getIzq());
        }
        return true;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public Lista listar() {
        Lista ls = new Lista();
        if (raiz != null) {
            listarAux(ls, raiz);
        }
        return ls;
    }

    private void listarAux(Lista ls, NodoABB n) {
        if (n != null) {
            listarAux(ls, n.getIzq());
            ls.insertar(n.getElem(), ls.longitud() + 1);
            listarAux(ls, n.getDer());
        }
    }

    public Lista listarRango(Comparable min, Comparable max) {
        Lista ls = new Lista();
        if (raiz != null) {
            listarRangoAux(ls, raiz, min, max);
        }
        return ls;
    }

    private void listarRangoAux(Lista ls, NodoABB n, Comparable min, Comparable max) {
        if (n != null && n.getElem().compareTo(max) <= 0) {
            listarRangoAux(ls, n.getIzq(), min, max);
            if (n.getElem().compareTo(min) >= 0) {
                ls.insertar(n.getElem(), ls.longitud() + 1);
            }
            listarRangoAux(ls, n.getDer(), min, max);
        }
    }

    public Comparable maximoElem() {
        NodoABB aux = raiz;
        while (aux.getDer() != null) {
            aux = aux.getDer();
        }
        Comparable maximo = aux.getElem();
        return maximo;
    }

    public Comparable minimoElem() {
        NodoABB aux = raiz;
        while (aux.getIzq() != null) {
            aux = aux.getIzq();
        }
        Comparable minimo = aux.getElem();
        return minimo;
    }

    public void eliminarMinimo() {
        NodoABB padre = raiz;
        if (padre.getIzq() != null) {
            NodoABB aux = raiz.getIzq();
            while (aux.getIzq() != null) {
                aux = aux.getIzq();
            }
            if (aux.getDer() != null) {
                padre.setIzq(aux.getDer());
            } else {
                padre.setIzq(null);
            }
        } else if (padre.getDer() != null) {
            raiz = padre.getDer();
        } else {
            raiz = null;
        }
    }

    public ABB clonarParteInvertida(Comparable elem) {
        ABB arbolClon = new ABB();
        if (raiz != null) {
            NodoABB encontrado = buscarNodo(raiz, elem);
            if (encontrado != null) {
                arbolClon.raiz = new NodoABB(encontrado.getElem(), null, null);
                invertirAux(arbolClon.raiz, encontrado);
            }
        }
        return arbolClon;
    }

    private NodoABB buscarNodo(NodoABB n, Comparable elem) {
        NodoABB retorno = new NodoABB(null, null, null);
        if (n != null) {
            if (n.getElem().compareTo(elem) == 0) {
                retorno = n;
            } else {
                if (n.getElem().compareTo(elem) < 0) {
                    retorno = buscarNodo(n.getDer(), elem);
                } else {
                    retorno = buscarNodo(n.getIzq(), elem);
                }
            }
        }
        return retorno;
    }

    private void invertirAux(NodoABB clon, NodoABB n) {
        if (n != null) {
            if (n.getDer() != null) {
                clon.setIzq(new NodoABB(n.getDer().getElem(), null, null));
                invertirAux(clon.getIzq(), n.getDer());
            }
            if (n.getIzq() != null) {
                clon.setDer(new NodoABB(n.getIzq().getElem(), null, null));
                invertirAux(clon.getDer(), n.getIzq());
            }
        }
    }

    public String toString() {
        String toString = toStringPR(this.raiz);
        return toString;
    }

    private String toStringPR(NodoABB nodo) {
        String toString = "Arbol vacio";
        if (nodo != null) {
            toString = nodo.getElem().toString();
            NodoABB hijoIzq = nodo.getIzq();
            NodoABB hijoDer = nodo.getDer();
            if (hijoIzq != null) {
                toString = toString + ", H.I: " + hijoIzq.getElem().toString();

            } else {
                toString = toString + ", H.I: -";
            }
            if (hijoDer != null) {
                toString = toString + ", H.D: " + hijoDer.getElem().toString() + "\n";
            } else {
                toString = toString + ", H.D: -\n";
            }

            if (hijoIzq != null) {
                toString = toString + toStringPR(hijoIzq);
            }

            if (hijoDer != null) {
                toString = toString + toStringPR(hijoDer);
            }
        }
        return toString;
    }

    public Lista listarMayorIgual(Comparable elem) {
        Lista ls = new Lista();
        if (raiz != null) {
            listarMayorAux(raiz, elem, ls);
        }
        return ls;
    }
    private void listarMayorAux(NodoABB n, Comparable elem, Lista ls) {
        if (n != null) {
            if (n.getElem().compareTo(elem) > 0) {  
                listarMayorAux(n.getIzq(), elem, ls);
                ls.insertar(n.getElem(), ls.longitud()+1);
            }
            listarMayorAux(n.getDer(), elem, ls);               
        }
    }
    public Lista listarmayoresQue(Comparable valor, Comparable elem){
        Lista ls = new Lista();
        if(raiz!=null){
            NodoABB aux = buscarNodo(raiz, elem);
            if(aux!=null){
                listarMayorAux(aux, valor, ls);
            }
        }
        return ls;
    }
}