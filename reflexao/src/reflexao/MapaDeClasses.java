package reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MapaDeClasses {

	private Map<String, String> mapa;

	public Class getClass(String chave) throws Exception {
		String valor = mapa.get(chave);
		if (valor != null) {
			return Class.forName(valor);
		} else {
			throw new RuntimeException("Chave inválida");
		}
	}

	public Object getObject(String classe) throws Exception {
		return getClass(classe).newInstance();
	}

	public Object getObject(String chave, Object[] params) throws NoSuchMethodException, SecurityException, Exception {
		Class<?>[] tiposConstrutor = new Class<?>[params.length];
		for (int i = 0; i < tiposConstrutor.length; i++) {
			tiposConstrutor[i] = params[i].getClass();
		}
		Constructor<?> c = getClass(chave).getConstructor(tiposConstrutor);
		return c.newInstance(params);
	}

	public static Map<String, Object> getFieldMap(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Class<?> c = obj.getClass();
		for (Field f : c.getDeclaredFields()) {
			f.setAccessible(true);
			map.put(f.getName(), f.get(obj));
		}
		return map;
	}

	public static void chamarTestes(Object obj) throws IllegalAccessException, IllegalArgumentException {

		Class<?> clazz = obj.getClass();

		for (Method m : clazz.getMethods()) {
			if (m.getName().startsWith("test") && m.getReturnType() == void.class
					&& m.getParameterTypes().length == 0) {

				try {
					m.invoke(obj);
				} catch (InvocationTargetException e) {
					e.getTargetException();
				}
			}
		}
	}

}
