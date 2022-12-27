package fun.listenia.fastjson;

import fun.listenia.fastjson.extra.serialize.SourceOutput;

import java.util.Collection;
import java.util.Map;

public class WriterJSON {

    public static String writeObject (Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        writeObject(new SourceOutput(sb), map);
        return sb.toString();
    }
    protected static void writeObject (SourceOutput source, Map<String, Object> map) {
        source.write('{');
        boolean first = true;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first)
                source.write(',');
            first = false;
            Utils.writeKeyValue(source, entry.getKey(), entry.getValue());
        }
        source.write('}');
    }


    public static String writeList (Collection<Object> list) {
        StringBuilder sb = new StringBuilder();
        writeList(new SourceOutput(sb), list);
        return sb.toString();
    }

    public static String writeArray (Object[] list) {
        StringBuilder sb = new StringBuilder();
        writeArray(new SourceOutput(sb), list);
        return sb.toString();
    }

    static void writeList (SourceOutput source, Collection<Object> list) {
        source.write('[');
        boolean first = true;
        for (Object value : list) {
            if (!first)
                source.write(',');
            first = false;
            Utils.writeRawValue(source, value);
        }
        source.write(']');
    }

    static void writeArray (SourceOutput source, Object[] list) {
        source.write('[');
        boolean first = true;
        for (Object value : list) {
            if (!first)
                source.write(',');
            first = false;
            Utils.writeRawValue(source, value);
        }
        source.write(']');
    }

}
