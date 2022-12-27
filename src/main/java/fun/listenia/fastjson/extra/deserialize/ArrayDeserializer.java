package fun.listenia.fastjson.extra.deserialize;

import fun.listenia.fastjson.ReaderJSON;
import fun.listenia.fastjson.Utils;
import fun.listenia.fastjson.extra.Type;

import java.util.List;
import java.util.Map;

public class ArrayDeserializer extends Deserializer {

    private final List<Object> array;

    public ArrayDeserializer (SourceInput in) {
        super(in, Type.ARRAY);
        Utils.requireChar(source.next(), '[');
        array = ReaderJSON.readRawArray(source);
    }

    public List<Object> getArray () {
        return array;
    }

    public String getString (int index) {
        return (String) array.get(index);
    }

    public Number getNumber (int index) {
        return (Number) array.get(index);
    }

    public double getDouble (int index) {
        return (double) array.get(index);
    }

    public int getInt (int index) {
        return (int) array.get(index);
    }

    public long getLong (int index) {
        return (long) array.get(index);
    }

    public boolean getBoolean (int index) {
        return (boolean) array.get(index);
    }

    public Object get (int index) {
        return array.get(index);
    }

    public Map<String, Object> getMap (int index) {
        return (Map<String, Object>) array.get(index);
    }

    public Object[] getArray (int index) {
        return (Object[]) array.get(index);
    }

}
