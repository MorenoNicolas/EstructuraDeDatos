package Estaticas;

public class Pila {
    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO=5;


    public Pila(){
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    public boolean apilar (Object elemento){
        boolean exito;
        if(this.tope<TAMANIO-1){
            this.tope++;
            this.arreglo[tope] = elemento;
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public boolean desapilar(){
        boolean exito;
        if(this.tope>=0){
            this.arreglo[tope] = null;
            this.tope--;
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }

    public Object obtenerTope(){
        Object elemento;
        if(tope>=0){
            elemento = this.arreglo[tope];
        } else {
            elemento = null;
        }
        return elemento;
    }

    public boolean esVacia(){
        boolean vacia = false;
        if(tope<0){
            vacia = true;
        }
        return vacia;
    }

    public void vaciar(){
        //this.arreglo = null;
        while(tope>-1){
            this.arreglo[tope] = null;
            tope--;
        }
    }

    public Pila clone(){
        Pila nuevaPila = new Pila();
        for (int i = 0; i <= this.tope; i++) {
            nuevaPila.arreglo[i] = this.arreglo[i];
        }
        nuevaPila.tope = this.tope;
        return nuevaPila;
    }

    public String toString(){
        String cadenaAcum="[";
        for (int i = tope; i >=0; i--) {
            cadenaAcum += this.arreglo[i].toString()+" ";
        }
        cadenaAcum = cadenaAcum.trim();
        cadenaAcum+="]";
        return cadenaAcum;
    }
}
