package reflexao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Reflexao {
	
	public static List<String> getAtributos(Object o, String busca){
		try {
			List<String> lista = new ArrayList<>();
			Class<?> c = o.getClass();
			for(Field f : c.getFields()) {
				Object value = f.get(o);
				if(value.equals(busca)) {
					lista.add(f.getName());
				}
			}
			return lista;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
