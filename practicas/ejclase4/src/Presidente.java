public class Presidente
    implements Gobierno{

    private Gobierno sigJerarquia;

    public Presidente() {
    }

    @Override
    public void setSiguiente(Gobierno gob) {
        sigJerarquia = gob;
    }

    @Override
    public void leerDocumento(Documento doc) {
        if (doc.getTipo() <= 3){
            System.out.println("Me encargo yo");
        }else if (sigJerarquia != null){
            sigJerarquia.leerDocumento(doc);
        }
    }
}
