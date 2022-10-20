public class Usuario {

    private Integer id;
    private String tipo;
    private String cancion;

    public Usuario(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public void solicitarCancion(String nombre){
        cancion = nombre;
    }

    public String getCancion() {
        return cancion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
