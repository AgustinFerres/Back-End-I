import java.util.HashMap;
import java.util.Map;

public class ArbolFactory {

    public static final String ORNAMENTALES = "Ornamentales";
    public static final String FRUTALES = "Frutales";
    public static final String FLORALES = "Florales";
    private static ArbolFactory instance;
    private static Map<String, Arbol> arbolMap;

    private ArbolFactory() {
        arbolMap= new HashMap<>();
    }

    public static ArbolFactory getInstance() {
        if (instance == null){
            instance = new ArbolFactory();
        }
        return instance;
    }

    public Arbol getArbol(String codigo) throws ArbolFactoryException{
        if (codigo == null){
            throw new ArbolFactoryException("el codigo no puede ser nulo");
        }
        switch (codigo){
            case ORNAMENTALES:
                if (!arbolMap.containsKey(codigo)){
                    arbolMap.put(codigo, new Arbol(200,400,"verde"));
                }
                return arbolMap.get(codigo);
            case FRUTALES:
                if (!arbolMap.containsKey(codigo)){
                    arbolMap.put(codigo, new Arbol(500,300,"rojo"));
                }
                return arbolMap.get(codigo);
            case FLORALES:
                if (!arbolMap.containsKey(codigo)){
                    arbolMap.put(codigo, new Arbol(100,200,"celeste"));
                }
                return arbolMap.get(codigo);
            default:
                throw new ArbolFactoryException(codigo + " no es un codigo valido");
        }
    }
}
