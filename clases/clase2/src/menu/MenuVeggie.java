package menu;

public class MenuVeggie extends Menu{

    private Integer cantidadSalsas;
    private Boolean especias;

    public MenuVeggie(Double precioBase, Integer cantidadSalsas, Boolean especias) {
        super(precioBase);
        this.cantidadSalsas = cantidadSalsas;
        this.especias = especias;
    }

    public Integer getCantidadSalsas() {
        return cantidadSalsas;
    }

    public void setCantidadSalsas(Integer cantidadSalsas) {
        this.cantidadSalsas = cantidadSalsas;
    }

    public Boolean getEspecias() {
        return especias;
    }

    public void setEspecias(Boolean especias) {
        this.especias = especias;
    }
}
