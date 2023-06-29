package Dinamicas;

public class Test {
    public static void main(String[] args) {
        Lista au = new Lista();
        Lista aa = new Lista();

        au.insertar(5, 1);
        au.insertar(4, 1);
        au.insertar(3, 1);
        au.insertar(2, 1);
        au.insertar(1, 1);

        aa.insertar(5, 1);
        aa.insertar(4, 1);
        aa.insertar(3, 1);
        aa.insertar(2, 1);
        aa.insertar(1, 1);

        System.out.println(au.toString());
        au.intercalar(aa);
        System.out.println(au.toString());

    }
}
