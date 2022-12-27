package fun.listenia.fastjson.extra.deserialize;

import fun.listenia.fastjson.extra.Type;
import fun.listenia.fastjson.extra.serialize.ArraySerializer;
import fun.listenia.fastjson.extra.serialize.ObjectSerializer;

public class Deserializer {

    public static ObjectDeserializer readObject (SourceInput in) {
        return new ObjectDeserializer(in);
    }

    public static ArrayDeserializer readArray (SourceInput in) {
        return new ArrayDeserializer(in);
    }

   /* public static void serialize (JSONSerializable obj, Deserializer serializer) {
        obj.deserialize(serializer);
    }*/

    protected final SourceInput source;

    private final Type type;

    protected Deserializer (SourceInput source, Type type) {
        this.source = source;
        this.type = type;
    }

}
