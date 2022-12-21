package fun.listenia.fastjson;

import fun.listenia.fastjson.extra.Serializer;

import java.util.List;
import java.util.Map;

public class Utils {

    public static void writeSafeString (StringBuilder sb, String str) {
        final char slash = '\\';
        final char quote = '"';

        for (char c : str.toCharArray()) {
            if (c == slash) {
                sb.append(slash);
                sb.append(slash);
            } else if (c == quote) {
                sb.append(slash);
                sb.append(quote);
            } else {
                sb.append(c);
            }
        }
    }
    public static void writeRawValue (StringBuilder sb, Object value) {
        if (value instanceof Number) {
            sb.append(value);
        } else if (value instanceof Boolean) {
            sb.append(((boolean) value) ? "true" : "false");
        } else if (value instanceof String) {
            sb.append('"');
            writeSafeString(sb, (String) value);
            sb.append('"');
        } else if (value instanceof Map) {
            WriterJSON.writeObject(sb, (Map<String, Object>) value);
        } else if (value instanceof List) {
            WriterJSON.writeList(sb, (List) value);
        } else if (value instanceof Object[]) {
            WriterJSON.writeArray(sb, (Object[]) value);
        } else if (value == null) {
            sb.append("null");
        } else if (value instanceof JSONSerializable) {
            Serializer serializer = Serializer.createSerializer(sb);
            Serializer.serialize((JSONSerializable) value, serializer);
        }
    }

    public static void writeKeyValue (StringBuilder sb, String key, Object value) {
        sb.append('"');
        Utils.writeSafeString(sb, key);
        sb.append('"');
        sb.append(':');
        Utils.writeRawValue(sb, value);
    }

}
