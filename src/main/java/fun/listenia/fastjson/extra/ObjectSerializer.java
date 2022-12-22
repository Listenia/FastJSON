package fun.listenia.fastjson.extra;

import fun.listenia.fastjson.JSONSerializable;
import fun.listenia.fastjson.Utils;

import java.util.List;

public class ObjectSerializer extends Serializer {

    public ObjectSerializer (StringBuilder sb) {
        super(sb, Type.OBJECT);
    }

    public ObjectSerializer () {
        super(new StringBuilder(), Type.OBJECT);
    }

    private void putValue (String key, Object value) {
        if (addComa)
            sb.append(',');
        addComa = true;
        Utils.writeKeyValue(sb, key, value);
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

