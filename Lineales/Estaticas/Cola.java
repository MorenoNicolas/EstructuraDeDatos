package Estaticas;
    public class Cola{
        private int tamanio=10;
        private int frente;
        private int fin;
        private Object[] array;
        public Cola(){
            array = new Object[this.tamanio];
            frente=0;
            fin=0;
        }
    
        public boolean esVacia(){
            boolean exit=false;
            if(fin==frente){
                exit=true;
            }
            return exit;
        }
        public boolean poner(Object elemento){
            boolean exit= true;
            if(this.llena()){
              exit=false;
            }else{
                array[fin]=elemento;
                fin=(fin+1)%tamanio;
            }
            return exit;
        }
        private boolean llena(){
            return(frente==(fin+1)%tamanio);
        }
    
        public boolean sacar(){
            boolean exito=true;
            if(this.esVacia()){
                exito=true;
            }else{
                array[frente]=null;
                fin= (fin+1)%tamanio;
            }
            return exito;
        }
        public String toString(){
            String s="";
            for(int i=0;i<tamanio;i++){
                s=s+array[i];
            }
            return s;
        }
        public Object obtenerFrente(){
            return array[frente];
        }
        public Cola clone(){
            Cola colaClon=new Cola();
            if(!esVacia()){
                int i=this.frente;
                while(i!=this.fin){
                    colaClon.array[i]=this.array[i];
                    i=(i+1)%tamanio;
                }
            }
            return colaClon;
        }
    }

