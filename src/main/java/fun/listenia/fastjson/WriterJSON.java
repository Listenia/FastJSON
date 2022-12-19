package fun.listenia.fastjson;

import java.util.Collection;
import java.util.Map;

public class WriterJSON {

    public static String writeObject (Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        writeObject(sb, map);
        return sb.toString();
    }
    protected static void writeObject (StringBuilder sb, Map<String, Object> map) {
        sb.append('{');
        boolean first = true;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (!first)
                sb.append(',');
            first = false;
            Utils.writeKeyValue(sb, entry.getKey(), entry.getValue());
        }
        sb.append('}');
    }


    public static String writeList (Collection<Object> list) {
        StringBuilder sb = new StringBuilder();
        writeList(sb, list);
        return sb.toString();
    }

    public static String writeArray (Object[] list) {
        StringBuilder sb = new StringBuilder();
        writeArray(sb, list);
        return sb.toString();
    }

    static void writeList (StringBuilder sb, Collection<Object> list) {
        sb.append('[');
        boolean first = true;
        for (Object value : list) {
            if (!first)
                sb.append(',');
            first = false;
            Utils.writeRawValue(sb, value);
        }
        sb.append(']');
    }

    static void writeArray (StringBuilder sb, Object[] list) {
        sb.append('[');
        boolean first = true;
        for (Object value : list) {
            if (!first)
                sb.append(',');
            first = false;
            Utils.writeRawValue(sb, value);
        }
        sb.append(']');
    }

}
