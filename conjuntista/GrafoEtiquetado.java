package conjuntista;
import Dinamicas.Lista;
import Dinamicas.Cola;
import java.util.concurrent.TimeUnit;

public class GrafoEtiquetado {

    private NodoVert inicio;

    public GrafoEtiquetado() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object elem) {
        /*
         * Dado un elemento de TipoVertice se lo agrega a la estructura controlando
         * que no se inserten vertices repetidos. Si puede realizar la insercion
         * devuelve verdadero, en caso contrario devuelve falso
         */
        boolean exito = false;
        NodoVert aux = ubicarVertice(elem);
        if (aux == null) {
            this.inicio = new NodoVert(elem, this.inicio);
            exito = true;
        }
        return exito;
    }

    public NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getClave().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean eliminarVertice(Object elem) {
        /*
         * Dado un elemento de TipoVertice se lo quita de la estructura. Si se
         * encuentra el vertice, tambien deben eliminarse todos los arcos que lo
         * tengan como origen o destino. Si se puede realizar la eliminacion con
         * exito devuelve verdadero, en caso contrario devuelve falso
         */
        boolean exito = false;
        if (this.inicio != null) {
            if (this.inicio.getClave().equals(elem)) {
                eliminarVerticeAux(this.inicio.getPrimerAdy(), elem);
                this.inicio = this.inicio.getSigVertice();
                exito = true;
            } else {
                NodoVert aux = this.inicio;
                while (aux != null && !exito) {
                    if (aux.getSigVertice().getClave().equals(elem)) {
                        eliminarVerticeAux(aux.getSigVertice().getPrimerAdy(), elem);
                        aux.setSigVertice(aux.getSigVertice().getSigVertice());
                        exito = true;
                    } else {
                        aux = aux.getSigVertice();
                    }
                }
            }
        }
        return exito;
    }

    private void eliminarVerticeAux(NodoAdy nodo, Object elem) {
        // Modulo que elimina los arcos que tengan como destino a elem
        while (nodo != null) {
            NodoAdy aux = nodo.getVertice().getPrimerAdy();
            if (aux.getVertice().getClave().equals(elem)) {
                nodo.getVertice().setPrimerAdy(aux.getSigAdyacente());
            } else {
                boolean corte = false;
                while (aux != null && !corte) {
                    if (aux.getSigAdyacente().getVertice().getClave().equals(elem)) {
                        aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());
                        corte = true;
                    } else {
                        aux = aux.getSigAdyacente();
                    }
                }
            }
            nodo = nodo.getSigAdyacente();
        }
    }

    public boolean existeVertice(Object buscado) {
        // Dado un elemento, devuelve verdadero si esta en la estructura y falso en caso
        // contrario
        boolean resultado = false;
        if (this.inicio != null) {
            NodoVert aux = this.inicio;
            while (aux != null && !resultado) {
                if (aux.getClave().equals(buscado)) {
                    resultado = true;
                } else {
                    aux = aux.getSigVertice();
                }
            }
        }
        return resultado;
    }

    public boolean insertarArco(Object origen, Object destino, double etiqueta) {
        /*
         * Dados dos elementos de TipoVertice (origen y destino) agrega el arco
         * en la estructura, solo si ambos vertices ya existen en el grafo. Si puede
         * realizar la insercion devuelve verdadero, en caso contrario devuelve falso
         */
        boolean exito = false;
        if (this.inicio != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null && (!origen.equals(destino))) {
                origenAux.setPrimerAdy(new NodoAdy(destinoAux, origenAux.getPrimerAdy(), etiqueta));
                destinoAux.setPrimerAdy(new NodoAdy(origenAux, destinoAux.getPrimerAdy(), etiqueta));
                exito = true;
            }
        }
        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        /*
         * Dados dos elementos de TipoVertice (origen y destino) se quita de la
         * estructura el arco que une ambos vertices. Si el arco existe y se puede
         * realizar la eliminacion con éxito devuelve verdadero, en caso contrario
         * devuelve falso
         */
        boolean exito = false;
        if (this.inicio != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null && (!origen.equals(destino))) {
                // Si los vertices existen, verifico si tienen un arco y lo elimino
                boolean exitoOrigen = eliminarArcoAux(origenAux, destino);
                boolean exitoDestino = eliminarArcoAux(destinoAux, origen);
                exito = exitoOrigen && exitoDestino;
            }
        }
        return exito;
    }

    private boolean eliminarArcoAux(NodoVert origen, Object destino) {
        boolean resultado = false;
        if (origen.getPrimerAdy().getVertice().getClave().equals(destino)) {
            origen.setPrimerAdy(origen.getPrimerAdy().getSigAdyacente());
            resultado = true;
        } else {
            NodoAdy aux = origen.getPrimerAdy();
            while (aux.getSigAdyacente() != null && !resultado) {
                if (aux.getSigAdyacente().getVertice().getClave().equals(destino)) {
                    aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());
                    resultado = true;
                } else {
                    aux = aux.getSigAdyacente();
                }
            }
        }
        return resultado;
    }

    public boolean existeArco(Object origen, Object destino) {
        /*
         * Dados dos elementos de TipoVertice (origen y destino), devuelve verdadero
         * si existe un arco en la estructura que los une y falso en caso contrario
         */
        boolean resultado = false;
        if (this.inicio != null) {
            NodoVert origenAux = ubicarVertice(origen);
            if (origenAux != null) {
                NodoAdy aux = origenAux.getPrimerAdy();
                while (aux != null && !resultado) {
                    if (aux.getVertice().getClave().equals(destino)) {
                        resultado = true;
                    } else {
                        aux = aux.getSigAdyacente();
                    }
                }
            }
        }
        return resultado;
    }

    public boolean existeCamino(Object origen, Object destino) {
        boolean exito = false;
        if (!origen.equals(destino) && this.inicio != null) {
            NodoVert nOrigen = ubicarVertice(origen);
            NodoVert nDestino = ubicarVertice(destino);
            if (nOrigen != null && nDestino != null) {
                Lista visitados = new Lista();
                exito = existeCaminoAux(nOrigen, destino, visitados);
            } else {
                System.out.println("No se encontraron los nodos origen o destino.");
            }
        }
        return exito;
    }

    private boolean existeCaminoAux(NodoVert nAux, Object destino, Lista visitados) {
        boolean exito = false;
        if (nAux != null) {
            if (nAux.getClave().equals(destino)) {
                exito = true;
            } else {
                visitados.insertar(nAux.getClave(), visitados.longitud() + 1);
                NodoAdy aux = nAux.getPrimerAdy();
                while (!exito && aux != null) {
                    if (visitados.localizar(aux.getVertice().getClave()) < 0) {
                        exito = existeCaminoAux(aux.getVertice(), destino, visitados);
                    }
                    aux = aux.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    public Lista caminoMasCorto(Object origen, Object destino) {
        /*
         * Dados dos elementos de TipoVertice (origen y destino), devuelve un camino
         * (lista de vertices) que indique el camino que pasa por menos vertices que
         * permite llegar del vertice origen al vertice destino. Si hay mas de un
         * camino con igual cantidad de vertices, devuelve cualquiera de ellos. Si
         * alguno de los vertices no existe o no hay camino posible entre ellos
         * devuelve la lista vacia
         */
        Lista visitados = new Lista();
        Lista res = new Lista();
        if (this.inicio != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                res = caminoMasCortoAux(origenAux, destino, visitados, res);
            }
        }
        return res;
    }

    private Lista caminoMasCortoAux(NodoVert vert, Object destino, Lista visitados, Lista res) {
        if (vert != null) {
            if ((visitados.longitud() < res.longitud()) || res.esVacia()) {
                visitados.insertar(vert.getClave(), visitados.longitud() + 1);
                if (vert.getClave().equals(destino)) {
                    res = visitados.clone();
                    System.out.println("Llego " + res.toString());
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {

                    NodoAdy ady = vert.getPrimerAdy();
                    while (ady != null) {
                        if (visitados.localizar(ady.getVertice().getClave()) < 0) {
                            res = caminoMasCortoAux(ady.getVertice(), destino, visitados, res);
                        }
                        ady = ady.getSigAdyacente();
                    }
                }
                visitados.eliminar(visitados.longitud());// ya lo visite, lo elimino del camino
            }
        }
        return res;
    }

    public Lista caminoMasRapido(Object origen, Object destino) {
        Lista visitados = new Lista();
        Lista res = new Lista();
        double[] menosKM = new double[1];
        menosKM[0] = 0;
        if (this.inicio != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                res = caminoMasRapidoAux(origenAux, destino, 0, menosKM, visitados, res);
            }
        }
        return res;
    }

    private Lista caminoMasRapidoAux(NodoVert vert, Object destino, double kmAux, double[] menosKM, Lista visitados,
            Lista res) {
        if (vert != null) {
            double km = menosKM[0];
            if (km == 0 || km > kmAux) {
                visitados.insertar(vert.getClave(), visitados.longitud() + 1);
                if (vert.getClave().equals(destino)) {
                    menosKM[0] = kmAux;
                    res = visitados.clone();
                } else {
                    NodoAdy ady = vert.getPrimerAdy();
                    while (ady != null) {
                        if (visitados.localizar(ady.getVertice().getClave()) < 0) {
                            double aux = kmAux + ady.getEtiqueta();
                            res = caminoMasRapidoAux(ady.getVertice(), destino, aux, menosKM, visitados, res);
                        }
                        ady = ady.getSigAdyacente();
                    }
                }
                visitados.eliminar(visitados.longitud());
            }
        }
        return res;
    }

    // Metodo que busca el camino mas corto pero devuelve su kilometraje
    public double caminoMasRapidoenKM(Object origen, Object destino, int KmMax) {
        Lista visitados = new Lista();
        double res = 0;
        double[] menosKM = new double[1];
        if (this.inicio != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                res = caminoMasRapidoAuxEnKm(origenAux, destino, 0, menosKM, visitados, res, KmMax, false);
            }
        }
        return res;
    }

    private double caminoMasRapidoAuxEnKm(NodoVert vert, Object destino, double kmAux, double[] menosKM,
            Lista visitados, double res, int KmMax, boolean bucle) {
        if (vert != null && kmAux <= KmMax) {
            visitados.insertar(vert.getClave(), visitados.longitud() + 1);
            if (vert.getClave().equals(destino)) {
                double km = menosKM[0];
                if (km == 0 || km > kmAux) {
                    menosKM[0] = kmAux;
                    bucle = true;
                }
            } else {
                NodoAdy ady = vert.getPrimerAdy();
                while (ady != null&& bucle==false) {
                    if (visitados.localizar(ady.getVertice().getClave()) < 0) {
                        double aux = kmAux + ady.getEtiqueta();
                        res = caminoMasRapidoAuxEnKm(ady.getVertice(), destino, aux, menosKM, visitados, res, KmMax, bucle);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return menosKM[0];
    }

    public Lista listarTodosLosCaminos(Object origen, Object destino) {
        Lista visitados = new Lista();
        Lista caminos = new Lista();
        NodoVert origenAux = ubicarVertice(origen);

        if (origenAux != null) {
            listarCaminosAux(origenAux, destino, visitados, caminos);
        }
        return caminos;
    }

    // Método auxiliar recursivo para buscar caminos desde vertice 'vert' hasta 'destino'
    private void listarCaminosAux(NodoVert vert, Object destino, Lista visitados, Lista caminos) {
        if (vert != null) {
            // Marcar la ciudad como visitada y agregarla al camino actual
            visitados.insertar(vert.getClave(), visitados.longitud() + 1);
            // Si llegamos al destino, agregamos el camino actual a la lista de caminos
            if (vert.getClave().equals(destino)) {
                caminos.insertar(visitados.clone(), caminos.longitud() + 1);

                System.out.println(visitados.toString()+ "\n salto " );

                visitados.eliminar(visitados.longitud());
            } else {
                NodoAdy ady = vert.getPrimerAdy();
                // Continuar explorando los vecinos que no han sido visitados
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getClave()) < 0) {
                        listarCaminosAux(ady.getVertice(), destino, visitados, caminos);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
        }
    }
    public Lista listarCaminosConCiudad(Object origen, Object intermedio, Object destino) {
        Lista caminos = new Lista();
        Lista visitados = new Lista();
        NodoVert origenAux = ubicarVertice(origen);

        if (origenAux != null) {
            listarCaminosAux(origenAux, destino, intermedio, visitados, caminos, false);
        }

        return caminos;
    }

    private void listarCaminosAux(NodoVert vert, Object destino, Object intermedio, Lista visitados, Lista caminos, boolean pasoPorIntermedio) {
        if (vert != null) {
            // Marcar la ciudad como visitada y agregarla al camino actual
            visitados.insertar(vert.getClave(), visitados.longitud() + 1);

            // Si pasamos por la ciudad intermedia 'C', lo marcamos
            if (vert.getClave().equals(intermedio)) {
                pasoPorIntermedio = true;
            }

            // Si llegamos al destino y hemos pasado por 'intermedio', agregamos el camino actual a la lista de caminos
            if (vert.getClave().equals(destino) && pasoPorIntermedio) {
            } else {
                NodoAdy ady = vert.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getClave()) < 0) {
                        listarCaminosAux(ady.getVertice(), destino, intermedio, visitados, caminos, pasoPorIntermedio);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
    }

    // //Método que encuentra todos los caminos de A a B pasando por C, pero ningun nodo entre listas se repite (metodo anterior es la correccion)
    
    // public Lista caminosConIntermedio(Object origen, Object intermedio, Object destino) {
    //     Lista visitados = new Lista();
    //     Lista visitadosAnteriores = new Lista();
    //     Lista res = new Lista();
    //     Lista conjuntoCaminos = new Lista();
    //     Lista retorno = new Lista();
    //     NodoVert origenAux = ubicarVertice(origen);
    //     if (this.inicio != null) {
    //         //Busco el primer camino
    //         res = listarCaminoAux(origenAux,origen, destino,intermedio, visitados, res, visitadosAnteriores);
            
    //         if(!res.esVacia()){
    //             visitadosAnteriores = res.clone();
    //             while(!res.esVacia()){
    //                 conjuntoCaminos.insertar(res, 1);
    //                 visitados = new Lista();
    //                 res = listarCaminoAux(origenAux,origen, destino, intermedio, visitados, new Lista(), visitadosAnteriores);
    //                 for (int i = 0; i < res.longitud(); i++) {
    //                     Object elem = res.recuperar(i);
    //                     if (elem!=null&&visitadosAnteriores.localizar(elem) < 0) {
    //                         visitadosAnteriores.insertar(elem, visitadosAnteriores.longitud() + 1);
    //                     }
    //                 }
    //             }
    //             //Busco en la Lista de Listas las que tengan Intermedio
    //             for (int i = 1; i <= conjuntoCaminos.longitud(); i++) {
    //                 Lista listaInterna = (Lista) conjuntoCaminos.recuperar(i); 
    //                 // Verificamos si la lista interna contiene el valor
    //                 if (listaInterna.localizar(intermedio) >= 0) {
    //                     retorno.insertar(listaInterna, retorno.longitud() + 1); 
    //                 }
    //             }
    //         }
    //     }
    //     return retorno;
    // }

    // private Lista listarCaminoAux(NodoVert vert,Object origen, Object destino,Object medio, Lista visitados, Lista res, Lista visitadosAux) {
    //     if (vert != null) {
    //         visitados.insertar(vert.getClave(), visitados.longitud() + 1);
    //         if (vert.getClave().equals(destino)) { 
    //                 res = visitados.clone();
    //         } else {
    //             NodoAdy ady = vert.getPrimerAdy();
    //             while (ady != null) {
    //                 if ((visitados.localizar(ady.getVertice().getClave()) < 0 && visitadosAux.localizar(ady.getVertice().getClave()) < 0)||ady.getVertice().getClave().equals(destino)||ady.getVertice().getClave().equals(medio)) {
    //                     res = listarCaminoAux(ady.getVertice(),origen, destino,medio, visitados, res, visitadosAux);
    //                 }
    //                 ady = ady.getSigAdyacente();
    //             }
    //         }
    //         visitados.eliminar(visitados.longitud());
    //     }
    //     return res;
    // }

    public Lista caminoMasLargo(Object origen, Object destino) {
        /*
         * Dados dos elementos de TipoVertice (origen y destino), devuelve un camino
         * (lista de vertices) que indique el camino que pasa por más vertices (sin
         * ciclos)
         * que permite llegar del vertice origen al vertice desti.getEtno. Si hay mas de
         * un
         * camino con igual cantidad de vertices, devuelve cualquiera de ellos. Si
         * alguno de los vertices no existe o no hay camino posible entre ellos
         * devuelve la lista vacía
         */
        Lista visitados = new Lista();
        Lista res = new Lista();
        if (this.inicio != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                res = caminoMasLargoAux(origenAux, destino, visitados, res);
            }
        }
        return res;
    }

    private Lista caminoMasLargoAux(NodoVert vert, Object destino, Lista visitados, Lista res) {
        if (vert != null) {
            visitados.insertar(vert.getClave(), visitados.longitud() + 1);
            if (vert.getClave().equals(destino)) {
                if ((visitados.longitud() > res.longitud()) || res.esVacia()) {
                    res = visitados.clone();
                }
            } else {
                NodoAdy ady = vert.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getClave()) < 0) {
                        res = caminoMasLargoAux(ady.getVertice(), destino, visitados, res);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return res;
    }

    public Lista listarEnProfundidad() {
        // Devuelve una lista con los vertices del grafo visitados segun el recorrido en
        // profundidad
        Lista visitados = new Lista();
        // Define un vertice donde empezar a recorrer
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getClave()) < 0) {
                // Si el vertice todavia no se visito, avanza en profundidad
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert nodo, Lista visitados) {
        if (nodo != null) {
            // Marca al vertice como visitado
            visitados.insertar(nodo.getClave(), visitados.longitud() + 1);
            NodoAdy ady = nodo.getPrimerAdy();
            while (ady != null) {
                // Visita en profundidad los adyacentes de nodo aun no visitados
                if (visitados.localizar(ady.getVertice().getClave()) < 0) {
                    listarEnProfundidadAux(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public Lista listarEnAnchura() {
        // Devuelve una lista con los vertices del grafo visitados según el recorrido en
        // anchura
        Lista visitados = new Lista();
        NodoVert aux = this.inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getClave()) < 0) {
                listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnAnchuraAux(NodoVert verticeInicial, Lista visitados) {
        Cola cola = new Cola();
        visitados.insertar(verticeInicial.getClave(), visitados.longitud() + 1);
        cola.poner(verticeInicial);
        while (!cola.esVacia()) {
            NodoVert aux = (NodoVert) cola.obtenerFrente();
            cola.sacar();
            NodoAdy ady = aux.getPrimerAdy();
            while (ady != null) {
                if (visitados.localizar(ady.getVertice().getClave()) < 0) {
                    visitados.insertar(ady.getVertice().getClave(), visitados.longitud() + 1);
                    cola.poner(ady.getVertice());
                }
                ady = ady.getSigAdyacente();
            }
        }
    }

    public boolean esVacio() {
        return this.inicio == null;
    }

    @Override
    public GrafoEtiquetado clone() {
        /*
         * Genera y devuelve un grafo que es equivalente (igual estructura y
         * contenido de los nodos) al original
         */
        GrafoEtiquetado grafoClon = new GrafoEtiquetado();
        if (this.inicio != null) {
            grafoClon.inicio = new NodoVert(this.inicio.getClave(), null);
            clonarVertices(this.inicio, grafoClon.inicio);
            clonarAdyacentes(this.inicio, grafoClon.inicio);
        }
        return grafoClon;
    }

    private void clonarAdyacentes(NodoVert vertOriginal, NodoVert vertClon) {
        if (vertOriginal != null) {
            if (vertOriginal.getPrimerAdy() != null) {
                vertClon.setPrimerAdy(new NodoAdy(vertOriginal.getPrimerAdy().getVertice(), null,
                        vertOriginal.getPrimerAdy().getEtiqueta()));
                NodoAdy ady1 = vertOriginal.getPrimerAdy();
                NodoAdy ady2 = vertClon.getPrimerAdy();
                while (ady1.getSigAdyacente() != null) {
                    ady2.setSigAdyacente(new NodoAdy(ady1.getSigAdyacente().getVertice(), null,
                            ady1.getSigAdyacente().getEtiqueta()));
                    ady1 = ady1.getSigAdyacente();
                    ady2 = ady2.getSigAdyacente();
                }
            }
            clonarAdyacentes(vertOriginal.getSigVertice(), vertClon.getSigVertice());
        }
    }

    private void clonarVertices(NodoVert vertOriginal, NodoVert vertClon) {
        if (vertOriginal != null) {
            if (vertOriginal.getSigVertice() != null) {
                vertClon.setSigVertice(new NodoVert(vertOriginal.getSigVertice().getClave(), null));
            }
            clonarVertices(vertOriginal.getSigVertice(), vertClon.getSigVertice());
        }
    }

    @Override
    public String toString() {
        /*
         * Con fines de debugging, este metodo genera y devuelve una cadena String
         * que muestra los vertices almacenados en el grafo y que adyacentes tiene cada
         * uno de ellos
         */
        String resultado;
        if (this.inicio != null) {
            resultado = toStringAux(this.inicio);
        } else {
            resultado = "El grafo esta vacio ";
        }
        return resultado;
    }

    private String toStringAux(NodoVert vertice) {
        String cadena = "";
        if (vertice != null) {
            cadena = cadena + vertice.getClave().toString() + " | Adyacentes: ";
            NodoAdy ady = vertice.getPrimerAdy();
            while (ady != null) {
                if (ady.getSigAdyacente() != null) {
                    cadena = cadena + ady.getVertice().getClave().toString() + "(" + ady.getEtiqueta() + ")" + ", ";
                } else {
                    cadena = cadena + ady.getVertice().getClave().toString() + "(" + ady.getEtiqueta() + ")";
                }
                ady = ady.getSigAdyacente();
            }
            cadena = cadena + "\n" + toStringAux(vertice.getSigVertice());
        }
        return cadena;
    }

    public Lista primerCaminoMenorPeso(Object origen, Object destino, int pesoMin, int longMax) {
        Lista visitados = new Lista();
        Lista res = new Lista();
        if (this.inicio != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                boolean[] bucle = {false};
                res = primerCaminoMenorPesoAux(origenAux, destinoAux, pesoMin, longMax, 0, 0, visitados, bucle, res);
            }
        }
        return res;
    }
    
    private Lista primerCaminoMenorPesoAux(NodoVert vert, NodoVert destino, int pesoMin, int longMax, int pesoAcumulado, int vertices, Lista visitados, boolean[] bucle, Lista res) {
        if (vert != null && visitados.longitud() <= longMax) {
            visitados.insertar(vert.getClave(), visitados.longitud() + 1);
            
            if (vert.equals(destino)) {
                if (pesoAcumulado >= pesoMin) {
                    bucle[0] = true;
                    res = visitados.clone(); 
                }
            } else {
                NodoAdy ady = vert.getPrimerAdy();
                while (ady != null && !bucle[0]) {
                    if (visitados.localizar(ady.getVertice().getClave()) < 0) { 
                        int nuevoPeso=100;
                        res = primerCaminoMenorPesoAux(ady.getVertice(), destino, pesoMin, longMax, nuevoPeso, vertices + 1, visitados, bucle, res);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return res;
    }


    public Lista primerCaminoDeLongitudMenorA(Object origen, Object destino, int longMax) {
        Lista visitados = new Lista();
        Lista res = new Lista();
        int contador = 0;
        if (this.inicio != null) {
            NodoVert origenAux = ubicarVertice(origen);
            NodoVert destinoAux = ubicarVertice(destino);
            if (origenAux != null && destinoAux != null) {
                res = primerCaminoAux(origenAux, destinoAux,  longMax, contador, visitados, res);
            }
        }
        return res;
    }
    
    private Lista primerCaminoAux(NodoVert vert, NodoVert destino, int longMax, int cont, Lista visitados, Lista res) {
        if (vert != null && cont <= longMax) {
            
            visitados.insertar(vert.getClave(), visitados.longitud() + 1);
            cont++;  
            if (vert.equals(destino)) {
                if (res.esVacia()) { 
                    res = visitados.clone();
                }
            } else {
                NodoAdy ady = vert.getPrimerAdy();
                while (ady != null && res.esVacia()) {
                    System.out.println(ady.getVertice().getClave()); 
                    System.out.println(visitados.localizar(ady.getVertice().getClave()));
                    if (visitados.localizar(ady.getVertice().getClave()) <= 0) {  
                        res = primerCaminoAux(ady.getVertice(), destino, longMax, cont, visitados, res);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return res;
    }
}
