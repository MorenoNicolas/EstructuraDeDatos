package conjuntista;

import Dinamicas.Lista;

public class ArbolAVL {
    NodoAVL raiz;
    
    public ArbolAVL()
    {
        raiz = null;
    }
    
    public boolean insertar(Comparable obj)
    {
        boolean success = true;
        if(raiz==null)
            raiz = new NodoAVL(obj, null, null);
        else {
            success = insertar(obj, raiz, null, 'U');
        }
        return success;
    }
    
    private boolean insertar(Comparable obj, NodoAVL nodo, NodoAVL padre, char wichChild)
    {
        boolean success = true;
        int comparacion = obj.compareTo(nodo.getElem());
        if(comparacion > 0)
        {
            if(nodo.getDer() == null)
                nodo.setDer(new NodoAVL(obj, null, null));
            else
                success = insertar(obj, nodo.getDer(), nodo, 'D');
        }
        else if(comparacion < 0)
        {
            if(nodo.getIzq() == null)
                nodo.setIzq(new NodoAVL(obj, null, null));
            else
                success = insertar(obj, nodo.getIzq(), nodo, 'I');
        }
        else
            success = false;
        
        if(success)
        {
            balancear(nodo, padre, wichChild);
            nodo.recalcularAltura();
        }

        return success;
    }
    
    public boolean eliminar(Comparable obj)
    {
        boolean success = false;
        if(raiz!=null) {
            success = eliminar(obj, raiz, null, 'U'); 
        
        }
        return success;
    }
    
    private boolean eliminar(Comparable obj, NodoAVL nodo, NodoAVL padre, char wichChild) // wichChild: I para izquierdo, D para derecho
    {
        boolean success = false;
        NodoAVL reemplazo = null;
        int comparacion = obj.compareTo(nodo.getElem());
        
        //Busca el nodo a eliminar de forma recursiva
        if(comparacion > 0 && nodo.getDer()!=null)
            success = eliminar(obj, nodo.getDer(), nodo, 'D');
        else if(comparacion < 0 && nodo.getIzq() != null) 
            success = eliminar(obj, nodo.getIzq(), nodo, 'I');
      
        //Cuando lo encuentra
        else if(comparacion == 0)
        {
            success = true; 
            //Primer caso es default
            
            //Segundo caso
            if(nodo.getIzq()!=null && nodo.getDer() == null)
                reemplazo = nodo.getIzq();

            else if(nodo.getIzq()==null && nodo.getDer() != null)
                reemplazo = nodo.getDer();

            //Tercer caso
            else if(nodo.getIzq()!=null && nodo.getDer() != null)
            {
                reemplazo = extraerMayorDelMenor(nodo.getIzq(), nodo);
                reemplazo.setIzq(nodo.getIzq());
                reemplazo.setDer(nodo.getDer());
            }

            if(reemplazo!=null) {
                reemplazo.recalcularAltura();
                nodo = reemplazo; // <--- al final se balancea nodo
            }
            //Cambia los nodos, el eliminado por su reemplazo
            switch (wichChild) 
            {
                case 'U' -> this.raiz = reemplazo;
                case 'I' -> padre.setIzq(reemplazo);
                case 'D' -> padre.setDer(reemplazo);
            }
        }
        
        if(success)
        {
            nodo.recalcularAltura();
            balancear(nodo, padre, wichChild);
            nodo.recalcularAltura();
        }
        return success;
    }
    
    private NodoAVL extraerMayorDelMenor(NodoAVL nodo, NodoAVL padre)
    {
        NodoAVL mayor;
        if(nodo.getDer()==null)
        {
            mayor = nodo;
            padre.setIzq(null);
        }
        else 
            mayor = extraerMayorDelMenorR(nodo.getDer(), nodo);
        nodo.recalcularAltura();
        return mayor;
    }
    
    private NodoAVL extraerMayorDelMenorR(NodoAVL nodo, NodoAVL padre)
    {
        NodoAVL mayor;
        if(nodo.getDer()==null) {
            mayor=nodo;
            padre.setDer(null);
        }
        else
            mayor=extraerMayorDelMenorR(nodo.getDer(), nodo);
        nodo.recalcularAltura();
        return mayor;
    }
    
