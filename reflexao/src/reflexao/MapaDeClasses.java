package reflexao;

import java.lang.reflect.Constructor;
import java.util.Map;

public class MapaDeClasses {
	
	private Map<String,String> mapa;

    public Class getClass(String chave) throws Exception{
        String valor = mapa.get(chave);
        if(valor != null){
            return Class.forName(valor);
        }else{
            throw new RuntimeException("Chave inválida");
        }
    }
    
    public Object getObject(String classe) throws Exception {
    	return getClass(classe).newInstance();
    }
    
    public Object getObject(String chave, Object[] params) throws NoSuchMethodException, SecurityException, Exception {
    	Class<?>[] tiposConstrutor = new Class<?>[params.length];
        for(int i=0; i<tiposConstrutor.length; i++){
            tiposConstrutor[i] = params[i].getClass();
        }
        Constructor<?> c = getClass(chave).getConstructor(tiposConstrutor); 
        return c.newInstance(params);
    }

}
