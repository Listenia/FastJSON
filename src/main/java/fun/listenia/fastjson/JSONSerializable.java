package fun.listenia.fastjson;

import fun.listenia.fastjson.extra.Serializer;

public abstract class JSONSerializable {

    public abstract void serialize(Serializer serializer);
    // public abstract void serialize (String json);


    @Override
    public String toString() {
        Serializer ser = Serializer.createSerializer();
        ser.start();
        this.serialize(ser);
        ser.end();
        return ser.toString();
    }
}
