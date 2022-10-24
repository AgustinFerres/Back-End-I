public class Arbol {

    private Integer alto;
    private Integer ancho;
    private String color;

    public Arbol(Integer alto, Integer ancho, String color) {
        this.alto = alto;
        this.ancho = ancho;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Arbol{" +
                "alto=" + alto +
                ", ancho=" + ancho +
                ", color='" + color + '\'' +
                '}';
    }
}
