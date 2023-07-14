package TestED;

import Estaticas.Pila;

public class testPila {
    public static void main(String[] args) {
        
        Pila pila1 = new Pila();
        
        int cont=0;
        
        /*for (int j = 0; j < 5; j++) {
           pila1.apilar(j);
        }*/

        pila1.apilar(1);
        pila1.apilar(2);
        pila1.apilar(3);
        pila1.apilar(2);
        pila1.apilar(1);
        Pila pila2 = pila1.clone();
        Pila pila3 = new Pila();
        while(!pila2.esVacia()){
            pila3.apilar(pila2.obtenerTope());
            pila2.desapilar();
            cont++;
        }
        System.out.println(cont);
        System.out.println(pila1.toString());
        System.out.println(pila3.toString());
        capicua(pila1, pila3);
        
}
public static boolean capicua(Pila pila1,Pila pila3){
    boolean es=true;
    do{
        es = pila1.obtenerTope() == pila3.obtenerTope();
        System.out.println(es);
     }while(es && pila1.desapilar() && pila3.desapilar());
     return es;
 }   
}

