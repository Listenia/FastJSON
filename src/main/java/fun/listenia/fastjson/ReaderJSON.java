package fun.listenia.fastjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ReaderJSON {

    public static Object readValue (char[] chars, AccessibleInteger index) {
        if (chars[index.get()] == '"') {
            return Utils.readString(chars, index);
        } else if (chars[index.get()] == 't') {
            index.addAndGet(4);
            return true;
        } else if (chars[index.get()] == 'f') {
            index.addAndGet(5);
            return false;
        } else if (chars[index.get()] >= '0' && chars[index.get()] <= '9') {
            return Utils.readNumber(chars, index);
        } else if (chars[index.get()] == '{') {
            index.incrementAndGet();
            return readRawObject(chars, index);
        } else if (chars[index.get()] == '[') {
            index.incrementAndGet();
            return readRawArray(chars, index);
        }

        return null;
    }


    public static Map<String, Object> readRawObject (String json) {
        final char[] chars = json.toCharArray();
        Utils.requireChar(chars[0], '{');
        return readRawObject(chars, new AccessibleInteger(1));
    }

    private static void ignoreChars (char[] chars, AccessibleInteger index) {
        char c = chars[index.get()];
        int i = index.get();
        while (chars[i] == ' ' || chars[i] == '\t' || chars[i] == '\r') {
            i++;
        }
        index.set(i);
    }

    private static Map<String, Object> readRawObject (char[] chars, AccessibleInteger index) {
        Map<String, Object> map = new HashMap<>();
        while (true) {
            if (chars[index.get()] == '}') {
                break;
            }

            ignoreChars(chars, index);
            String key = Utils.readString(chars, index);
            ignoreChars(chars, index);

            Utils.requireChar(chars[index.getAndIncrement()], ':');

            ignoreChars(chars, index);
            Object value = readValue(chars, index);
            ignoreChars(chars, index);

            map.put(key, value);
            char c = chars[index.getAndIncrement()];
            if (c == '}') {
                break;
            } else if (c != ',') {
                throw new IllegalArgumentException("Invalid JSON object");
            }
        }
        return map;
    }




    public static List<Object> readRawArray (String json) {
        final char[] chars = json.toCharArray();
        Utils.requireChar(chars[0], '[');
        return readRawArray(chars, new AccessibleInteger(1));
    }

    public static List<Object> readRawArray (char[] chars, AccessibleInteger index) {
        List<Object> list = new ArrayList<>();
        while (true) {
            Object value = readValue(chars, index);
            list.add(value);

            char c = chars[index.getAndIncrement()];
            if (c == ']') {
                break;
            } else if (c != ',') {
                throw new IllegalArgumentException("Invalid JSON object");
            }
        }
        return list;
    }





}