    private void balancear(NodoAVL nodo, NodoAVL padre, char wichChild)
    {
        int balance;
        NodoAVL tmp = null;
        balance = obtenerBalance(nodo);
        if(balance == -2) 
        {            
            if(obtenerBalance(nodo.getDer()) == 1) {
                nodo.setDer(rotarDerecha(nodo.getDer()));
            }
            tmp = rotarIzquierda(nodo);
        }
        else if(balance == 2)
        {
            if(obtenerBalance(nodo.getIzq()) == -1) {
                nodo.setIzq(rotarIzquierda(nodo.getIzq()));
            }
            tmp = rotarDerecha(nodo);
        }
        if(tmp!=null) 
        {
            switch (wichChild) 
            {
                case 'U' -> this.raiz = tmp;
                case 'I' -> padre.setIzq(tmp);
                case 'D' -> padre.setDer(tmp);
            }
        }
    }
    
    private int obtenerBalance(NodoAVL nodo)
    {
        int balance = (nodo.getIzq()==null?-1 : nodo.getIzq().getAltura()) - (nodo.getDer()==null?-1 : nodo.getDer().getAltura());
        return balance;
    }
    
    private NodoAVL rotarIzquierda(NodoAVL r)
    {
        NodoAVL h = r.getDer();
        NodoAVL tmp = h.getIzq();
        h.setIzq(r);
        r.setDer(tmp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }
    
    private NodoAVL rotarDerecha(NodoAVL r)
    {
        NodoAVL h = r.getIzq();
        NodoAVL tmp = h.getDer();
        h.setDer(r);
        r.setIzq(tmp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }
    
    public boolean pertenece(Comparable obj)
    {
        boolean pertenece = false;
        if(raiz!=null)
            pertenece = perteneceR(obj, raiz);    
        return pertenece;
    }    
    
    private boolean perteneceR(Comparable obj, NodoAVL nodo)
    {
        boolean pertenece = false;
        int comparacion = obj.compareTo(nodo.getElem());
        
        if(comparacion == 0)
            pertenece = true;
        else if(comparacion > 0 && nodo.getDer()!=null)
            pertenece = perteneceR(obj, nodo.getDer());
        else if(comparacion < 0 && nodo.getIzq()!=null)
            pertenece = perteneceR(obj, nodo.getIzq());
 
        return pertenece;
    }
    
    public Object obtener(Comparable obj)
    {
        Object encontrado = null;
        if(raiz!=null)
            encontrado = obtenerR(obj, raiz);    
        return encontrado;
    }
    
    private Object obtenerR(Comparable obj, NodoAVL nodo)
    {
        Object encontrado = null;
        int comparacion = obj.compareTo(nodo.getElem());
        
        if(comparacion == 0)
            encontrado = nodo.getElem();
        else if(comparacion > 0 && nodo.getDer()!=null)
            encontrado = obtenerR(obj, nodo.getDer());
        else if(comparacion < 0 && nodo.getIzq()!=null)
            encontrado = obtenerR(obj, nodo.getIzq());
 
        return encontrado;
    }
    
    public Object minimoElem()
    {
        NodoAVL min = raiz;
        Object obj = null;
        
        if(min!=null)
        {       
            while(min.getIzq()!=null)
                min = min.getIzq();

            obj = min.getElem();
        } 
        return obj;
    }
    
    public Object maximoElem()
    {
        NodoAVL max = raiz;
        Object obj = null;
        
        if(max!=null)
        {       
            while(max.getDer()!=null)
                max = max.getDer();

            obj = max.getElem();
        }
        return obj;  
    }
    
    public boolean esVacio()
    {
        return raiz == null;
    }    
    
    public void vaciar()
    {
        raiz = null;
    }
    
    @Override
    public String toString() 
    {
        return raiz==null? "" : toString(raiz);
    }
    
    private String toString(NodoAVL nodo) 
    {
        String sLeft = nodo.getIzq()==null? "-" : nodo.getIzq().getElem().toString();
        String sRight = nodo.getDer()==null? "-" : nodo.getDer().getElem().toString();
        return nodo.getAltura()+" ["+nodo.getElem().toString()+"]: [ "+sLeft+" | "+sRight+" ]" + (nodo.getIzq()==null? "" : "\n" + toString(nodo.getIzq())) + (nodo.getDer()==null? "" : "\n" + toString(nodo.getDer()));
    }
    
}
