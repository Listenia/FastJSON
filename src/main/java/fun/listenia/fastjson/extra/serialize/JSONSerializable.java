package fun.listenia.fastjson.extra.serialize;

import fun.listenia.fastjson.extra.deserialize.Deserializer;

public abstract class JSONSerializable {

    public abstract void serialize (ObjectSerializer serializer);
    public abstract void deserialize (Deserializer deserializer);


    @Override
    public String toString() {
        Serializer ser = Serializer.createObject();
        return ser.toString();
    }
}
