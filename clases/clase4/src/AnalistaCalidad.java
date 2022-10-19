public abstract class AnalistaCalidad {

    private AnalistaCalidad siguienteAnalista;

    public abstract String comprobarArticulo(Articulo art);

    public AnalistaCalidad getSiguienteAnalista() {
        return siguienteAnalista;
    }

    public void setSiguienteAnalista(AnalistaCalidad siguienteAnalista) {
        this.siguienteAnalista = siguienteAnalista;
    }
}
