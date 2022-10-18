public class Circulo implements FiguraGeometrica{

    private Double radio;

    public Circulo(Double radio) {
        this.radio = radio;
    }

    @Override
    public String calcularArea() throws FiguraGeometricaException{
        if (radioEsPositivo()){
            Double area = Math.pow(radio,2) * Math.PI;
            return "El area del " + this.getClass().toString().replaceAll("class", "").trim() + " es de " + area;

        }else {
            throw new FiguraGeometricaException("El valor del radio o lado debe ser mayor que cero");
        }
    }

    private Boolean radioEsPositivo(){
        return radio > 0;
    }
}
