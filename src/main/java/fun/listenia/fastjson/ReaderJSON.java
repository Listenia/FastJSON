package fun.listenia.fastjson;

import fun.listenia.fastjson.extra.deserialize.SourceInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReaderJSON {

    public static Object readRawValue (SourceInput source) {
        char c = source.get();
        if (c == '"') {
            return Utils.readString(source);
        } else if (c == 't') {
            source.skip(3);
            return true;
        } else if (c == 'f') {
            source.skip(4);
            return false;
        } else if (c >= '0' && c <= '9') {
            return Utils.readNumber(source);
        } else if (c == '{') {
            return readRawObject(source);
        } else if (c == '[') {
            return readRawArray(source);
        }
        return null;
    }


    public static Map<String, Object> readRawObject (String json) {
        final char[] chars = json.toCharArray();
        SourceInput source = new SourceInput(chars);
        Utils.requireChar(source.next(), '{');
        return readRawObject(source);
    }

    private static void ignoreChars (SourceInput source) {
        do {
            char c = source.next();
            if (c != ' ' && c != '\t' && c != '\r')
                break;
        } while (true);
    }

    public static Map<String, Object> readRawObject (SourceInput source) {
        Map<String, Object> map = new HashMap<>();
        while ((source.get()) != '}') {
            ignoreChars(source);
            if (source.get() == '}') break;
            String key = Utils.readString(source);
            ignoreChars(source);

            Utils.requireChar(source.get(), ':');

            ignoreChars(source);
            Object value = readRawValue(source);
            map.put(key, value);
            ignoreChars(source);
            if (source.get() != ',') break;
        }
        return map;
    }




    public static List<Object> readRawArray (String json) {
        final char[] chars = json.toCharArray();
        SourceInput source = new SourceInput(chars);
        Utils.requireChar(source.next(), '[');
        return readRawArray(source);
    }

    public static List<Object> readRawArray (SourceInput source) {
        List<Object> list = new ArrayList<>();
        while ((source.get()) != ']') {
            ignoreChars(source);
            if (source.get() == ']') break;
            Object value = readRawValue(source);
            list.add(value);
            ignoreChars(source);
            if (source.get() != ',') break;
        }
        return list;
    }


}
