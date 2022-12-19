package fun.listenia.fastjson;

public abstract class JSONSerializable {

    protected abstract void serialize (Serializer serializer);
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
