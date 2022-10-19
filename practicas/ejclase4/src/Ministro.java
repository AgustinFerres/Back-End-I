public class Ministro
    implements Gobierno{

    private Gobierno sigJerarquia;

    public Ministro() {
    }

    @Override
    public void setSiguiente(Gobierno gob) {
        sigJerarquia = gob;
    }

    @Override
    public void leerDocumento(Documento doc) {
        if (doc.getTipo() <= 2){
            System.out.println("Me encargo yo, (ministro), señor presidente");
        }else if (sigJerarquia != null){
            sigJerarquia.leerDocumento(doc);
        }
    }
}
