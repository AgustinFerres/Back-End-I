public class Diputado
    implements Gobierno{

    private Gobierno sigJerarquia;

    public Diputado() {
    }

    @Override
    public void setSiguiente(Gobierno gob) {
        this.sigJerarquia = gob;
    }

    @Override
    public void leerDocumento(Documento doc) {
        if (doc.getTipo() <= 1){
            System.out.println("Me encargo yo, (diputado), señor presidente");
        }else if (sigJerarquia != null){
            sigJerarquia.leerDocumento(doc);
        }
    }
}
