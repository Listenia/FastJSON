package writing;

import fun.listenia.fastjson.WriterJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WritingTest {

    public static void main (String[] args) {
        String value = value();
        System.out.println(value);
    }

    public static String value () {
        Map<String, Object> sub = new HashMap<>();
        sub.put("key", "value");

     List<Object> list = new ArrayList<>();
        list.add(15);
        list.add(0.1D);
        list.add("Test");
        list.add("\\\"Test");

        Map<String, Object> map = new HashMap<>();
        map.put("str", "hello");
        map.put("double", 0.8D);
        map.put("boolean", true);
        map.put("obj", sub);
        map.put("array", list);

        return WriterJSON.writeObject(map);
    }


}
