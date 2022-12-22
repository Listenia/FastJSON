package fun.listenia.fastjson;

import fun.listenia.fastjson.extra.ObjectSerializer;
import fun.listenia.fastjson.extra.Serializer;
import fun.listenia.fastjson.extra.Type;

public abstract class JSONSerializable {

    public abstract void serialize (ObjectSerializer serializer);
    // public abstract void serialize (String json);


    @Override
    public String toString() {
        Serializer ser = Serializer.createObject();
        return ser.toString();
    }
}
