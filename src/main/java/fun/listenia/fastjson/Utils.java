package fun.listenia.fastjson;

import fun.listenia.fastjson.extra.deserialize.SourceInput;
import fun.listenia.fastjson.extra.serialize.JSONSerializable;
import fun.listenia.fastjson.extra.serialize.Serializer;

import java.util.List;
import java.util.Map;

public class Utils {

    public static void requireChar(char c, char expected) {
        if (c != expected)
            throw new IllegalArgumentException("Invalid JSON object");
    }

    public static void writeSafeString (StringBuilder sb, String str) {
        final char slash = '\\';
        final char quote = '"';
        boolean hasSlash = false;

        for (char c : str.toCharArray()) {
            if (c == slash) {
                sb.append(slash);
                hasSlash = !hasSlash;
            } else if (c == quote) {
                if (hasSlash) {
                    sb.append(slash);
                    hasSlash = false;
                }
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
            Serializer serializer = Serializer.createObject(sb);
            Serializer.serialize((JSONSerializable) value, serializer);
        } else if (value instanceof Serializer) {
            sb.append(value);
        } else {
            throw new IllegalArgumentException("Invalid JSON object");
        }
    }

    public static void writeKeyValue (StringBuilder sb, String key, Object value) {
        sb.append('"');
        Utils.writeSafeString(sb, key);
        sb.append('"');
        sb.append(':');
        Utils.writeRawValue(sb, value);
    }

    public static String readString (SourceInput source) {
        requireChar(source.get(), '"');
        StringBuilder sb = new StringBuilder();
        boolean escape = false;
        while (true) {
            char c = source.next();
            if (escape) {
                escape = false;
                sb.append(c);
            } else if (c == '\\') {
                escape = true;
            } else if (c == '"') {
                break;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static Number readNumber (SourceInput source) {
        double value = 0;
        int dividor = 1;

        char c = source.get();
        do {
            int diff = c - '0';
            if (diff == -2) // is '.'
                dividor = 10;
            else if (diff <= 9 && diff >= 0) { // is number
                if (dividor == 1) {
                    value = value * 10 + diff;
                } else {
                    value += diff / (double) dividor;
                    dividor *= 10;
                }
            } else { // is other
                break;
            }
            c = source.future();
        } while (true);

        if (dividor == 1) {
            return (int) value;
        } else {
            return value;
        }
    }

}
