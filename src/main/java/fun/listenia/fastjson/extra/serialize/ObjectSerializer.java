package fun.listenia.fastjson.extra.serialize;

import fun.listenia.fastjson.Utils;
import fun.listenia.fastjson.extra.Type;

import java.util.List;

public class ObjectSerializer extends Serializer {

    public ObjectSerializer (SourceOutput source) {
        super(source, Type.OBJECT);
    }

    private void putValue (String key, Object value) {
        if (addComa)
            source.write(',');
        addComa = true;
        Utils.writeKeyValue(source, key, value);
    }

    public void put (String key, Object[] value) {
        this.putValue(key, value);
    }

    public void put (String key, String value) {
        this.putValue(key, value);
    }

    public void put (String key, Number value) {
        this.putValue(key, value);
    }

    public void put (String key, boolean value) {
        this.putValue(key, value);
    }

    public void put (String key, JSONSerializable json) {
        this.putValue(key, json);
    }

    public void put (String key, Serializer json) {
        this.putValue(key, json);
    }

    public void put (String key, List<Object> value) {
        this.putValue(key, value);
    }



}

