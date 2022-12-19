package fun.listenia.fastjson;

import java.util.Collection;
import java.util.Map;

public class WriterJSON {

    public static String writeObject (Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        writeObject(sb, map);
        return sb.toString();
    }
    static void writeObject(StringBuilder sb, Map<String, Object> map) {
        sb.append('{');

        int len = map.size();
        int iters = 0;

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();

            sb.append('"');
            Utils.writeSafeString(sb, entry.getKey());
            sb.append('"');
            sb.append(':');

            Utils.writeRawValue(sb, value);
            // value

            iters++;
            if (iters != len) {
                sb.append(',');
            }
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
        int len = list.size();
        int iters = 0;
        for (Object value : list) {
            Utils.writeRawValue(sb, value);
            iters++;
            if (iters != len) {
                sb.append(',');
            }
        }
        sb.append(']');
    }

    static void writeArray(StringBuilder sb, Object[] list) {
        sb.append('[');
        int len = list.length;
        int iters = 0;
        for (Object value : list) {
            Utils.writeRawValue(sb, value);
            iters++;
            if (iters != len) {
                sb.append(',');
            }
        }
        sb.append(']');
    }

}
