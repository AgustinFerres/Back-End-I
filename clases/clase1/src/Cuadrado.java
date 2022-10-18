public class Cuadrado implements FiguraGeometrica{

    private Double lado;

    public Cuadrado(Double lado) {
        this.lado = lado;
    }

    @Override
    public String calcularArea() throws FiguraGeometricaException {
        if (ladoEsPositivo()) {
            Double area = lado * lado;
            return "El area del " + this.getClass().toString().replaceAll("class", "").trim() + " es de " + area;
        } else {
            throw new FiguraGeometricaException("El valor del radio o lado debe ser mayor que cero");
        }
    }

    private Boolean ladoEsPositivo(){
        return lado > 0;
    }
}
