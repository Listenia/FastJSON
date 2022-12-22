package fun.listenia.fastjson.extra;

import fun.listenia.fastjson.JSONSerializable;
import fun.listenia.fastjson.Utils;

import java.util.List;

public class ArraySerializer extends Serializer {

    public ArraySerializer (StringBuilder sb) {
        super(sb, Type.ARRAY);
    }

    public ArraySerializer () {
        super(new StringBuilder(), Type.ARRAY);
    }

    private void addValue (Object value) {
        if (addComa)
            sb.append(',');
        addComa = true;
        Utils.writeRawValue(sb, value);
    }

    public void add (Object[] value) {
        this.addValue(value);
    }

    public void add (String value) {
        this.addValue(value);
    }

    public void add (Number value) {
        this.addValue(value);
    }

    public void add (boolean value) {
        this.addValue(value);
    }

    public void add (JSONSerializable json) {
        this.addValue(json);
    }

    public void add (Serializer json) {
        this.addValue(json);
    }

    public void add (List<Object> value) {
        this.addValue(value);
    }

}
