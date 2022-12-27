package fun.listenia.fastjson.extra.serialize;

import fun.listenia.fastjson.extra.Type;

public class Serializer {

    public static ObjectSerializer createObject () {
        return new ObjectSerializer(new SourceOutput(new StringBuilder()));
    }
    public static ObjectSerializer createObject (SourceOutput source) {
        return new ObjectSerializer(source);
    }

    public static ArraySerializer createArray () {
        return new ArraySerializer(new SourceOutput(new StringBuilder()));
    }

    public static ArraySerializer createArray (SourceOutput source) {
        return new ArraySerializer(source);
    }

    public static void serialize (JSONSerializable obj, Serializer serializer) {
        serializer._start();
        obj.serialize((ObjectSerializer) serializer);
        serializer._end();
    }

    protected final SourceOutput source;
    protected boolean addComa;

    private final Type type;

    protected Serializer (SourceOutput source, Type type) {
        this.source = source;
        this.type = type;
    }





    private void _start() {
        this.source.write('{');
    }

    private void _end() {
        this.source.write('}');
    }

    public String toString () {
        if (type == Type.OBJECT)
            return "{" + this.source.toString() + "}";
        return "[" + this.source.toString() + "]";
    }

}
