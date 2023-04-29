package Dinamicas;

public class PruebaLista {
    public static void main(String[] args) {
        Lista lista1 = new Lista();
        

        lista1.insertar('B', 1);
        lista1.insertar('B', 2);
        lista1.insertar('B', 3);
        lista1.insertar('B', 4);
        lista1.insertar('T', 5);
        lista1.insertar('B', 6);
        lista1.insertar('B', 7);
        lista1.insertar('B', 8);
        lista1.insertar('R', 9);
        ///////////////////////////////////
        lista1.eliminarOcurrencias('B');
        System.out.println(lista1.toString());
        
    }
    public static Lista concatenar(Lista lista1, Lista lista2){
        int j=1;
        int longi1 = lista1.longitud(), longi2 = lista2.longitud();
        Object aux;
        Lista concatenada = lista1.clone();
        while(j<=(longi2)){
            aux = lista2.recuperar(j);
            concatenada.insertar(aux, longi1+j);
            j++;
        }
        return concatenada;
    }
    public static boolean comprobar(Lista lista1, Lista lista2){

        return false;
    }
}
