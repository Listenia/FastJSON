package fun.listenia.fastjson;

import fun.listenia.fastjson.extra.deserialize.SourceInput;
import fun.listenia.fastjson.extra.serialize.JSONSerializable;
import fun.listenia.fastjson.extra.serialize.Serializer;
import fun.listenia.fastjson.extra.serialize.SourceOutput;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Map;

public class Utils {

    public static void requireChar(char c, char expected) {
        if (c != expected)
            throw new IllegalArgumentException("Invalid JSON object");
    }

    public static void writeSafeString (SourceOutput source, String str) {
        final char slash = '\\';
        final char quote = '"';
        boolean hasSlash = false;

        for (char c : str.toCharArray()) {
            if (c == slash) {
                source.write(slash);
                hasSlash = !hasSlash;
            } else if (c == quote) {
                if (hasSlash) {
                    source.write(slash);
                    hasSlash = false;
                }
                source.write(slash);
                source.write(quote);
            } else {
                source.write(c);
            }
        }
    }
    public static void writeRawValue (SourceOutput source, Object value) {
        if (value instanceof Number) {
            writeNumber(source, (Number) value);
        } else if (value instanceof Boolean) {
            writeString(source, ((boolean) value) ? "true" : "false");
        } else if (value instanceof String) {
            source.write('"');
            writeSafeString(source, (String) value);
            source.write('"');
        } else if (value instanceof Map) {
            WriterJSON.writeObject(source, (Map<String, Object>) value);
        } else if (value instanceof List) {
            WriterJSON.writeList(source, (List) value);
        } else if (value instanceof Object[]) {
            WriterJSON.writeArray(source, (Object[]) value);
        } else if (value == null) {
            writeString(source, "null");
        } else if (value instanceof JSONSerializable) {
            Serializer serializer = Serializer.createObject(source);
            Serializer.serialize((JSONSerializable) value, serializer);
        } else if (value instanceof Serializer) {
            writeString(source, value.toString());
        } else {
            throw new IllegalArgumentException("Invalid JSON object");
        }
    }

    public static void writeKeyValue (SourceOutput source, String key, Object value) {
        source.write('"');
        Utils.writeSafeString(source, key);
        source.write('"');
        source.write(':');
        Utils.writeRawValue(source, value);
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

    public static void writeString (SourceOutput source, String str) {
        for (char c : str.toCharArray()) {
            source.write(c);
        }
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

    public static void writeNumber (SourceOutput source, Number number) {
        if (number instanceof Double || number instanceof Float) {
            writeString(source, Double.toString(number.doubleValue()));
            return;
        }
        writeString(source, Long.toString(number.longValue()));
    }

}
