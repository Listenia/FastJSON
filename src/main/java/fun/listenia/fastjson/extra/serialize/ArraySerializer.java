package fun.listenia.fastjson.extra.serialize;

import fun.listenia.fastjson.Utils;
import fun.listenia.fastjson.extra.Type;

import java.util.List;

public class ArraySerializer extends Serializer {

    public ArraySerializer (SourceOutput source) {
        super(source, Type.ARRAY);
    }

    private void addValue (Object value) {
        if (addComa)
            source.write(',');
        addComa = true;
        Utils.writeRawValue(source, value);
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
