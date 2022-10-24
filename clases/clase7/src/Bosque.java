public class Bosque {

    public static void main(String[] args) {

        int contador = 0;
        ArbolFactory factory = ArbolFactory.getInstance();

        for (int i = 0; i < 1000000 / 2; i++) {
            try {
                factory.getArbol(ArbolFactory.FRUTALES);
                contador++;
            } catch (ArbolFactoryException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 1000000 / 2; i++) {
            try {
                factory.getArbol(ArbolFactory.ORNAMENTALES);
                contador++;
            } catch (ArbolFactoryException e) {
                e.printStackTrace();
            }
        }

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memoria usada: " + (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024) + " " + contador);
    }
}
