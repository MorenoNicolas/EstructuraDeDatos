package conjuntista;

public class Heap {
    private int tamanio = 20;
    private Comparable[] heap;
    private int ultimo;

    public Heap(){
        heap = new Comparable[tamanio];
        ultimo = 0;
    }
    public String toString(){
        int i=1;
        String salida = "";
        while(i<=ultimo){
            salida+=heap[i]+", ";
            i++;
        }
        return salida;
    }
    public boolean insertar(Comparable elemento){
        boolean exito;
        if(ultimo+1 == tamanio){
            exito = false;
        }else{
            heap[ultimo+1] = elemento;
            ultimo++;
            hacerSubir(ultimo);
            exito = true;
        }
        return exito;
    }

    private void hacerSubir(int pos){
        int posPadre;
        Comparable aux = heap[pos];
        boolean salir = false;

        while(!salir){
            posPadre = pos / 2;
            if(posPadre >= 1){
                if(this.heap[posPadre].compareTo(aux)>0){
                    heap[pos] = heap[posPadre];
                    heap[posPadre]= aux;
                    pos = posPadre;
                }else{
                    salir = true;
                }
            }else{
                salir = true;
            }
        }
    }

    public boolean eliminarCima(){
        boolean exito;
        if(ultimo ==0){
            exito = false;
        }else{
            heap[1]=heap[ultimo];
            ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }
    private void hacerBajar(int pos){
        int posH;
        Comparable temp = heap[pos];
        boolean salir = false;

        while(!salir){
            posH = pos*2;
            if(posH<=ultimo){
                if(posH<ultimo){
                    if(heap[posH+1].compareTo(heap[posH])<0){
                        posH++;
                    }
                }
                if(heap[posH].compareTo(temp)<0){
                    heap[pos] = heap[posH];
                    heap[posH]= temp;
                    pos = posH;
                }else{
                    salir = true;
                }
            }else{
                salir = true;
            }
        }
    }
    public Object recuperarCima(){
        Object retorno=null;
        if(ultimo != 0){
            retorno =heap[1];
        }
        return retorno;
    }
}
