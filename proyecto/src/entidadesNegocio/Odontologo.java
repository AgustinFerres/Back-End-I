package entidadesNegocio;

public class Odontologo extends Persona {

    private String matricula;

    public Odontologo(String nombre, String apellido, String matricula) {
        super(nombre, apellido);
        this.matricula = matricula;
    }
}
