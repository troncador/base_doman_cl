package cl.doman.db.model.field;

import java.util.HashMap;
import java.util.Map;

import cl.doman.db.model.convert.Convertable;

public class ReverseEnumMap<V extends Enum<V> & EnumConverter<T>, T> {
	  private Map<T, V> map = new HashMap<T, V>();
	  public ReverseEnumMap(Class<V> valueType) {
	    for (V v : valueType.getEnumConstants()) {
	      map.put(v.convert(), v);
	    }
	  }

	  public V get(T num) {
	    return map.get(num);
	  }
}