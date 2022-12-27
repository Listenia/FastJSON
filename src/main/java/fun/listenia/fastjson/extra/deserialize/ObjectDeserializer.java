package fun.listenia.fastjson.extra.deserialize;

import fun.listenia.fastjson.ReaderJSON;
import fun.listenia.fastjson.Utils;
import fun.listenia.fastjson.extra.Type;

import java.util.List;
import java.util.Map;

public class ObjectDeserializer extends Deserializer {

    private final Map<String, Object> map;

    public ObjectDeserializer (SourceInput source) {
        super(source, Type.OBJECT);
        Utils.requireChar(source.next(), '{');
        map = ReaderJSON.readRawObject(source);
    }

    public Map<String, Object> getMap () {
        return map;
    }

    public String getString (String key) {
        return (String) map.get(key);
    }

    public Number getNumber (String key) {
        return (Number) map.get(key);
    }

    public double getDouble (String key) {
        return (double) map.get(key);
    }

    public int getInt (String key) {
        return (int) map.get(key);
    }

    public long getLong (String key) {
        return (long) map.get(key);
    }

    public boolean getBoolean (String key) {
        return (boolean) map.get(key);
    }

    public Object get (String key) {
        return map.get(key);
    }

    public Map<String, Object> getMap (String key) {
        return (Map<String, Object>) map.get(key);
    }

    public Object[] getArray (String key) {
        return (Object[]) map.get(key);
    }

    public List<Object> getList (String key) {
        return (List<Object>) map.get(key);
    }

}
