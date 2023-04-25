package Dinamicas;

public class PruebaLista {
    public static void main(String[] args) {
        Lista lista1 = new Lista();
        Lista lista2 = new Lista();

        lista1.insertar(1, 1);
        lista1.insertar(2, 2);
        lista1.insertar(3, 3);
        ///////////////////////////////////
        lista2.insertar(1, 1);
        lista2.insertar(2, 2);
        lista2.insertar(3, 3);
        lista2.insertar(6, 4);

        System.out.println(concatenar(lista1, lista2).toString());
        System.out.println(concatenar(lista1, lista2).longitud());
        
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
