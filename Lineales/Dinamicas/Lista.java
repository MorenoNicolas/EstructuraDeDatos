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
        if (pos < 1 || pos > longitud() + 1|| this.cabecera==null) {
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
        if (pos < 1 || pos > longitud() ) {
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

    public int localizar(Object elem) {
        int posicion = -1, i = 1;
        boolean resultado = false;
        Nodo aux = cabecera;
        while (i < longitud() && !resultado && aux != null) {
            resultado = aux.getElem().equals(elem);
            if (resultado) {
                posicion = i;
            } else {
                aux = aux.getEnlace();
                i++;
            }
        }
        return posicion;
    }

    public void vaciar() {
        cabecera = null;
    }

    public boolean esVacia() {
        return cabecera == null;
    }

    public Lista clone(){
        Lista clon = new Lista();
        Nodo aux = cabecera.getEnlace();
        clon.cabecera = new Nodo(cabecera.getElem(), null);
        Nodo aux2 = clon.cabecera;
        while(aux!=null){
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
        String cadena="";
        int i, largo=longitud();
        Nodo aux=this.cabecera;
        
        for (i=1; i<=largo; i++) {
            if (i==1) {
                cadena=cadena+aux.getElem().toString();
            } else {
                cadena=cadena+","+aux.getElem().toString();
            }
            aux=aux.getEnlace();
        }
        return cadena;
    }
}
