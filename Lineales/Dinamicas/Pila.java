package Dinamicas;





public class Pila {

    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object nuevoElem) {
        Nodo nuevoNodo = new Nodo(nuevoElem, this.tope);
        this.tope = nuevoNodo;
        return true;
    }

    public boolean desapilar() {

        if (this.tope == null) {
            return false;
            
        } else {
            this.tope = this.tope.getEnlace();
            return true;
        }
    }

    public Object obtenerTope() {
        Object elementoTope;
        if (this.tope == null) {
            elementoTope = null;
        } else {
            elementoTope = this.tope.getElem();
        }
        return elementoTope;
    }

    public boolean esVacia() {
        return this.tope == null;
    }

    public void vaciar() {
        this.tope = null;
    }

    @Override
    public String toString() {
        String s = "]";
        Nodo aux = this.tope;
        if (this.tope == null) {
            s = "pila vacia";
        } else {
            while (aux != null) {
                s = aux.getElem().toString()+s;
                aux = aux.getEnlace();
                if (aux != null) {
                    s = ","+s;
                }
            }
        }
        return "[" + s;
    }

    @Override
    public Pila clone() {
        Pila clon = new Pila();
        clonar(clon, this.tope);
        return clon;
    }

    private void clonar(Pila clon, Nodo otroEnlace) {
        if (otroEnlace != null) {
            //va recursivamente hasta que llega al final del nodo( osea enlace nulo)
            clonar(clon, otroEnlace.getEnlace());
            //una vez llega al final va cambiando el tope y poniendolo como enlace, si no queda al reves la pila
            clon.tope = new Nodo(otroEnlace.getElem(), clon.tope);
        }
    }

}

