package Dinamicas;

public class Lista {
    private Nodo cabecera;

    public Lista() {
        cabecera = null;
    }

    public boolean insertar(Object elemento, int pos) {
        boolean exito = true;
        int largo = longitud();
        if (pos < 1 || pos > largo + 1) {
            exito = false;
        } else {
            if (pos == 1) {
                cabecera = new Nodo(elemento, cabecera);
            } else {
                Nodo aux = cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(elemento, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        Nodo aux = cabecera;
        boolean exito = true;
        int i = 1;
        if (pos < 1 || pos > longitud() + 1 || this.cabecera == null) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = cabecera.getEnlace();
            } else {
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        }
        return exito;
    }

    public Object recuperar(int pos) {
        Object elemento;
        if (pos < 1 || pos > longitud()) {
            elemento = null;
        } else {
            int i = 1;
            Nodo aux = cabecera;
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            elemento = aux.getElem();
        }
        return elemento;
    }

    public int localizar(Object obj) {
        int retorna = -1;
        int i = 0;
        Nodo temp = cabecera;
        while (retorna == -1 && i < longitud()) {
            if (temp.getElem().equals(obj))
                retorna = i;

            i++;
            temp = temp.getEnlace();
        }
        return retorna + 1;
    }

    public void vaciar() {
        cabecera = null;
    }

    public boolean esVacia() {
        return cabecera == null;
    }

    public Lista clone() {
        Lista clon = new Lista();
        Nodo aux = cabecera.getEnlace();
        clon.cabecera = new Nodo(cabecera.getElem(), null);
        Nodo aux2 = clon.cabecera;
        while (aux != null) {
            aux2.setEnlace(new Nodo(aux.getElem(), null));
            aux = aux.getEnlace();
            aux2 = aux2.getEnlace();
        }
        return clon;
    }

    public int longitud() {
        int i = 0;
        if (cabecera != null) {
            i = 1;
            Nodo enlace = this.cabecera.getEnlace();
            while (enlace != null) {
                enlace = enlace.getEnlace();
                i++;
            }
        }
        return i;
    }

    public String toString() {
        String cadena = "";
        int i, largo = longitud();
        Nodo aux = this.cabecera;

        for (i = 1; i <= largo; i++) {
            if (i == 1) {
                cadena = cadena + aux.getElem().toString();
            } else {
                cadena = cadena + "," + aux.getElem().toString();
            }
            aux = aux.getEnlace();
        }
        return cadena;
    }

    public Lista obtenerMultiplos(int num) {
        Lista lis = new Lista();
        Nodo aux = cabecera;
        int i = 1, j = 1;

        while (i <= this.longitud()) {
            if (i % num == 0) {
                if (j == 1) {
                    lis.cabecera = new Nodo(aux.getElem(), cabecera);
                }
                lis.insertar(aux.getElem(), j);
                aux = aux.getEnlace();
                i++;
                j++;
            } else {
                aux = aux.getEnlace();
                i++;
            }
        }
        return lis;
    }

    public void eliminarOcurrencias(Object x) {
        Nodo aux = cabecera;
        while (cabecera.getElem().equals(x)) {
            cabecera = cabecera.getEnlace();
        }
        while (aux.getEnlace() != null) {
            if (aux.getEnlace().getElem().equals(x)) {
                aux.setEnlace(aux.getEnlace().getEnlace());

            } else {
                aux = aux.getEnlace();

            }
        }
    }

    public void cambiarPosicion(int pos1, int pos2) {
        int i = 1;
        if (pos1 > 0 && pos2 > 0 && pos1 != pos2) {
            Nodo aux = cabecera;
            Nodo Npos1 = null;
            Nodo Npos2 = null;
            Nodo anterior = null;
            Nodo anterior2 = null;
            
                while (aux != null) {// Npos1 == null && Npos2 ==null
                    if (i <= pos1-1) {
                        Npos1 = aux.getEnlace();
                        anterior = aux;
                    }
                    if (i+1 == pos2) {
                        anterior2 = aux;
                        Npos2 = aux.getEnlace();
                    }
                    aux = aux.getEnlace();
                    i++;
                }
                if(pos1==1){
                    Npos2.setEnlace(Npos1);
                    cabecera.setEnlace(Npos1.getEnlace());
                    Npos1.setEnlace(Npos2.getEnlace()); 
                }else if(pos2 == 1) {
                    Npos1.setEnlace(Npos2);
                    cabecera.setEnlace(Npos1);
                    anterior.setEnlace(Npos1.getEnlace());
                }else if (pos1<pos2){
                    anterior.setEnlace(Npos1.getEnlace());
                    Npos1.setEnlace(Npos2.getEnlace());
                    Npos2.setEnlace(Npos1); 
                }else if(pos1>pos2){
                    anterior.setEnlace(Npos1.getEnlace());
                    Npos1.setEnlace(Npos2);
                    anterior2.setEnlace(Npos1);
                }     
            }
         }
        
    

    public void cambiarPosicion2(int pos1, int pos2) {
        Nodo aux = this.cabecera;
        int i = 1;
        Object eliminado = null;
        Nodo anterior = null;
        if (pos2 >= 1 && pos2 <= longitud() && pos1 >= 1 && pos1 <= longitud()) {
            if (pos1 != pos2) {
                while (aux.getEnlace() != null) {
                    if (i == 1 && pos1 == i) {
                        this.cabecera = this.cabecera.getEnlace();
                        eliminado = aux.getElem();
                        aux = aux.getEnlace();
                    }
                    if (pos1 == i + 1) {
                        eliminado = aux.getEnlace().getElem();
                        aux.setEnlace(aux.getEnlace().getEnlace());
                    }
                    if (pos2 == i + 1) {
                        anterior = aux;
                    }
                    i++;
                    aux = aux.getEnlace();
                }
                if (pos2 == 1) {
                    this.cabecera = new Nodo(eliminado, this.cabecera);
                } else {
                    anterior.setEnlace(new Nodo(eliminado, anterior.getEnlace()));
                }

            }
        }
    }

    public void intercalar(Lista l2) {

    }

}