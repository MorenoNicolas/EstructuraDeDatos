package Dinamicas;

public class Test {
    public static void main(String[] args) {
        Lista au = new Lista();
        

        au.insertar(5, 1);
        au.insertar(4, 1);
        au.insertar(3, 1);
        au.insertar(2, 1);
        au.insertar(1, 1);
        au.insertar(6, 6);
        au.insertar(7, 7);

        Lista aa = au.obtenerMultiplos(3);
        //aa.insertar(4, 1);
        // aa.insertar(3, 1);
        // aa.insertar(2, 1);
        // aa.insertar(1, 1);
        // aa.insertar(6, 6);
        // aa.insertar(7, 7);

        
        System.out.println(au.toString());
        System.out.println(aa.toString());

    }
}
