package fun.listenia.fastjson.extra;

import fun.listenia.fastjson.JSONSerializable;
import fun.listenia.fastjson.Utils;

import java.util.List;

public class Serializer {

    public static ObjectSerializer createObject () {
        return new ObjectSerializer();
    }
    public static ObjectSerializer createObject (StringBuilder sb) {
        return new ObjectSerializer(sb);
    }

    public static ArraySerializer createArray () {
        return new ArraySerializer();
    }
    public static ArraySerializer createArray (StringBuilder sb) {
        return new ArraySerializer(sb);
    }

    public static void serialize (JSONSerializable obj, Serializer serializer) {
        serializer._start();
        obj.serialize((ObjectSerializer) serializer);
        serializer._end();
    }

    protected final StringBuilder sb;
    protected boolean addComa;

    private final Type type;

    protected Serializer (StringBuilder sb, Type type) {
        this.sb = sb;
        this.type = type;
    }





    private void _start() {
        this.sb.append('{');
    }

    private void _end() {
        this.sb.append('}');
    }

    public String toString () {
        if (type == Type.OBJECT)
            return "{" + this.sb + "}";
        return "[" + this.sb + "]";
    }

}
