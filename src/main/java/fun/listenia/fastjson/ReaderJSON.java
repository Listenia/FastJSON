package fun.listenia.fastjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ReaderJSON {


    private static void requireChar (char c, char expected) {
        if (c != expected)
            throw new IllegalArgumentException("Invalid JSON object");
    }

    public static String readString (char[] chars, AtomicInteger index) {
        requireChar(chars[index.getAndIncrement()], '"');
        StringBuilder sb = new StringBuilder();
        boolean escape = false;
        while (true) {
            char c = chars[index.getAndIncrement()];
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

    public static Number readNumber (char[] chars, AtomicInteger index) {
        double value = 0;
        int dividor = 1;

        while (true) {
            char c = chars[index.get()];
            if ((c < '0' || c > '9') && c != '.') {
                break;
            }

            if (c == '.') {
                dividor = 10;
            } else if (dividor == 1) {
                value *= 10;
                if (c == '0') value += 0;
                else if (c == '1') value += 1;
                else if (c == '2') value += 2;
                else if (c == '3') value += 3;
                else if (c == '4') value += 4;
                else if (c == '5') value += 5;
                else if (c == '6') value += 6;
                else if (c == '7') value += 7;
                else if (c == '8') value += 8;
                else if (c == '9') value += 9;
            } else {
                if (c == '0') value += 0.0 / dividor;
                else if (c == '1') value += 1.0 / dividor;
                else if (c == '2') value += 2.0 / dividor;
                else if (c == '3') value += 3.0 / dividor;
                else if (c == '4') value += 4.0 / dividor;
                else if (c == '5') value += 5.0 / dividor;
                else if (c == '6') value += 6.0 / dividor;
                else if (c == '7') value += 7.0 / dividor;
                else if (c == '8') value += 8.0 / dividor;
                else if (c == '9') value += 9.0 / dividor;
                dividor *= 10;
            }
            index.incrementAndGet();
        }

        if (dividor == 1) {
            return (int) value;
        } else {
            return value;
        }
    }

    public static Object readValue (char[] chars, AtomicInteger index) {
        if (chars[index.get()] == '"') {
            return readString(chars, index);
        } else if (chars[index.get()] == 't') {
            index.addAndGet(4);
            return true;
        } else if (chars[index.get()] == 'f') {
            index.addAndGet(5);
            return false;
        } else if (chars[index.get()] >= '0' && chars[index.get()] <= '9') {
            return readNumber(chars, index);
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
        requireChar(chars[0], '{');
        return readRawObject(chars, new AtomicInteger(1));
    }

    private static Map<String, Object> readRawObject (char[] chars, AtomicInteger index) {
        Map<String, Object> map = new HashMap<>();
        while (true) {
            String key = readString(chars, index);
            requireChar(chars[index.getAndIncrement()], ':');
            Object value = readValue(chars, index);

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
        requireChar(chars[0], '[');
        return readRawArray(chars, new AtomicInteger(1));
    }

    public static List<Object> readRawArray (char[] chars, AtomicInteger index) {
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
