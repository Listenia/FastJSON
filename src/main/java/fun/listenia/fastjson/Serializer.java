package fun.listenia.fastjson;

public class Serializer {

    public static Serializer createSerializer (StringBuilder sb) {
        return new Serializer(sb);
    }

    public static Serializer createSerializer () {
        StringBuilder sb = new StringBuilder();
        return new Serializer(sb);
    }

    public static void serialize (JSONSerializable obj, Serializer serializer) {
        serializer.start();
        obj.serialize(serializer);
        serializer.end();
    }

    private final StringBuilder sb;
    private boolean addComa;

    protected Serializer (StringBuilder sb) {
        this.sb = sb;
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

    protected void start() {
        this.sb.append('{');
    }

    protected void end() {
        this.sb.append('}');
    }

    public String toString () {
        return "{" + this.sb + "}";
    }

}
