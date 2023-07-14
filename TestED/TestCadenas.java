package TestED;

import Dinamicas.Cola;
import Dinamicas.Lista;
import Dinamicas.Pila;

public class TestCadenas {
    public static void main(String[] args) {
        
        Pila p1 = new Pila();
        Pila p2 = new Pila();

        p1.apilar(1);
        p1.apilar(2);
        p1.apilar(3);
        p1.apilar(4);

        p2.apilar(1);
        p2.apilar(2);
        p2.apilar(4);

        System.out.println(p1.equals(p2));
    }

    public static Cola generar(Cola c1) {
        Cola clon = c1.clone();
        Cola clon2 = c1.clone();
        Cola retornada = new Cola();
        Pila unapila = new Pila();

        while (!clon.esVacia()) {
            while (!clon.esVacia() && !clon.obtenerFrente().equals('#')) {
                unapila.apilar(clon.obtenerFrente());
                retornada.poner(clon.obtenerFrente());
                clon.sacar();
                // System.out.println(retornada.toString());
            }
            while (!unapila.esVacia()) {
                retornada.poner(unapila.obtenerTope());
                unapila.desapilar();

            }
            while (!clon2.esVacia() && !clon2.obtenerFrente().equals('#')) {
                retornada.poner(clon2.obtenerFrente());
                clon2.sacar();
            }
            if (!clon.esVacia() && !clon2.esVacia()) {
                clon.sacar();
                clon2.sacar();
                retornada.poner('#');
            }
        }
        return retornada;
    }
    public static boolean verificarBalanceo(Cola q){
        boolean resultado = true;
        Lista lista = new Lista();
        Pila pila = new Pila();
        int i=1;
        char elem;
        while (!q.esVacia()) {
            char aux = (char) q.obtenerFrente();
            if(aux =='{' || aux =='}' || aux =='[' || aux ==']' || aux =='(' || aux ==')'){
                lista.insertar(q.obtenerFrente(),i);
                pila.apilar(q.obtenerFrente());
                i++;
            }
            q.sacar();
        }
        
        i=1;
        while(!pila.esVacia() && resultado){
            elem = (char) lista.recuperar(i);
            switch(elem){
                case '{':resultado=(char)pila.obtenerTope()=='}';break;
                case '[':resultado=(char)pila.obtenerTope()==']';break;
                case '(':resultado=(char)pila.obtenerTope()==')';break;
            }
      
            pila.desapilar();
            i++;
        }
        return resultado;
    }

    public static int cuentaSecuencias(Cola secu){
        int retorno = 0;
        Pila p = new Pila();
        Cola q = secu.clone();
        Cola q2 = new Cola();

        while(!q.esVacia()){
            boolean capicua =true;
            while(!q.esVacia()&&!q.obtenerFrente().equals('$')){
                q2.poner(q.obtenerFrente());
                p.apilar(q.obtenerFrente());
                q.sacar();
            }
            while(capicua&&!p.esVacia()){
                if(q2.obtenerFrente()==p.obtenerTope()){
                    q2.sacar();
                    p.desapilar();
                }else{
                    capicua =false;
                }
            }
            if(capicua==true){
                retorno++;
            }
            q.sacar();
            q2.vaciar();
            p.vaciar();
        }
        return retorno;
    }
}
    
  

// public static Lista concatenar(Lista lista1, Lista lista2){
// int j=1;
// int longi1 = lista1.longitud(), longi2 = lista2.longitud();
// Object aux;
// Lista concatenada = lista1.clone();
// while(j<=(longi2)){
// aux = lista2.recuperar(j);
// concatenada.insertar(aux, longi1+j);
// j++;
// }
// return concatenada;
// }
// public static boolean comprobar(Lista lista1, Lista lista2){

// return false;
// }
